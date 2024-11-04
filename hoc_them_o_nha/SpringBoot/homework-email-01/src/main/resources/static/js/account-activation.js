$(document).ready(function () {

    function activateAccount() {
        $.ajax({
            type: "PUT",
            url: "/api/v1/accounts/" + $("#account-id").html() + "/activation",
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                // thanh cong thi => hien thi message dang ky thanh cong, check mail de kich hoat tai khoan
                window.location = '/accounts/activation-success';
                // alert("Kich hoat tai khoan thanh cong")
            },
            error: function (err) {
                // show error
                console.log(err);
                window.location = '/accounts/activation-failed';//......
            }
        });
    }

    activateAccount();
})