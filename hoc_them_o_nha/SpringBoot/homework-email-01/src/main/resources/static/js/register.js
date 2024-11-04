$(document).ready(function () {

    $("#register-account").click(function () {
        // 1. lay du lieu tu form -> tao 1 object
        const formData = $("#registration-form").serializeArray();
        const body = {};
        for (let i = 0; i < formData.length; i++) {
            body[formData[i].name] = formData[i].value;
        }

        // 2. call api bang ajax
        $.ajax({
            type: "POST",
            url: "/api/v1/accounts",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(body),
            success: function (response) {
                // thanh cong thi => hien thi message dang ky thanh cong, check mail de kich hoat tai khoan
                alert("Dang ky thanh cong, vui long kiem tra email de kich hoat tai khoan")
            },
            error: function (err) {
                // show error
                console.log(err);
            }
        });
    })
})