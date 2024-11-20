$(document).ready(function () {

    $("#success-register").hide();
    $("#failed-register").hide();
    $("#register-btn").click(function () {
        // const isValidForm = $("register-form").valid();
        // if(!isValidForm){
        //     return;
        // }

        //b1 : Lay du lieu tu form
        const formData = $("#register-form").serializeArray();
        const register = {};
        for (let i = 0; i < formData.length; i++) {
            register[formData[i].name] = formData[i].value;
        }
        //b2 : call ajax
        $.ajax({
            url: "/api/v1/authentications/registration",
            type: "POST",
            data: JSON.stringify(register),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                //b3 : Hien thi thong bao thanh cong va yeu cau xac thuc tai khoan
                $("#resend-email").attr("account-id", data.id);
                $("#success-register").show();
            },
            error: function (err) {
                $("#failed-register").show();
            }
        });
    });

    $("#resend-email").click(function () {
        const id = $("#resend-email").attr("account-id");
        $.ajax({
            url: "/api/v1/accounts/" + id + "/activation_emails",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                showToast("Resent email successfully", SUCCESS_TOAST);
            },
            error: function (err) {
                showToast("Resent email failed", ERROR_TOAST);
            }
        })
    });
});