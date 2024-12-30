$(document).ready(async function () {

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
        if (!isValidForm) {
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

    // Lấy jobId từ URL path
    const pathParts = window.location.pathname.split('/');
    const jobId = pathParts[pathParts.length - 1]; // Lấy phần tử cuối cùng


    // Thay đổi tiêu đề form dựa trên jobId
    if (jobId) {
        // Chế độ cập nhật
        await loadJobDetails(jobId);
        $("#form-title").text("Update Job");
    } else {
        // Chế độ tạo mới
        $("#form-title").text("Post Job");
    }

    $(document).ready(async function () {
        // Lấy jobId từ URL path
        const pathParts = window.location.pathname.split('/');
        const jobId = pathParts[pathParts.length - 1]; // Lấy phần tử cuối cùng


        // Thay đổi tiêu đề form và tải dữ liệu nếu có jobId
        if (jobId) {
            $("#form-title").text("Update Job");
            await loadJobDetails(jobId);
        } else {
            $("#form-title").text("Post Job");
        }

        // Xử lý sự kiện khi nhấn nút Save
        $("#save-job-btn").click(async function () {
            const isValidForm = $("#post-job-form").valid();
            if (!isValidForm) {
                return;
            }

            $("#save-job-btn").prop("disabled", true);
            $("#job-saving-spinner").toggleClass("d-none");

            // Thu thập dữ liệu từ form
            const formData = $("#post-job-form").serializeArray();
            const jobData = {};
            formData.forEach(item => jobData[item.name] = item.value);

            if (jobId) {
                try {
                    await $.ajax({
                        url: `/api/v1/jobs/${jobId}`,
                        type: "PUT",
                        data: JSON.stringify(jobData),
                        contentType: "application/json; charset=utf-8",
                    });
                    showToast("Job updated successfully", SUCCESS_TOAST);
                    await loadJobDetails(jobId); // Tải lại dữ liệu
                    window.location.href = `/companies/jobs/${jobId}`; // Chuyển về chi tiết công việc
                } catch (error) {
                    showToast("Failed to update job", ERROR_TOAST);
                }
            } else {
                // Nếu không có jobId -> Tạo mới công việc
                try {
                    await $.ajax({
                        url: "/api/v1/jobs",
                        type: "POST",
                        data: JSON.stringify(jobData),
                        contentType: "application/json; charset=utf-8",
                    });
                    showToast("Job posted successfully", SUCCESS_TOAST);
                    window.location.href = "/companies/jobs"; // Chuyển về danh sách công việc
                } catch (error) {
                    showToast("Failed to post job", ERROR_TOAST);
                }
            }

            $("#save-job-btn").prop("disabled", false);
            $("#job-saving-spinner").toggleClass("d-none");
        });
    });

});

// Hàm tải dữ liệu job từ API và điền vào form
async function loadJobDetails(jobId) {
    try {
        const job = await $.ajax({
            url: `/api/v1/jobs/${jobId}`,
            type: "GET",
            contentType: "application/json; charset=utf-8",
        });

        // Lặp qua các key trong object job và gán giá trị trực tiếp vào form
        for (const key in job) {
            const field = $(`[name='${key}']`);
            // Nếu tìm thấy field tương ứng trong form
            if (field.length > 0) {
                if (field.is("input, textarea")) {
                    field.val(job[key]); // Gán giá trị cho input và textarea
                } else if (field.is("select")) {
                    field.val(job[key]); // Gán giá trị cho select
                }
            }
        }
    } catch (err) {
        showToast("Failed to load job details", ERROR_TOAST);
    }
}

