// function getDogs(){
//     return fetch("https://dog.ceo/api/breeds/list/all").then(function(response) {
//         return response.json();
//     })
// }
// // Gọi API và nhận về kết quả
// // convert dữ liệu nhận được ra json

// function renderProducts(dogs) {
//     const dogListEl = document.querySelector(".dogs");
//     const html = dogs.map(function(dog)  {
//          const dogHTML = [
//             `<div>`,
//             `<div class="dog-info">`,
//             `<p class="dog-message">${dog.message}</p>`,`</div>`,
//             `</div>`,
//             `<div>` ,
//             `<button class="btn-get-sub-breed" data-product-id="${dog.message}">Get Sub Breed</button>` ,
//             `</div>` ,
//             `</div>`
//             `<div>`
//          ].join("");

//          return dogHTML;
//     })
//     .join("");

//     dogListEl.innerHTML = html;
// }

// function setupEventHandlet(){
//     document.querySelectorAll("button.btn-get-sub-breed")
//     .forEach(function(button) {
//         button.addEventListener("click" , function () {
//             const productID =  button.getAttribute("data-product-id");

//             alert(`Add product with ID : ${productID} to cart`)
//         })
//     })
// }
const btn = document.getElementById("btn");
const image = document.getElementById("image");
const select = document.getElementById("select");
const breedListEl = document.getElementById("breed-list");

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
  for (let breed in breeds) {
    const html = `
            <option value="${breed}">${breed}</option>
        `;
    breedListEl.innerHTML += html;
  }
}
// Xử lý sự kiện cho nút Fetch
// Thực hiện một hàm bất đồng bộ khi click để lấy ra ảnh sau đó gán link ảnh vào thẻ img
btn.addEventListener("click", async () => {
  let link = `https://dog.ceo/api/breed/${breedListEl.value}/images/random`;
  let res = await axios.get(link);
  image.src = res.data.message;
});
getBreedList();
