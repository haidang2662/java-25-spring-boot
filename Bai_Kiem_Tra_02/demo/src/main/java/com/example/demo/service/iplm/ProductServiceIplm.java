package com.example.demo.service.iplm;

import com.example.demo.dao.ProductDao;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceIplm implements ProductService {

//    @Qualifier("ProductDaoIplm1")
//    @Autowired

    @Resource(name = "ProductDaoIplm1")
    ProductDao productDao;

    // autowire bằng constructor
//    public ProductServiceIplm(ProductDao productDao) {
//        this.productDao = productDao;
//    }

    @PreDestroy
    public void readDataFromDb() {
        System.out.println("1111111111");
    }

    @Override
    public List<Product> getAllProduct() {
        return productDao.findAll();
    }

    @Override
    public Product findProductById(int id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product> findProductsByName(String keyword) {
        return productDao.findByNameContainIgnoreCase(keyword);
    }

    @Override
    public List<Product> findProductsBetweenPrice(int starPrice, int endPrice) {
        List<Product> products = productDao.findAll();
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() >= starPrice && product.getPrice() <= endPrice) {
                result.add(product);
            }
        }
        return result;
    }
}
