package com.EcommBackend.demo.Controller;

import com.EcommBackend.demo.Model.Product;
import com.EcommBackend.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Optional<Product> getProduct(@PathVariable int id){
        return service.getProductById(id);
    }
}
