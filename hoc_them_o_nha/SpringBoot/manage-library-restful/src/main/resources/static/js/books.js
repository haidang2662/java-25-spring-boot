

$(document).ready(function () {

    // Thêm custom rule để kiểm tra năm phải là quá khứ
    $.validator.addMethod("pastYear", function(value, element) {

        // Lấy ngày hiện tại
        const currentDate = new Date();

        // Lấy ngày nhập vào từ form (giả định rằng giá trị có định dạng yyyy-MM-dd)
        const inputDate = new Date(value);

        // So sánh năm
        if (inputDate.getFullYear() > currentDate.getFullYear()) {
            return false;
        }

        // So sánh tháng nếu năm nhập vào bằng năm hiện tại
        if (inputDate.getFullYear() === currentDate.getFullYear()) {
            if (inputDate.getMonth() > currentDate.getMonth()) {
                return false;
            }

            // So sánh ngày nếu tháng nhập vào bằng tháng hiện tại
            if (inputDate.getMonth() === currentDate.getMonth()) {
                if (inputDate.getDate() > currentDate.getDate()) {
                    return false;
                }
            }
        }

        // Nếu vượt qua tất cả các kiểm tra, ngày là hợp lệ (trong quá khứ hoặc hiện tại)
        return true;
    }, "Năm xuất bản phải là thời điểm trong quá khứ hoặc  hiện tại.");

    // Xử lý validate :
    const bookValidator = $("#book-form").validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            "name": {
                required: true,
                maxlength: 200
            },
            "price": {
                required: true,
                min: 1
            },
            "publisher": {
                required: true,
                maxlength: 200
            },
            "publishedYear": {
                required: true,
                pastYear : true
            }
        },
        messages: {
            "name": {
                required: "Tên sách là bắt buộc.",
                maxlength: "Tên sách không được vượt quá 200 ký tự."
            },
            "price": {
                required: "Giá sách là bắt buộc.",
                min: "Giá sách phải lớn hơn hoặc bằng 1."
            },
            "publisher": {
                required: "Nhà xuất bản là bắt buộc.",
                maxlength: "Tên nhà xuất bản không được vượt quá 200 ký tự."
            },
            "publishedYear": {
                required: "Năm xuất bản là bắt buộc.",
                pastYear: "Năm xuất bản phải là năm trong quá khứ hoặc năm hiện tại."
            }
        }
    });

    //  Xử lý luu dau sach
    $("#save-book-btn").click(function (event){
        // kiem tra xem bam tao moi hay cap nhat
        const bookId = $(event.currentTarget).attr("book-id");
        if(bookId) {
            updateBook(bookId);
        } else {
            createBook();
        }
    });

    function updateBook(bookId) {
        const isValidForm = $("#book-form").valid();
        if(!isValidForm){
            return;
        }
        const formData = $("#book-form").serializeArray();
        const book ={};
        for (let i = 0; i < formData.length; i++) {
            book[formData[i].name] = formData[i].value;
        }

        $.ajax({
            url: '/api/v1/books/' + bookId,
            type: 'PUT',
            data: JSON.stringify(book),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Cập nhật đầu sách thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.message);
            }
        });
    }

    function createBook() {
        const isValidForm = $("#book-form").valid();
        if(!isValidForm){
            return;
        }

        //1 : Lay thong tin nguoi dung dang nhap p form

        const formData = $("#book-form").serializeArray();
        const book = {};
        for (let i = 0; i < formData.length; i++) {
            book[formData[i].name] = formData[i].value;
        }
        //2 : Goi ve phia java (BookResource) de luu book --> dung ajax

        $.ajax({
            url: '/api/v1/books',
            type: 'POST',
            data: JSON.stringify(book),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Tạo mới đầu sách thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.message);
            }
        });

    }

    // xử lý xóa dau sach
    $(".delete-btn").click(function (event) {
        const confirmResult = confirm("Bạn có chắc chắn muốn xóa đầu sách này?");
        if (!confirmResult) {
            return;
        }

        const bookID = $(event.currentTarget).attr("book-id");

        // xóa
        $.ajax({
            url: '/api/v1/books/' + bookID,
            type: 'DELETE',
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Xóa đầu sách thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.message); // Todo sao đoạn này báo chút lỗi mà vẫn chạy bt
            }
        });
    });



    // xử lý nút update
    $(".update-btn").click(async function (event) {
        // 1 . Lay ra id cua sach
        const bookId = $(event.currentTarget).attr("book-id");
        // 2 . Lay thong tin cua sach -> goi API lay thong tin chi tiet
        const book = await getBookDetails(bookId);
        if (book == null) {
            return;
        }

        // 3 . Dien thong tin cua dau sach vao form cua modal
        $("#book-form #name").val(book.name);
        $("#book-form #price").val(book.price);
        $("#book-form #publisher").val(book.publisher);
        $("#book-form #publishedYear").val(book.publishedYear);

        //Them đánh dấu id giup phan biet save và update để xử lý dữ kiện
        $("#save-book-btn").attr("book-id" , bookId);

        $("#book-modal .modal-title").text("Cập nhât đầu sách");

        // 4 . Show modal len :
        $("#book-modal").modal('show');
    });

    // Lay thong tin chi tiet cua 1 dau sach
    async function getBookDetails(id) {
        let book = null;
        await $.ajax({
            url: '/api/v1/books/' + id,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                book = data;
            },
            error: function (err) {
                alert(err.responseJSON.message);
            }
        });
        return book;
    }

    // bắt sự kiện modal ẩn đi
    $('#book-modal').on('hidden.bs.modal', function () {
        // reset dữ liệu trong form
        $("#book-form").trigger("reset");
        $("#save-book-btn").removeAttr("book-id");
        $("#book-modal .modal-title").text("Tạo mới đầu sách : ");

        // reset validate
        bookValidator.resetForm();
        $("label.error").hide();
        $(".error").removeClass("error");
    });
});