$(document).ready(function () {

    $("#send-email-forget-password-btn").click(function () {
        const inputEmail = $("#input-email").val();

        $.ajax({
            url: "/api/v1/accounts/password_forgotten_emails",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                email: inputEmail
            }),
            success: function () {
                showToast("Send email successfully", SUCCESS_TOAST);
                setTimeout(function () {
                    location.href = "/";
                }, 3000);
            },
            error: function () {
                showToast("Send email failed", ERROR_TOAST);
            }
        });

    });

});