$(document).ready(function () {

    $.validator.addMethod("pastYear", function (value, element) {
        const currentDate = new Date();
        const inputDate = new Date(value);
        return currentDate > inputDate;
    }, "Ngày sinh phải là ngày trong quá khứ");

    //Xu ly validate:
    const studentValidate = $("#student-form").validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            "name": {
                required: true,
                maxlength: 200
            },
            "phone": {
                required: true
            },
            "dob": {
                required: true,
                pastYear: true
            },
            "address": {
                required: true,
                maxlength: 200
            }
        },
        messages: {
            "name": {
                required: "Tên sinh viên là bắt buộc.",
                maxlength: "Tên sinh viên không được vượt quá 200 ký tự."
            },
            "phone": {
                required: "SĐT là bắt buộc.",
            },
            "dob": {
                required: "Ngày sinh là bắt buộc.",
                pastYear: "Ngày sinh phải trong quá khứ"
            },
            "address": {
                required: "Địa chỉ là bắt buộc.",
                maxlength: "Địa chỉ không được vượt quá 200 ký tự."
            }
        }
    });

    function createStudent() {

        const isValidForm = $("#student-form").valid();
        if(!isValidForm){
            return false;
        };

        //B1 : Lay thong tin
        const formData = $("#student-form").serializeArray();
        const student = {};
        for (let i = 0; i < formData.length; i++) {
            student[formData[i].name] = formData[i].value;
        };

        //B2 : Goi ham ajax de luu ve ben Resource:

        $.ajax({
            url: '/api/v1/students',
            type: 'POST',
            data: JSON.stringify(student),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Tạo mới sinh viên thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });

    };

    function updateStudent(studentId) {

        const isValidForm = $("#student-form").valid();
        if(!isValidForm){
            return false;
        };

        //B1 : Lay thong tin tu student-form
        const formData = $("#student-form").serializeArray();
        const student = {};
        for (let i = 0; i < formData.length; i++) {
            student[formData[i].name] = formData[i].value;
        }

        //B2 : Goi ajax de xu ly ben Resource

        $.ajax({
            url: '/api/v1/students/' + studentId,
            type: 'PUT',
            data: JSON.stringify(student),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Cập nhật sinh viên thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
    }


    // Xu ly nút sinh vien tao moi
    $("#save-student-btn").click(function (event) {

        const studentId = $(event.currentTarget).attr("student-id");

        if (studentId) {
            return updateStudent(studentId);
        } else {
            return createStudent();
        };
    });



    // Xu ly nút sinh vien update
    $(".update-btn").click(async function (event) {

        //B1 : lay ra id sinh vien
        const studentId = $(event.currentTarget).attr("student-id");

        //B2 : Lay ra thong tin sinh vien
        const student = await getStudentDetails(studentId);
        if(student == null){
            return;
        }

        //B3 : Dien du lieu vao modal
        $("#student-form #name").val(student.name);
        $("#student-form #phone").val(student.phone);
        $("#student-form #dob").val(student.dob);
        $("#student-form #address").val(student.address);

        //doi tieu de
        $("#student-modal .modal-title").text("Cập nhật sinh viên ");

        //Danh dau bang id
        $("#save-student-btn").attr("student-id" , studentId)

        //B4 show modal len :
        $("#student-modal").modal('show');
    });

    async function getStudentDetails(studentId) {
        let student = null;

        await $.ajax({
            url: '/api/v1/students/' + studentId,
            type: 'GET',
            data: JSON.stringify(student),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                student = data;
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
        return student;
    }
});