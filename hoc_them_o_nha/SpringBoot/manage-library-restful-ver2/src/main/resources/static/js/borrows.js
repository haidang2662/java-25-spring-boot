$(document).ready(function () {

    async function initData() {
        $("#borrow-form #bookId").empty();
        $("#borrow-form #readerId").empty();

        const books = await getBooks();
        // đổ dữ liệu (render dữ liệu vào form cho mượn sách) -> jQuery
        for (let i = 0; i < books.length; i++) {
            const option = `<option value="${books[i].id}">${books[i].name}</option>`;
            $("#borrow-form #bookId").append($(option));
        }

        const readers = await getReaders();
        for (let i = 0; i < readers.length; i++) {
            const option = `<option value="${readers[i].id}">${readers[i].name}</option>`;
            $("#borrow-form #readerId").append($(option));
        }
    }

    initData();

    // lấy danh sách tất cả quyển sách
    async function getBooks() {
        let books = null;
        await $.ajax({
            url: '/api/v1/books',
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                books = data;
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
        return books;
    }

    // lấy danh sách tất cả bạn đọc
    async function getReaders() {
        let readers = null;
        await $.ajax({
            url: '/api/v1/readers',
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                readers = data;
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
        return readers;
    }

    // Thêm custom rule để kiểm tra ngày phải là tương lai
    $.validator.addMethod("futureYear", function (value, element) {

        // Lấy ngày hiện tại
        const currentDate = new Date();

        // Lấy ngày nhập vào từ form (giả định rằng giá trị có định dạng yyyy-MM-dd)
        const inputDate = new Date(value);

        // So sánh năm
        if (inputDate.getFullYear() < currentDate.getFullYear()) {
            return false;
        }

        // So sánh tháng nếu năm nhập vào bằng năm hiện tại
        if (inputDate.getFullYear() === currentDate.getFullYear()) {
            if (inputDate.getMonth() < currentDate.getMonth()) {
                return false;
            }

            // So sánh ngày nếu tháng nhập vào bằng tháng hiện tại
            if (inputDate.getMonth() === currentDate.getMonth()) {
                if (inputDate.getDate() <= currentDate.getDate()) {
                    return false;
                }
            }
        }

        // Nếu ngày nhập vào lớn hơn ngày hiện tại, trả về true
        return true;
    }, "Ngày phải là ngày trong tương lai.");

    // Xử lý validate :
    const borrowValidator = $("#borrow-form").validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            "bookId": {
                required: true,
                min: 1
            },
            "readerId": {
                required: true,
                min: 1
            },
            "quantity": {
                required: true,
                min: 1,
                max: 5
            },
            "expectedReturnDate": {
                required: true,
                futureYear: true
            }
        },
        messages: {
            "bookId": {
                required: "Id của đầu sách là bắt buộc",
                min: "Id của đầu sách không được âm"
            },
            "readerId": {
                required: "Id của bạn đọc là bắt buộc",
                min: "Id của bạn đọc không được âm"
            },
            "quantity": {
                required: "Số lượng mượn là bắt buộc",
                min: "Số lượng mượn phải tối thiểu bằng 1",
                max: "Số lượng mượn không được vượt quá 5"
            },
            "expectedReturnDate": {
                required: "ngày trả dự kiến là bắt buộc.",
                futureYear: "ngày trả dự kiến phải là ngày trong tương lai"
            }
        }
    });

    // Xử lý luu lươt muon lay du lieu tu tren web
    $("#save-borrow-btn").click(function (event) {
        //kiem tra xem tao moi hay update
        const borrowId = $(event.currentTarget).attr("borrow-id");
        if (borrowId) {
            updateBorrow(borrowId);
        } else {
            createBorrow();
        }
    });

    function updateBorrow(borrowId) {
        const isValidForm = $("#borrow-form").valid();
        if (!isValidForm) {
            return;
        }
        const formData = $("#borrow-form").serializeArray();
        const borrow = {};
        for (let i = 0; i < formData.length; i++) {
            borrow[formData[i].name] = formData[i].value;
        }

        $.ajax({
            url: '/api/v1/borrows/' + borrowId,
            type: 'PUT',
            data: JSON.stringify(borrow),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Cập nhật lượt mượn thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
    }

    function createBorrow() {
        const isValidForm = $("#borrow-form").valid();
        if (!isValidForm) {
            return;
        }
        const formData = $("#borrow-form").serializeArray();
        const borrow = {};
        for (let i = 0; i < formData.length; i++) {
            borrow[formData[i].name] = formData[i].value;
        }

        $.ajax({
            url: '/api/v1/borrows',
            type: 'POST',
            data: JSON.stringify(borrow),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Tạo mới lượt mượn thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
    }

    // xử lý xóa luot muon
    $(".delete-btn").click(function (event) {
        const confirmResult = confirm("Bạn có chắc chắn muốn xóa luot muon này?");
        if (!confirmResult) {
            return;
        }

        const borrowId = $(event.currentTarget).attr("borrow-id");

        // xóa
        $.ajax({
            url: '/api/v1/borrows/' + borrowId,
            type: 'DELETE',
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("Xóa lượt mượn thành công");
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
    });

    //Xử lý update borrow
    $(".update-btn").click(async function (event) {
        await initData();

        //1 . Lấy ra id của borrow
        const borrowId = $(event.currentTarget).attr("borrow-id");
        //2 . Lay ra tong tin chi tiet
        const borrow = await getBorrowDetails(borrowId);
        if (borrow == null) {
            return;
        }

        // Gọi thêm API lấy chi tiết sách và bạn đọc
        // const book = await getBookDetails(borrow.bookId);
        // console.log(book);
        // const reader = await getReaderDetails(borrow.readerId);
        // console.log(reader);

        // 3 . Dien thong tin cua sach vao modal :
        $("#borrow-form #bookId").val(borrow.book.id);
        // $("#bookName").text(book.name); // Hiển thị tên sách dưới select
        $("#borrow-form #readerId").val(borrow.reader.id);
        // $("#readerName").text(reader.name); // Hiển thị tên bạn đọc
        $("#borrow-form #quantity").val(borrow.quantity);
        $("#borrow-form #expectedReturnDate").val(borrow.expectedReturnDate);

        // Them danh dau id giup phan biet save va update

        $("#save-borrow-btn").attr("borrow-id", borrowId);

        $("#borrow-modal .modal-title").text("Cập nhật lượt mượn");

        // 4 : show modal lên
        $("#borrow-modal").modal('show');
    })

    async function getBookDetails(bookId) {
        let book = null;
        await $.ajax({
            url: '/api/v1/books/' + bookId,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                book = data;
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
        return book;
    }

    async function getReaderDetails(readerId) {
        let reader = null;
        await $.ajax({
            url: '/api/v1/readers/' + readerId,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                reader = data;
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
        return reader;
    }

    async function getBorrowDetails(borrowId) {
        let borrow = null;
        await $.ajax({
            url: '/api/v1/borrows/' + borrowId,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                borrow = data;
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
        return borrow;
    }

    // bắt sự kiện modal ẩn đi
    $('#borrow-modal').on('hidden.bs.modal', function () {
        // reset dữ liệu trong form
        $("#borrow-form").trigger("reset");
        $("#save-borrow-btn").removeAttr("borrow-id");
        $("#borrow-modal .modal-title").text("Tạo mới lượt mượn : ");

        // reset validate
        borrowValidator.resetForm();
        $("label.error").hide();
        $(".error").removeClass("error");
    });

    // bắt sự kiện mở modal khi bấm Tạo mới
    $("#open-borrow-modal").click(async () => {
        initData();
        $('#borrow-modal').modal('show');
    });

});