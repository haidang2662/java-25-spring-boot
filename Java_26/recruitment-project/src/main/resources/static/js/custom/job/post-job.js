$(document).ready(function () {

    $.validator.addMethod(
        "futureDate",
        function (value, element) {
            if (!value) return true;
            const dob = new Date(value);
            const today = new Date();
            return dob > today;
        },
        "Date must be a date in the future"
    );


    $("#post-job-form").validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            "name": {
                required: true,
                maxlength: 50,
            },
            "position": {
                required: true,
                maxlength: 50,
            },
            "yearOfExperienceFrom": {
                required: true,
                min: 1,
            },
            "yearOfExperienceTo": {
                required: true,
                min: 1,
            },
            "workingAddress": {
                required: true,
                maxlength: 50,
            },
            "expiredDate": {
                required: true,
                futureDate: true,
            },
            "recruitingQuantity": {
                required: true,
                min: 1,
            },
            "skills": {
                required: true,
                maxlength: 1000,
            },
            "benefit": {
                required: true,
                maxlength: 5000,
            },
            "requirement": {
                required: true,
                maxlength: 5000,
            },
            "salaryFrom": {
                required: true,
                min: 1,
            },
            "salaryTo": {
                required: true,
                min: 1,
            },
            "description": {
                required: true,
                maxlength: 5000,
            },
        },
        messages: {
            "name": {
                required: "Job title is required.",
                maxlength: "Job title must not exceed 50 characters.",
            },
            "position": {
                required: "Position is required.",
                maxlength: "Position must not exceed 50 characters.",
            },
            "yearOfExperienceFrom": {
                required: "Year Of Experience From is required.",
                min: "Year Of Experience From must be greater than or equal to 1"
            },
            "yearOfExperienceTo": {
                maxlength: "Year Of Experience To must not exceed 50 characters.",
                min: "Year Of Experience To must be greater than or equal to 1"

            },
            "workingAddress": {
                required: "Working Address is required.",
                maxlength: "Working Address must not exceed 50 characters.",
            },
            "recruitingQuantity": {
                maxlength: "Recruiting Quantity must not exceed 50 characters.",
                min: "Recruiting Quantity must be greater than or equal to 1",
            },
            "expiredDate": {
                maxlength: "The expiration date must not exceed 50 characters.",
                futureDate: "The expiration date must be in the future",
            },
            "skills": {
                required: "Skills is required.",
                maxlength: "Skills must not exceed 1000 characters.",
            },
            "benefit": {
                required: "Benefit is required.",
                maxlength: "Benefit must not exceed 5000 characters.",
            },
            "requirement": {
                required: "Requirement is required.",
                maxlength: "Requirement must not exceed 5000 characters.",
            },
            "salaryFrom": {
                maxlength: "Salary from must not exceed 50 characters.",
                min: "Salary from must be greater than or equal to 1",
            },
            "salaryTo": {
                maxlength: "Salary to must not exceed 50 characters.",
                min: "Salary to must be greater than or equal to 1",
            },
            "description": {
                required: "Description is required.",
                maxlength: "Description must not exceed 5000 characters.",
            },
        }
    });

    $("#post-job-btn").click(async function () {
        const isValidForm = $("#post-job-form").valid();
        if(!isValidForm){
            return;
        }
        $("#post-job-btn").prop("disabled", true);
        $("#job-posting-spinner").toggleClass('d-none');

        const formData = $("#post-job-form").serializeArray();
        const postJob = {};
        for (let i = 0; i < formData.length; i++) {
            postJob[formData[i].name] = formData[i].value;
        }

        await $.ajax({
            url: "/api/v1/jobs",
            type: "POST",
            data: JSON.stringify(postJob),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                showToast("Post job successfully", SUCCESS_TOAST);
            },
            error: function () {
                showToast("Post job failed", ERROR_TOAST);
            }
        });

        $("#post-job-btn").prop("disabled", false);
        $("#job-posting-spinner").toggleClass('d-none');
    });

    // Lay thong tin chi tiet cua 1 dau sach
    async function getJobDetails(id) {
        let job = null;
        await $.ajax({
            url: '/api/v1/jobs/' + id,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                job = data;
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
        return job;
    }

    // xử lý nút update
    $(".update-job-btn").click(async function (event) {
        // 1 . Lay ra id cua sach
        const bookId = $(event.currentTarget).attr("book-id");
        // 2 . Lay thong tin cua sach -> goi API lay thong tin chi tiet
        const book = await getBookDetails(bookId);
        if (book == null) {
            return;
        }

        // 3 . Dien thong tin cua dau sach vao form cua modal
        $("#book-form #name").val(book.name);
        $("#book-form #price").val(book.price);
        $("#book-form #publisher").val(book.publisher);
        $("#book-form #publishedYear").val(book.publishedYear);

        //Them đánh dấu id giup phan biet save và update để xử lý dữ kiện
        $("#save-book-btn").attr("book-id" , bookId);

        $("#book-modal .modal-title").text("Cập nhât đầu sách");

        // 4 . Show modal len :
        $("#book-modal").modal('show');
    });

});