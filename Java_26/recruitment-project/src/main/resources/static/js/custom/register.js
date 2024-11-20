$(document).ready(function () {

    $("p").hide();

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
            contentType:"application/json; charset=utf-8",
            success:function (data){
                //b3 : Hien thi thong bao thanh cong va yeu cau xac thuc tai khoan
                $("p").show();
            },
            error:function (err){
                alert(err.responseJSON.message)
            }
        });

    });

    $("#resend-email").click(function (){
        $.ajax({
            url: "/api/v1/authentications/registration",
            type: "POST",
            contentType:"application/json; charset=utf-8",
        })
    });
});