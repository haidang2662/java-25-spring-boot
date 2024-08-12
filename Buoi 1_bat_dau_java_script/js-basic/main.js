console.log('hello word')
// template string E56
let name = "Bùi Hiên"
let year = 1997

console.log(`Xin chào các bạn, mình tên là ${name}. Năm nay ${2022 - year} tuổi`);

// Khong su dung template string

// Function 
// tinh tong 2 so : a + b
// C1 : regular function
function sum (a,b){
    return a + b ;
}

// c2 : function expresstion 
let sum1 = function(a,b){
    return a + b ;
}
// c3 : arrow function (ES56) - gan giong Lamda function java 8
let sum2 = (a,b) => {
    return a + b ;
}

console.log(sum(10,21))
console.log(sum1(11,21))
console.log(sum2(12,21))


