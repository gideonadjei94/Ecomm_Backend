package com.EcommBackend.demo.Service;

import com.EcommBackend.demo.Model.Product;
import com.EcommBackend.demo.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return repo.findById(id);
    }
}
