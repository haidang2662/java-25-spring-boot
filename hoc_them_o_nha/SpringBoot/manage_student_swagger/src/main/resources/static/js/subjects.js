$(document).ready(function (){

    // Xử lý validate :
    const subjectValidator = $("#subject-form").validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            "name": {
                required: true,
                maxlength: 200
            },
            "totalCredits": {
                required: true,
                min: 1
            }
        },
        messages: {
            "name": {
                required: "Tên môn học là bắt buộc.",
                maxlength: "Tên môn học không được vượt quá 200 ký tự."
            },
            "totalCredits": {
                required: "Nhập số tín chỉ là bắt buộc.",
                min: "Số tín chỉ phải lớn hơn hoặc bằng 1."
            }
        }
    });

    //  Xử lý luu mon hoc
    $("#save-subject-btn").click(function (event){
        // kiem tra xem bam tao moi hay cap nhat
        const subjectId = $(event.currentTarget).attr("subject-id");
        if(subjectId) {
            updateSubject(subjectId);
        } else {
            createSubject();
        }
    });

    function updateSubject(subjectId) {
        const isValidForm = $("#subject-form").valid();
        if(!isValidForm){
            return;
        }
        const formData = $("#subject-form").serializeArray();
        const subject ={};
        for (let i = 0; i < formData.length; i++) {
            subject[formData[i].name] = formData[i].value;
        }

        $.ajax({
            url: '/api/v1/subjects/' + subjectId,
            type: 'PUT',
            data: JSON.stringify(subject),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Cập nhật môn học thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
    }

    function createSubject() {
        const isValidForm = $("#subject-form").valid();
        if(!isValidForm){
            return;
        }
        const formData = $("#subject-form").serializeArray();
        const subject ={};
        for (let i = 0; i < formData.length; i++) {
            subject[formData[i].name] = formData[i].value;
        }
        console.log(subject);

        $.ajax({
            url: '/api/v1/subjects' ,
            type: 'POST',
            data: JSON.stringify(subject),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Tạo mới môn học thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
    }

    //Xu ly xoa mon hoc
    $(".delete-btn").click(function (event){

        const confirmResult = confirm("Bạn có chắc chắn muốn xóa môn học này?");
        if (!confirmResult) {
            return;
        }

        const subjectId = $(event.currentTarget).attr("subject-id");

        // xóa
        $.ajax({
            url: '/api/v1/subjects/' + subjectId,
            type: 'DELETE',
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Xóa môn học thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
    });

    // xử lý nút update
    $(".update-btn").click(async function (event) {
        // 1 . Lay ra id cua mon hoc
        const subjectId = $(event.currentTarget).attr("subject-id");
        // 2 . Lay thong tin cua mon hoc
        const subject = await getSubjectDetails(subjectId);
        if (subject == null) {
            return;
        }

        // 3 . Dien thong tin cua mon hoc vao form cua modal
        $("#subject-form #name").val(subject.name);
        $("#subject-form #totalCredits").val(subject.totalCredits);

        //Them đánh dấu id giup phan biet save và update để xử lý dữ kiện
        $("#save-subject-btn").attr("subject-id" , subjectId);

        $("#subject-modal .modal-title").text("Cập nhât môn học");

        // 4 . Show modal len :
        $("#subject-modal").modal('show');
    });

    async function getSubjectDetails(subjectId) {
        let subject = null;

        await $.ajax({
            url: '/api/v1/subjects/' + subjectId,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                subject = data;
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
        return subject;
    }

    // bắt sự kiện modal ẩn đi
    $('#subject-modal').on('hidden.bs.modal', function () {
        // reset dữ liệu trong form
        $("#subject-form").trigger("reset");
        $("#save-subject-btn").removeAttr("subject-id");
        $("#subject-modal .modal-title").text("Tạo mới môn học : ");

        // reset validate
        subjectValidator.resetForm();
        $("label.error").hide();
        $(".error").removeClass("error");
    });
});