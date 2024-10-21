package com.EcommBackend.demo.Controller;

import com.EcommBackend.demo.Model.Product;
import com.EcommBackend.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> Products = service.getAllProducts();
        return new ResponseEntity<>(Products, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable int id){
        Optional<Product> product = service.getProductById(id);
        if(product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }else{
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
