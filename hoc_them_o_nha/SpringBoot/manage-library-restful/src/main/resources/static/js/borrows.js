

$(document).ready(function () {

    //  Xử lý luu lươt muon lay du lieu tu tren web
    $("#save-borrow-btn").click(function () {
        // 1 : Lay thong tin nguoi dung o form
        const formData = $("#borrow-form").serializeArray();
        console.log(formData);
        const borrow = {};
        for (let i = 0; i < formData.length; i++) {
            borrow[formData[i].name] = formData[i].value;
            // reader["name"] = 'dang hai';
            // reader["phone"] = '0975186411';
        }
        console.log(borrow);

        // 2 : Gọi lên phía java (BookResource) để lưu
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
                alert(err.responseJSON.message);
            }
        });
    });

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
                alert(err.responseJSON.message);
            }
        });
    });

    //Xử lý update borrow
    $(".update-btn").click(async function (event){
        //1 . Lấy ra id của borrow
        const borrowId = $(event.currentTarget).attr("borrow-id");
        console.log(borrowId);
        //2 . Lay ra tong tin chi tiet
        const borrow = await getBorrowDetails(borrowId);
        if(borrow == null){
            return;
        }

        // Gọi thêm API lấy chi tiết sách và bạn đọc
        const book = await getBookDetails(borrow.bookId);
        console.log(book);
        const reader = await getReaderDetails(borrow.readerId);
        console.log(reader);
        // 3 . Dien thong tin cua sach vao modal :
        $("#borrow-form #bookId").val(borrow.bookId);
        $("#bookName").text(book.name); // Hiển thị tên sách dưới select
        $("#borrow-form #readerId").val(borrow.readerId);
        $("#readerName").text(reader.name); // Hiển thị tên bạn đọc
        $("#borrow-form #quantity").val(borrow.quantity);
        $("#borrow-form #expectedReturnDate").val(borrow.expectedReturnDate);

        // Them danh dau id giup phan biet save va update

        $("#save-borrow-btn").attr("borrow-id" , borrowId);

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
                alert(err.responseJSON.message);
            }
        });
        return book;
    }

// Lấy thông tin chi tiết của bạn đọc dựa trên readerId
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
                alert(err.responseJSON.message);
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
                alert(err.responseJSON.message);
            }
        });
        console.log(borrow);
        return borrow;
    }

});