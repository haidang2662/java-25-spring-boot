$(document).ready(function () {// đợi cho html render xong thì code js bên trong này mới chạy

    // định nghĩa ra các rule để validate form bạn đọc
    const readerValidator = $("#reader-form").validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            "name": {
                required: true,
                maxlength: 200
            },
            "address": {
                required: true,
                maxlength: 200
            },
            "phone": {
                required: true,
                maxlength: 10,
                digits: true // chỉ chứa các kí tự số
            },
            "dob": {
                required: true,
            },
            "email": {
                required: true,
                email: true
            }
        },
        messages: {
            "name": {
                required: "Tên bạn đọc bắt buộc nhập",
                maxlength: "Tên bạn đọc tối đa 200 ký tự"
            },
            "address": {
                required: "Địa của của bạn đọc bắt buộc nhập",
                maxlength: "Địa chỉ bạn đọc tối đa 200 ký tự"
            },
            "email": {
                required: "Email của bạn đọc bắt buộc nhập",
                email: "Email không đúng định dạng"
            }
        }
    });

    // xử lý lưu bạn đọc
    $("#save-reader-btn").click(function (event) {
        // kiểm tra xem bấm tạo mới hay bấm cập nhật
        const readerId = $(event.currentTarget).attr("reader-id");
        if (readerId) { // cập nhật
            updateReader(readerId);
        } else { // tạo mới
            createReader();
        }
    });

    function updateReader(id) {
        const isValidForm = $("#reader-form").valid();
        if (!isValidForm) {
            return;
        }
        const formData = $("#reader-form").serializeArray();
        const reader = {};
        for (let i = 0; i < formData.length; i++) {
            reader[formData[i].name] = formData[i].value;
        }

        $.ajax({
            url: '/api/v1/readers/' + id,
            type: 'PUT',
            data: JSON.stringify(reader),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Cập nhật bạn đọc thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.message);
            }
        });
    }

    function createReader() {
        const isValidForm = $("#reader-form").valid();
        if (!isValidForm) {
            return;
        }

        // 1 - lấy được các thông tin mà người dùng nhập ở form
        // const ten = $("#reader-form #name").val();
        // const diaChi = $("#reader-form #address").val();
        // const ngaySinh = $("#reader-form #dob").val();
        // const sdt = $("#reader-form #phone").val();
        // const email = $("#reader-form #email").val();
        //
        // const reader = {
        //     name: ten,
        //     address: diaChi,
        //     dob: ngaySinh,
        //     email,
        //     phone: sdt
        // };
        // console.log(reader);

        const formData = $("#reader-form").serializeArray();
        const reader = {};
        for (let i = 0; i < formData.length; i++) {
            reader[formData[i].name] = formData[i].value;
            // reader["name"] = 'dang hai';
            // reader["phone"] = '0975186411';
        }
        // truy cập vào thuộc tính Id của reader có 2 cách reader.id hoặc reader["id"]


        // 2 - gọi lên phía java (ReaderResource) để lưu reader --> dùng ajax
        $.ajax({
            url: '/api/v1/readers',
            type: 'POST',
            data: JSON.stringify(reader),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Tạo mới bạn đọc thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.message);
            }
        });
    }

    // xử lý xóa
    $(".delete-btn").click(function (event) {
        const confirmResult = confirm("Bạn có chắc chắn muốn xóa bạn đọc này?");
        if (!confirmResult) {
            return;
        }

        const readerId = $(event.currentTarget).attr("reader-id");

        // xóa
        $.ajax({
            url: '/api/v1/readers/' + readerId,
            type: 'DELETE',
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Xóa bạn đọc thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.message);
            }
        });
    });

    // xử lý bấm bút chì (cách 1)
    // $(".update-btn").click(function (event) {
    //     // 1 - lấy ra id của bạn đọc
    //     const readerId = $(event.currentTarget).attr("reader-id");
    //
    //     // 2 - lấy ra thông tin của bạn đọc -> gọi API lấy thông tin chi tiết bạn đọc
    //     $.ajax({
    //         url: '/api/v1/readers/' + readerId,
    //         type: 'GET',
    //         contentType: 'application/json; charset=utf-8',
    //         success: function (data) {
    //             // 3 - điền thông tin bạn đọc vào form của modal
    //             $("#reader-form #name").val(data.name);
    //             $("#reader-form #phone").val(data.phone);
    //             $("#reader-form #address").val(data.address);
    //             $("#reader-form #email").val(data.email);
    //             $("#reader-form #dob").val(data.dob);
    //
    //             // 4 - show modal lên
    //             $("#reader-modal").modal('show');
    //         },
    //         error: function (err) {
    //             alert(err.responseJSON.message);
    //         }
    //     });
    // });

    // xử lý bấm bút chì (cách 2)
    $(".update-btn").click(async function (event) {
        // 1 - lấy ra id của bạn đọc
        const readerId = $(event.currentTarget).attr("reader-id");

        // 2 - lấy ra thông tin của bạn đọc -> gọi API lấy thông tin chi tiết bạn đọc
        const reader = await getReaderDetails(readerId);
        if (reader == null) {// không lấy được dữ liệu từ phía backend => không làm gì hết
            return;
        }

        // 3 - điền thông tin bạn đọc vào form của modal
        $("#reader-form #name").val(reader.name);
        $("#reader-form #phone").val(reader.phone);
        $("#reader-form #address").val(reader.address);
        $("#reader-form #email").val(reader.email);
        $("#reader-form #dob").val(reader.dob);

        //Them đánh dấu id giup phan biet save và update để xử lý dữ kiện
        $("#save-reader-btn").attr("reader-id", readerId);

        $("#reader-modal .modal-title").text("Cập nhật bạn đọc");

        // 4 - show modal lên
        $("#reader-modal").modal('show');
    });

    // lấy thông tin chi tiết 1 bạn đọc
    async function getReaderDetails(id) {
        let reader = null;
        await $.ajax({
            url: '/api/v1/readers/' + id,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                reader = data;
            },
            error: function (err) {
                alert(err.responseJSON.message);
            }
        });
        return reader;
    }

    // bắt sự kiện modal ẩn đi
    $('#reader-modal').on('hidden.bs.modal', function () {
        // reset dữ liệu trong form
        $("#reader-form").trigger("reset");
        $("#save-reader-btn").removeAttr("reader-id");
        $("#reader-modal .modal-title").text("Tạo mới bạn đọc");

        // reset validate
        readerValidator.resetForm();
        $("label.error").hide();
        $(".error").removeClass("error");
    });

});