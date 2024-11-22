$(document).ready(function (){

    $("#success-activation").hide()
    $("#fail-activation").hide()

    const currentUrl = window.location.href;

    // Tách URL thành các phần tử dựa trên dấu "/"
    const segments = currentUrl.split('/');

    // Lấy phần tử thứ 5 (đếm từ 0) - là ID
    const id = segments[4]; // Phần tử thứ 4 (chỉ số bắt đầu từ 0)

    console.log("ID lấy được:", id); // Kết quả: 2

    $.ajax({
        url:`/api/v1/accounts/${id}/activations`,
        type:"POST",
        contentType: "application/json; charset=utf-8",
        success:function (){
            $("#success-activation").show()
            setTimeout(function () {
                window.location.href = "/";
            }, 2000);
        },
        error:function (){
            $("#fail-activation").show()
        }
    })

});