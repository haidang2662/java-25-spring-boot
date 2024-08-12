// Bài 1 :
function caculate(number) {
    if (typeof number !== 'number' || number <= 0 || !Number.isInteger(number)){
        throw new Error("Dữ liệu đầu vào không hợp lệ");
    }
    let a = 1 ;
    for(let i = number ; i > 1 ; i--) {
        a *= i ;
    }
    return a;
}
// Bài 2 :
function reverse(str) {
    if (typeof str !== 'string') {
        throw new Error ('Dữ liệu đầu vào phải là chuỗi string ');
    }
    let reverseStr = '';
    for (i = str.length - 1 ; i >= 0 ; i--){
        reverseStr += str[i];
    }
    return reverseStr;
}
// Bài 3 :
function message(str) {
    if (typeof str !== 'string'){
        throw new Error ('Dữ liệu đầu vào phải là chuỗi String ')
    }
    switch(str){
        case 'VN' : {
            console.log ("Xin chào") ;
            break;
        }
        case 'EL' : {
            console.log('Hello');
            break;
        }
        case 'ESP' : {
            console.log ('HOLA');
            break;
        }
        case 'INDIA' : {
            console.log ('Namaste')
        }
    }
}
// Bài 4 :
function split(str) {

    if (typeof str !== 'string' || str.length <= 10){
        throw new Error ('Dữ liệu đầu vào phải là chuỗi String và có nhiều hơn 10 ký tự')
    }

    let newStr = '';
    for(let i = 0 ; i <=10 ; i++){
        newStr += str[i];
    }
    newStr = newStr + '...';
    for(i = 11 ; i <= str.length -1 ; i++){
        newStr += str[i];
    }
    return newStr;
}