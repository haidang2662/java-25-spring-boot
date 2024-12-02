$(document).ready( function () {

    // const currentUrl = window.location.href;

    // // Tách URL thành các phần tử dựa trên dấu "/"
    // const segments = currentUrl.split('/');
    //
    // // Lấy phần tử thứ 5 (đếm từ 0) - là ID
    // const id = segments[4]; // Phần tử thứ 4 (chỉ số bắt đầu từ 0)
    $("#change-password-btn").click(async function (event) {

        event.preventDefault(); // Ngăn chặn hành vi mặc định của form

        const account = JSON.parse(localStorage.getItem("account"));
        const accountId = account?.id;

        const newPassword = $("#newPassword").val();
        const confirmedPassword = $("#confirmPassword").val();

        if(newPassword !== confirmedPassword){
            showToast("Password and Confirm Password do not match" , ERROR_TOAST);
            return;
        }

        await $.ajax({
            url: `/api/v1/accounts/${accountId}/password`,
            type: "PATCH",
            contentType: "application/json; charset=utf-8",
            headers: {"Authorization": "Bearer " + localStorage.getItem('accessToken')},
            data : JSON.stringify({
                password : newPassword,
                confirmedPassword: confirmedPassword,
            }),
            success: function () {
                showToast("Change password successfully" , SUCCESS_TOAST);
                location.href="/";
            },
            error: function () {
                showToast("Change password failed" , ERROR_TOAST)
            }
        });


    })


});