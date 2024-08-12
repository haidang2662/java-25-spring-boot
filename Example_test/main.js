const btn = document.getElementById('btn');
const image = document.getElementById('image');
const select = document.getElementById('breed-list');

// Vừa load trang phải gọi API để render danh sách breed
// API : https://dog.ceo/api/breeds/list/all

async function getBreedList() {
    // Gọi API để lấy danh sách giống loài
    let res = await axios.get("https://dog.ceo/api/breeds/list/all");

    // Sau khi có data thì hiển thị kết quả trên giao diện
    renderBreed(res.data.message);
}

function renderBreed(breeds) {
    // Duyệt qua object breeds -> tạo thẻ option -> gắn vào DOM

    // Cách 1: Sử dụng for ... in
    for(const breed in breeds){
        const option = document.createElement('option');
        option.textContent = breed;
        select.appendChild(option);
    }
}

// Gọi hàm getBreedList để load danh sách giống chó khi trang được tải
getBreedList();

btn.addEventListener('click', async () => {
    const breed = select.value;
    const res = await axios.get(`https://dog.ceo/api/breed/${breed}/images/random`);
    image.src = res.data.message;
});

