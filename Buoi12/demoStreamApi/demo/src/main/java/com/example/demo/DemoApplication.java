package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// functional interface là interface chỉ chứa 1 phương thức abstract duy nhất
// Lamda expression được sử dụng để triển khai functional interface
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		// C1 : sd implement Greetinh
		Greeting vietNam = new VietNam();
		vietNam.sayHello("Huy");

		// C2 : sd anonymous class
		Greeting english = new Greeting() {
			@Override
			public void sayHello(String name) {
				System.out.println("Hello" + name);
			}
		};
		english.sayHello("An");

		// C3 sd lamda expression :

		Greeting japan = (name) -> {
			System.out.println("Chào " + name);
		};
		japan.sayHello("Tuan");

		Caculator sum = (a , b) ->{
			return a+b;
		};
		System.out.println("Sum = " +sum.caculate(10,20));

		Caculator minus = (a , b) ->{
			return a-b;
		};
		System.out.println("Minus = " +minus.caculate(10,20));

		//Thao tac voi List
//		List<Integer> numbers = new ArrayList<>(List.of(3,5,20,12,48));
//		numbers.forEach((number) ->System.out.println(number));
//
//		System.out.println("Xoa so le");
//		numbers.removeIf((number) -> number % 2 == 1);
//		numbers.forEach((number) -> System.out.println(number));
//
//		System.out.println("Sap xep ");
//		numbers.sort((a , b) -> a - b);
//		numbers.forEach((number) -> System.out.println(number));

		List<Integer> numbers = new ArrayList<>(List.of(3,5,20,12,48));

		int total =  numbers.stream()
				.map(number -> number * 2)
				.reduce(0 , (a , b) -> a+b);
		System.out.println("Tong bang = " + total);

		int max = numbers.stream()
				.filter(number -> number % 2 == 0)
				.max((a , b) -> a -b)
				.orElse(0);
		System.out.println("Max = " + max);

		int max1 = numbers.stream()
				.filter(number -> number % 2 == 0)
				.mapToInt(number -> number)
				.max()
				.orElse(0);
		System.out.println("Max1 = " + max1);

		List<Integer> numbers1 = new ArrayList<>(List.of(10, 5, 3, 12, 6, 7, 5, 3));

//		7. Lấy danh sách các phần tử không trùng nhau (distinct)
		List<Integer> result7 = numbers1
				.stream()
				.distinct()
				.collect(Collectors.toList());
		System.out.println("Cac phan tu khong trung nhau la : ");
		System.out.println(result7);
//		8. Lấy 5 phần tử đầu tiên trong mảng (limit)
		List<Integer> result8 = numbers1
				.stream()
				.limit(5)
				.collect(Collectors.toList());

		System.out.println("5 phan tu dau tien la " + result8);
//		9. Lấy phần tử từ thứ 3 -> thứ 5 (limit + skip)
		List<Integer> result9 = numbers1
				.stream()
				.limit(5)
				.skip(2)
				.collect(Collectors.toList());
		System.out.println("3 phan tu tu thu 3 den thu 5 la : " +result9);
//		10. Lấy phần tử đầu tiên > 5 (findFirst)
		Optional<Integer> result10 = numbers1
				.stream()
				.filter(num -> num > 5)
				.findFirst();

		result10.ifPresent(System.out::println);

//		11. Kiểm tra xem list có phải là list chẵn hay không (allMatch)
		boolean result11 = numbers1
				.stream()
				.allMatch(num -> num % 2 == 0);
		if(result11){
			System.out.println("Tat ca phan tu trong danh sach la so chan");
		} else {
			System.out.println("Phan tu co so le");
		}

//		12. Kiểm tra xem list có phần tử > 10 hay không(anyMatch)

		boolean result12 = numbers1
				.stream()
				.anyMatch(num -> num > 10);
		if(result12){
			System.out.println("Co phan tu lon hon 10");
		} else {
			System.out.println("Khong co phan tu lon hon 10");
		}

//		13. Có bao nhiêu phần tử > 5(count)

//		14. Nhân đôi các phần tủ trong list và trả về list mới (map)
//		15. Kiểm tra xem list không chứa giá trị nào = 8 hay không (noneMatch)
	}

}
