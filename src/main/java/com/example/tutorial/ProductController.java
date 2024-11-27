package com.example.tutorial;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private Config config;

    private GitConfig gitConfig;

    private GitConfig2 gitConfig2;

    private ProductRepository productRepository;

    public ProductController(Config config, GitConfig gitConfig, GitConfig2 gitConfig2, ProductRepository productRepository) {
        this.config = config;
        this.gitConfig = gitConfig;
        this.gitConfig2 = gitConfig2;
        this.productRepository = productRepository;
        System.out.println(config.getOutFile());
        System.out.println(this.gitConfig);
        System.out.println(this.gitConfig2);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductEntity productEntity) {
        productRepository.save(productEntity);
        ResponseMessage<ProductEntity> msg = new ResponseMessage<ProductEntity>(200, "ok", productEntity);
        return ResponseEntity.ok(msg);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProductEntity productEntity) {
        productRepository.save(productEntity);
        ResponseMessage<ProductEntity> msg = new ResponseMessage<ProductEntity>(200, "ok", productEntity);
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Optional<ProductEntity> ret = productRepository.findById(id);
        if (ret.isPresent()) {
            ResponseMessage<ProductEntity> msg = new ResponseMessage<ProductEntity>(200, "ok", ret.get());
            return  ResponseEntity.ok(msg);
        } else {
            ResponseMessage<ProductEntity> msg = new ResponseMessage<ProductEntity>(org.springframework.http.HttpStatus.NOT_FOUND.value(), "not found", null);
            return  ResponseEntity.ok(msg);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        Iterable<ProductEntity> ret = productRepository.findAll();
        ResponseMessage<Iterable<ProductEntity>> msg = new ResponseMessage<Iterable<ProductEntity>>(org.springframework.http.HttpStatus.OK.value(), "ok", ret);
        return  ResponseEntity.ok(msg);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        productRepository.deleteById(id);
        ResponseMessage<ProductEntity> msg = new ResponseMessage<ProductEntity>(org.springframework.http.HttpStatus.OK.value(), "ok", null);
        return  ResponseEntity.ok(msg);
    }
}
