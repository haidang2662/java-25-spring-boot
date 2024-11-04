$(document).ready(function () {

    async function initData() {
        $("#finalScore-form #studentId").empty();
        $("#finalScore-form #subjectId").empty();

        const students = await getStudents();

        // đổ dữ liệu (render dữ liệu vào form cho mượn sách) -> jQuery
        for (let i = 0; i < students.length; i++) {
            const option = `<option value="${students[i].id}">${students[i].name}</option>`;
            $("#finalScore-form #studentId").append($(option));
        }

        const subjects = await getSubjects();
        for (let i = 0; i < subjects.length; i++) {
            const option = `<option value="${subjects[i].id}">${subjects[i].name}</option>`;
            $("#finalScore-form #subjectId").append($(option));
        }
    }

    initData();

    async function getStudents() {
        let students = null;
        await $.ajax({
            url: '/api/v1/students',
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                students = data;
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
        return students;
    };

    async function getSubjects() {
        let subjects = null;
        await $.ajax({
            url: '/api/v1/subjects',
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                subjects = data;
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
        return subjects;
    };

    // Thêm custom rule để kiểm tra năm phải là quá khứ
    $.validator.addMethod("pastYear", function(value, element) {
        const currentDate = new Date(); // Lấy ngày hiện tại
        const inputDate = new Date(value); // Lấy ngày nhập vào từ form
        return currentDate > inputDate;
    }, "Năm xuất bản phải là thời điểm trong quá khứ hoặc  hiện tại.");

    // Xử lý validate :
    const finakScoreValidator = $("#finakScore-form").validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            "studentId": {
                required: true,
                min: 1
            },
            "subjectId": {
                required: true,
                min: 1
            },
            "scoreSubject": {
                required: true,
                min: 1
            },
            "examDay": {
                required: true,
                pastYear: true
            },
            "examTimes": {
                required: true,
                min: 1,
                max: 3
            }
        },
        messages: {
            "studentId": {
                required: "Id của sinh viên là bắt buộc",
                min: "Id của sinh viên không được âm"
            },
            "subjectId": {
                required: "Id của môn học là bắt buộc",
                min: "Id của môn học không được âm"
            },
            "scoreSubject": {
                required: "Điểm tổng kết không được bỏ trống",
                min: "Điểm tổng kết phải tối thiểu bằng 1"
            },
            "examDay": {
                required: "ngày thi là bắt buộc.",
                futureYear: "ngày thi là ngày trong quá khứ"
            },
            "examTimes": {
                required: "Lần thi là bắt buộc.",
                min: "Lần thi phải tối thiểu bằng 1",
                max: "Lần thi phải tối đa bằng 3"
            }
        }
    });

    // Xử lý luu lươt muon lay du lieu tu tren web
    $("#save-finalScore-btn").click(function (event) {
        //kiem tra xem tao moi hay update
        const finalScoreId = $(event.currentTarget).attr("finalScore-id");
        if (finalScoreId) {
            updateFinalScore(finalScoreId);
        } else {
            createFinalScore();
        }
    });

    function updateFinalScore(finalScoreId) {
        const isValidForm = $("#finalScore-form").valid();
        if (!isValidForm) {
            return;
        }
        const formData = $("#finalScore-form").serializeArray();
        const finalScore = {};
        for (let i = 0; i < formData.length; i++) {
            finalScore[formData[i].name] = formData[i].value;
        }

        $.ajax({
            url: '/api/v1/final_scores/' + finalScoreId,
            type: 'PUT',
            data: JSON.stringify(finalScore),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Cập nhật điểm tổng kết thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
    }

    function createFinalScore() {
        const isValidForm = $("#finalScore-form").valid();
        if (!isValidForm) {
            return;
        }
        const formData = $("#finalScore-form").serializeArray();
        const finalScore = {};
        for (let i = 0; i < formData.length; i++) {
            finalScore[formData[i].name] = formData[i].value;
        }

        $.ajax({
            url: '/api/v1/final_scores' ,
            type: 'POST',
            data: JSON.stringify(finalScore),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Tạo mới điểm tổng kết thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
    }

    $(".delete-btn").click(function (event){
        const confirmResult = confirm("Bạn có chắc chắn muốn xóa điểm tổng kết này?");
        if (!confirmResult) {
            return;
        }

        const finalScoreId = $(event.currentTarget).attr("finalScore-id");

        $.ajax({
            url: '/api/v1/finalScores/' + finalScoreId,
            type: 'DELETE',
            data: JSON.stringify(finalScore),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Xóa điểm tổng kết thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
    });



    //Xử lý update finalScore
    $(".update-btn").click(async function (event) {
        await initData();

        //1 . Lấy ra id của finalScore
        const finalScoreId = $(event.currentTarget).attr("finalScore-id");
        //2 . Lay ra tong tin chi tiet
        const finalScore = await getFinalScoreDetails(finalScoreId);
        if (finalScore == null) {
            return;
        }

        // 3 . Dien thong tin cua sach vao modal :
        $("#finalScore-form #studentId").val(finalScore.student.id);
        $("#finalScore-form #subjectId").val(finalScore.subject.id);
        $("#finalScore-form #scoreSubject").val(finalScore.scoreSubject);
        $("#finalScore-form #examDay").val(finalScore.examDay);
        $("#finalScore-form #examTimes").val(finalScore.examTimes);

        // Them danh dau id giup phan biet save va update

        $("#save-finalScore-btn").attr("finalScore-id", finalScoreId);

        $("#finalScore-modal .modal-title").text("Cập nhật điểm tổng kết");

        // 4 : show modal lên
        $("#finalScore-modal").modal('show');
    });

    async function getFinalScoreDetails(finalScoreId) {
        let finalScore = null;
        await $.ajax({
            url: '/api/v1/final_scores/' + finalScoreId,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                finalScore = data;
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
        return finalScore;
    }

    // bắt sự kiện modal ẩn đi
    $('#finalScore-modal').on('hidden.bs.modal', function () {
        // reset dữ liệu trong form
        $("#finalScore-modal").trigger("reset");
        $("#save-finalScore-btn").removeAttr("finalScore-id");
        $("#finalScore-modal .modal-title").text("Tạo mới điểm tổng kết : ");

        // reset validate
        finakScoreValidator.resetForm();
        $("label.error").hide();
        $(".error").removeClass("error");
    });

    // bắt sự kiện mở modal khi bấm Tạo mới
    $("#open-finalScore-modal").click(async () => {
        initData();
        $('#finalScore-modal').modal('show');
    });
});