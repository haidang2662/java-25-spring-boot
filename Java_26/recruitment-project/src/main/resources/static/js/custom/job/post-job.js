$(document).ready(function () {

    $("#post-job-btn").click(function () {
        const formData = $("#post-job-form").serializeArray();
        const postJob = {};
        for (let i = 0; i < formData.length; i++) {
            postJob[formData[i].name] = formData[i].value;
        }

        $.ajax({
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
    });

});