package com.EcommBackend.demo.Controller;

import com.EcommBackend.demo.Model.Product;
import com.EcommBackend.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile image){
        try {
          Product newProduct = service.addProduct(product, image);
          return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int id){
        Optional<Product> product = service.getProductById(id);
        byte[] Image = product.orElseThrow().getImageData();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.orElseThrow().getImageType()))
                .body(Image);

    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile image){
        Optional<Product> product1 = service.getProductById(id);
        if(product1 != null){
            Product updatedProduct = null;
            try {
                updatedProduct = service.updateProduct(id, product, image);
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
       Optional<Product> product = service.getProductById(id);
       if(product != null){
           service.deleteProduct(id);
           return new ResponseEntity<>("Product Deleted successfully", HttpStatus.OK);
       }else{
           return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
        List<Product> products = service.searchProduct(keyword);
        return  new ResponseEntity<>(products, HttpStatus.OK);
    }
 }


