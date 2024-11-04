$(document).ready(function (){

    let totalPage;
    let totalRecord;
    let paging;
    let pageIndex = 0;
    let pageSize = 10;

    function getReaderData(reader) {
        $.ajax({
            url: '/api/v2/readers',
            type: 'GET',
            data: {
                pageIndex: pageIndex,
                pageSize: pageSize,
                ...reader
            },
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                renderReaderTable(data);
            },
            error: function (data) {
                showToast(data.responseJSON.error, "error");
            }
        })
    }

    function renderReaderTable(data) {

        $("#reader-table tbody").empty();
        $("#reader-paging .pagination").empty();
        $(".total-record").empty();
        if (!data) {
            return;
        }

        const readers = data.data;
        totalPage = data.totalPage;
        totalRecord = data.totalRecord;
        paging = data.paging;
        pageIndex = paging.pageIndex;

        if (!readers || readers.length === 0) {
            return;
        }

        for (let i = 0; i < readers.length; i++) {
            const reader = readers[i];
            const tr = "<tr>" +
                "<td>" + reader.id + "</td>" +
                "<td>" + reader.name + "</td>" +
                "<td>" + reader.phone + "</td>" +
                "<td>" + reader.address + "</td>" +
                "<td>" + reader.dob + "</td>" +
                "<td>" + reader.email + "</td>" +
                "<td>" +
                "<span role=\"button\" class=\"text-primary btn-update\" id='" + reader.id + "'>\n" +
                "   <i class=\"fa-solid fa-pencil\"></i>\n" +
                "</span>\n" +
                "<span role=\"button\" class=\"text-danger btn-delete\">\n" +
                "    <i class=\"fa-solid fa-trash\"></i>\n" +
                "</span>" +
                "</td>" +
                "</tr>";
            $("#reader-table tbody").append(tr);
        }

        $("#reader-paging .pagination").append("<li class=\"page-item go-to-first-page\"><a class=\"page-link\" href=\"#\"><<</a></li>");
        $("#reader-paging .pagination").append("<li class=\"page-item previous-page\"><a class=\"page-link\" href=\"#\"><</a></li>");
        for (let i = 1; i <= totalPage; i++) {
            const page = "<li class='page-item " + (i === paging.pageIndex + 1 ? "active" : '') + "' page='" + (i - 1) + "'><a class='page-link' href='#'>" + i + "</a></li>";
            $("#reader-paging .pagination").append(page);
        }

        $("#reader-paging .pagination").append("<li class=\"page-item next-page\"><a class=\"page-link\" href=\"#\">></a></li>");
        $("#reader-paging .pagination").append("<li class=\"page-item go-to-last-page\"><a class=\"page-link\" href=\"#\">>></a></li>");

        $(".total-record").append("<span>Tong so ban ghi: " + totalRecord + "</span>")

        $(".page-item").click(function (event) {
            pageIndex = $(event.currentTarget).attr("page");
            if (!pageIndex) {
                return;
            }
            getReaderData({});
        });

        $(".go-to-first-page").click(function () {
            pageIndex = 0;
            getReaderData({});
        });

        $(".go-to-last-page").click(function () {
            pageIndex = totalPage - 1;
            getReaderData({});
        });

        $(".previous-page").click(function () {
            if (paging.pageIndex === 0) {
                return;
            }
            pageIndex = paging.pageIndex - 1;
            getReaderData({});
        });

        $(".next-page").click(function () {
            if (paging.pageIndex === totalPage - 1) {
                return;
            }
            pageIndex = paging.pageIndex + 1;
            getReaderData({});
        });
    }

    getReaderData({});

    $("#search-reader").click(function () {
        // lay du lieu tu form
        const formValues = $("#search-reader-form").serializeArray();
        const reader = {};
        formValues.forEach(input => {
            reader[input.name] = input.value;
        });

        // goi seach
        getReaderData(reader);
    });

    $("#reset-search-reader").click(function () {
        $("#search-reader-form")[0].reset();
        getReaderData({});
    });

    /// code xử lý create update, delete, ....

});