package com.example.demo.dao.iplm;

import com.example.demo.dao.ProductDao;
import com.example.demo.database.ProductDb;
import com.example.demo.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.database.ProductDb.products;

@Repository
public class ProductDaoIplm implements ProductDao {

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(int id) {
        for(Product product : products){
            if(product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> findByNameContainIgnoreCase(String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if(product.getName().toLowerCase().contains(keyword.toLowerCase())){
                result.add(product);
            }
        }
        return result;
    }
}
