// // Phần 1 :
// console.log("Trước promise");

// const promise = new Promise((resole , reject) => {
//     console.log("Trong promise");
//     let data ;

//     setTimeout(() => {
//         console.log("Hoàn thành promise")
//         data = "Dữ liệu" ;

//         // resole(data) ; // công việc hoàn thành
//         reject(new Error("Không thể lấy được dữ liệu")) ;
//     } , 3000) // giả sử mất 3s để query dữ liệu trong csdl
// });

// console.log("Sau promise");

// console.log(promise) ;

// promise.then((data) => {
//     console.log("trong then");
//     console.log("Tôi nhận được data" ,data);
// }).catch(reason => {
//     console.error(reason);
// });

// console.log("Sau then") ;

// Phần 2 :

// function orderCoffee(customer)  {
//     console.log("Nhận order của khách hàng thứ : " , customer);

//     return new Promise(function(resolve , reject) {
//         console.log("Làm coffee cho khách hàng : " , customer);

//         setTimeout(function() {
//             console.log("Làm xong coffee cho khách hàng" , customer);

//             resolve(); // thông báo cho khách hàng
//         } ,Math.random() * 5000)
//     })
// }

// for (let i = 1; i <= 10 ; i++){
//  orderCoffee(i).then(function() {
//     console.log(`Khách hàng ${i} nhận đồ uống`);
//  })
//     .then(function () {
//         console.log("Uống coffee");
//     })
//     .then(function () {
//         console.log("order cốc nữa")
//     })
// }

// Phần 3 :
// Application Programming Interface
// Giao diện lập trình ứng dụng ==> bộ các quy tắc để cho phép các ứng dụng hệ thống giao tiếp / trao đổi và làm việc
// với nhau .

// Các điểm truy cập cho phép các ứng dụng Client giao tiếp và trap đổi với Server
// Phổ biến nhất : HTTP ==> giao thức phổ biến để truyền tải dữ liệu giữa client và server 

// Phần 4 :
// https://dummyjson.com/products

function getProducts() {
    return fetch("https://dummyjson.com/products").then(function(response) {
        return response.json() ; // Chuyển đổi phản hồi từ API từ định dạng JSON sang đối tượng JavaScript.
    })
}// Gọi API và nhận về kết quả
// convert dữ liệu nhân được từ json và chuyển sang javascript

function renderProducts(products) {
    const productListEl = document.querySelector(".products");
    const html = products.map(function (product)  {
        const productHtml = [
            `<div>`,
            `<div class="product-image">`,
            `<img src="${product.thumbnail}" alt="${product.title}" />`,
            `</div>`,
            `<div class="product-info">`,
            `<h3 class="product-title">${product.title}</h3>`,
            `<p class="product-price">${product.price}</p>`,`</div>`,
            `</div>`,
            `<div>` ,
            `<button class="btn-add-to-cart" data-product-id="${product.id}">Add to cart</button>` ,
            `</div>` ,
            `</div>`
          ].join("");

          return productHtml;
    })
    .join("");

    productListEl.innerHTML = html;
}
function setupEventHandlet(){
    document.querySelectorAll("button.btn-add-to-cart")
    .forEach(function(button) {
        button.addEventListener("click" , function () {
            const productID =  button.getAttribute("data-product-id");

            alert(`Add product with ID : ${productID} to cart`)
        })
    })
}

function main() {
    getProducts()
    .then(function(data) {
        renderProducts(data.products);
    })
    .then(function() {
        setupEventHandlet();
    })
}

main();

