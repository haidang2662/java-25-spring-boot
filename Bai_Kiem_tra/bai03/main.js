// Sự kiện chờ khi toàn bộ tài liệu HTML đã được tải xong
document.addEventListener('DOMContentLoaded', function() {
  
    const typeHeading = document.getElementById('typeHeading');
    const postsBtn = document.getElementById('postsBtn');
    const albumsBtn = document.getElementById('albumsBtn');
    const photosBtn = document.getElementById('photosBtn');
    const dataList = document.getElementById('dataList');

    // Hàm lấy dữ liệu từ URL API và trả về dữ liệu JSON
    async function fetchData(url) {
        const response = await fetch(url); // Gọi API
        const data = await response.json(); // Chuyển đổi dữ liệu nhận được thành JSON
        return data; // Trả về dữ liệu JSON
    }

    // Hàm hiển thị dữ liệu nhận được từ API vào phần tử ul
    function displayData(data, key) {
        dataList.innerHTML = ''; // Xóa nội dung cũ trong ul
        data.forEach(item => { // Duyệt qua mỗi phần tử trong dữ liệu
            const li = document.createElement('li'); // Tạo phần tử li mới
            li.textContent = item[key]; // Đặt nội dung của li là giá trị của key (title)
            dataList.appendChild(li); // Thêm li vào ul
        });
    }

    // Hàm làm nổi bật nút đang được chọn
    function setActiveButton(activeBtn) {
        const buttons = [postsBtn, albumsBtn, photosBtn]; // Danh sách các nút
        buttons.forEach(button => button.classList.remove('active')); // Xóa class 'active' khỏi tất cả các nút
        activeBtn.classList.add('active'); // Thêm class 'active' vào nút được chọn
    }

    // Hàm cập nhật tiêu đề hiển thị loại dữ liệu
    function setTypeHeading(type) {
        typeHeading.textContent = `Type: ${type}`; // Cập nhật nội dung của thẻ h1
    }

    // Thêm sự kiện 'click' vào nút Posts
    postsBtn.addEventListener('click', async () => {
        setActiveButton(postsBtn); // Làm nổi bật nút Posts
        setTypeHeading('Posts'); // Cập nhật tiêu đề
        const data = await fetchData('https://jsonplaceholder.typicode.com/posts'); // Gọi API Posts
        displayData(data, 'title'); // Hiển thị dữ liệu
    });

    // Thêm sự kiện 'click' vào nút Albums
    albumsBtn.addEventListener('click', async () => {
        setActiveButton(albumsBtn); // Làm nổi bật nút Albums
        setTypeHeading('Albums'); // Cập nhật tiêu đề
        const data = await fetchData('https://jsonplaceholder.typicode.com/albums'); // Gọi API Albums
        displayData(data, 'title'); // Hiển thị dữ liệu
    });

    // Thêm sự kiện 'click' vào nút Photos
    photosBtn.addEventListener('click', async () => {
        setActiveButton(photosBtn); // Làm nổi bật nút Photos
        setTypeHeading('Photos'); // Cập nhật tiêu đề
        const data = await fetchData('https://jsonplaceholder.typicode.com/photos'); // Gọi API Photos
        displayData(data, 'title'); // Hiển thị dữ liệu
    });

    // Mặc định hiển thị danh sách Posts khi trang được tải
    (async function() {
        const data = await fetchData('https://jsonplaceholder.typicode.com/posts'); // Gọi API Posts
        displayData(data, 'title'); // Hiển thị dữ liệu
    })();
});
