// Danh sách các sản phẩm có trong giỏ hàng
let products = [
    {
        name: "Iphone 13 Pro Max", // Tên sản phẩm
        price: 3000000, // Giá mỗi sản phẩm
        brand: "Apple", // Thương hiệu
        count: 2, // Số lượng sản phẩm trong giỏ hàng
    },
    
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,

    } ,
];


// 1. In ra thông tin các sản phẩm trong giỏ hàng theo cấu trúc sau
// Tên - Giá - Thương Hiệu - Số lượng
// Ví dụ : OPPO Find X3 Pro - 19500000 - OPPO - 3

function showInfo(products) {
    products.forEach(product => {
        console.log(`${product.name} - ${product.price} - ${product.brand} - ${product.count}`)
    });
} ;

showInfo(products);

// 2. Tính tổng tiền tất cả sản phẩm trong giỏ hàng
// Tổng tiền mỗi sản phẩm = price * count

function caculate(products) {

    let tongTien = 0;

    products.forEach(product => {
        tongTien += product.price*product.count;
    }) ;
    console.log(`Tổng số tiền tất cả sản phẩm trong giỏ hàng là : ` , tongTien);
    return tongTien;
} ;

caculate(products) ;

// 3. Tìm các sản phẩm của thuơng hiệu "Apple"

function findBrand(products) {
    products.forEach(product => {
        if(product.brand == 'Apple'){
            console.log(`Sản phẩm của thương hiệu Appple là : ${product.name}`)
        }
    });
};

findBrand(products);

// 4. Tìm các sản phẩm có giá > 20000000

function findPrice(products) {
    products.forEach(product => {
        if(product.price > 20000000){
            console.log(`Các sản phẩm có giá trên 20000000 là : ${product.name}`)
        }
    })
};

findPrice(products);

// 5. Tìm các sản phẩm có chữ "pro" trong tên (Không phân biệt hoa thường)

// Cách 1 :
function findPro(products) {
    return products.filter(product => product.name.toLowerCase().includes('pro'));
}

const productWithPro = findPro(products);
console.log(`Sản phẩm có chữ pro trong tên là : ` , productWithPro);

// Cách 2 :
function findPro1(products) {
    console.log(`Sản phẩm có chữ pro trong tên là : `);
    products.forEach(product =>{
        if(product.name.toLowerCase().includes('pro')){
            console.log(`${product.name}`);
        }
    })
}

findPro1(products);

// 6. Thêm 1 sản phẩm bất kỳ vào trong mảng product

function addProduct(products , product){
    products.push(product);
}

const newProduct = {
    name : 'Iphone 14 Pro Vip' ,
    price : 60000000 ,
    brand : 'Apple' ,
    count : 3 ,
}

addProduct(products,newProduct)
console.log(`Bảng sản phẩm mới là : ` , products)

// 7. Xóa tất cả sản phẩm của thương hiệu "Samsung" trong giỏ hàng

// C1 :
function removeByBrand(products , brandName){
    return products.filter(product => product.brand !== brandName)
}
const newProducts = removeByBrand(products , 'Samsung')
console.log(`Giỏ hàng mới là : ` , newProducts);

// C2 :
let newProducts1 = [];
products.forEach(product =>{
    if(product.brand !== 'Samsung'){
        newProducts1.push(product)
    }
})
console.log(`Giỏ hàng mới (cách 2) là : ` , newProducts1);

// 8. Sắp xếp giỏ hàng theo price tăng dần

products.sort((a,b) => a.price - b.price);
console.log(`Giỏ hàng sau khi sắp xếp theo price tăng dần là : ` , products)

// 9. Sắp xếp giỏ hàng theo count giảm dần

products.sort((a,b) => b.count - a.count);
console.log(`Giỏ hàng sau khi sắp xếp theo count giảm dần là : ` , products)

// 10. Lấy ra 2 sản phẩm bất kỳ trong giỏ hàng

function getRandomProduct(products , count) {
    let productRandom = [];
    let copyProducts = [...products];
    for (let i = 0; i < count; i++) {
        if(copyProducts.length == 0){
            break;
        }
        let randomIndex = Math.floor(Math.random()*copyProducts.length)
        productRandom.push(copyProducts[randomIndex]);
        copyProducts.splice(randomIndex,1);
    }
    return productRandom;
}

const result = getRandomProduct(products,2);
console.log(`2 sản phẩm bất kỳ trong giỏ hàng là : ` , result)
