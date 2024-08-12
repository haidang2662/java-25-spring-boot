1 . Bean là gì ?
Bean được coi là thành phần xương sống của ứng dụng Spring và 
đối tượng được khởi tạo lắp ráp , quẻn lý bởi Sprign IoC 
container .
2 . Tạo ra Bean ntn ?
- C1 : Sử dụng các annotation để đánh dấu lên class : @Component ,
@Controller , @RestController , @Service ....
- C2 : Sứ dụng annotation @Bean trên method (method level ) trong 
class @Confirgation
3 . Su dụng Bean ntn ?
- Bean thường đc sử dụng trong 1 bean khác (dependency) (A -> B)
  (B nằm trong A , phụ thuộc vào A )
- VD : Classroom {
    SinhVien
    GiangVien
    TuVanVIen
} 
Classroom là A , những cái ở trong là B . A bắt buộc phải là Bean
 3 cách sử dụng Bean :
- Field-based Ịnection
- Constructor-based Ịnection
- Setter-based Injection