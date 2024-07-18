// Khai báo array rỗng
let arr = [];

// Khai báo array
let number = [];

// Gán giá trị cho các phần tử của array thông qua chỉ số
number[0] = 1;
number[1] = "Bùi Hiên";
number[2] = true;

// Khai báo và khởi tạo giá trị cho array
let myArr = [1, 2, 3, 4, true, false, "Nguyễn Văn A"];
// Bài 1: Viết function tìm số lớn nhất trong mảng
// C1 : Sắp xếp
// C2 : so sánh --> gán 
const findMax = (arr) => {
    arr.sort((a,b) => b - a);
    return arr[0];
}

const findMax2 = (arr) => {
    let max = arr[0];
    arr.forEach((value) => {
        if(value > max ){
            max = value ;
        }
    });
    return max ;
}

const findMax3 = (arr) => {
    return Math.max(...arr) ; // cú pháp ES6 : Spread operator
}

console.log(findMax([2 ,3 , 5 , 6 , 4 , 9])) ;
console.log(findMax2([2 ,3 , 5 , 6 , 4 , 9])) ;
console.log(findMax3([2 ,3 , 5 , 6 , 4 , 9])) ;
// Bài 3: Viết function cho phép truyền vào 1 mảng các số, kết quả trả về là 1 mảng mới với các số là số dư 
// tương ứng khi chia mảng cũ cho 2
// Ví dụ : [4,2,5,6,2,7] => [0,0,1,0,0,1]
// tạo mảng rỗng chứa kết quả
// vòng for -> chia du cho 2 -> push vao mang ket qua
const modulo2 = (arr) => {
    let rs = [];
    arr.forEach((value) => {
        rs.push(value % 2);
    })
    return rs ;
}

const modulo3 = (arr) => {
    // const result = arr.map(e => e % 2)
    // return result

    return arr.map((value) => value % 2);
}

// Bài 5: Viết function truyền vào 1 chuỗi, hãy sao chép đó chuỗi lên 10 lần, ngăn cách nhau bởi dấu gạch ngang (Sử dụng array để làm)

// Ví dụ: repeatString(‘a’) => Kết quả trả về là ‘a-a-a-a-a-a-a-a-a-a’
function repeat(str){
    const repeatStr = Array(10).fill(str);
    const result = repeatStr.join('-');
    return result ;
}