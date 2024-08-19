package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.example.demo.database.ProductDb.products;

@Controller
@RequestMapping("/products")

public class ProductController {
    @Autowired
    private ProductService productService;

    // 1 : In ra danh sách sản phẩm đang có
    @GetMapping
    public ResponseEntity<List<Product>> getAllBooks() {
        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }

    // 2 : Tìm sản phẩm theo id

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.findProductById(id);
        if(product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product , HttpStatus.OK);
    }

    // 3 : Tìm sản phẩm theo tên

    @GetMapping("/searchByName/{keyword}")
    public ResponseEntity<List<Product>> searchByName(@PathVariable String keyword) {
        List<Product> result = productService.findProductsByName(keyword);
        return ResponseEntity.ok(result);
    }

    //  4 : Tìm kiếm sản phẩm trong khoảng giá :

    @GetMapping("/searchByPrice/fromPrice/{fromPrice}/toPrice/{toYear}")
    public ResponseEntity<List<Product>> findProductInRangePrice(@PathVariable int fromPrice, @PathVariable int toPrice) {
        List<Product> result = productService.findProductsBetweenPrice(fromPrice,toPrice);
        return ResponseEntity.ok(result);
    }
}
