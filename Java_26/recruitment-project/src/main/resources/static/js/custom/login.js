$(document).ready(function () {

    $("#login-btn").click(function () {
        //B1 : Lay du lieu tu form
        const formData = $("#login-form").serializeArray();
        let login = {};
        for (let i = 0; i < formData.length; i++) {
            login[formData[i].name] = formData[i].value;
        }
        login["password"] = md5(login["password"]);
        //B2 : Call ajax
        $.ajax({
            url: "/api/v1/authentications/login",
            type: "POST",
            data: JSON.stringify(login),
            contentType: "application/json; charset=utf-8",
            success: async function (data) {
                localStorage.setItem("accessToken", data?.jwt);
                localStorage.setItem("refreshToken", data?.refreshToken);
                const account  = {
                    id: data?.id,
                    email: data?.username,
                    role: data?.roles?.[0]
                };
                localStorage.setItem("account", JSON.stringify(account));

                // lấy thông tin chi tiết account
                const accountInfo = await getAccountDetail(account.id);
                localStorage.setItem("account-info", JSON.stringify(accountInfo));

                location.href = "/";
            },
            error: function (err) {
                if (err?.responseJSON?.status == 401) {
                    showToast("Email or password incorrect", ERROR_TOAST);
                    return;
                }
                handleResponseError(err, "Login failed");
            }

        });
    });

});