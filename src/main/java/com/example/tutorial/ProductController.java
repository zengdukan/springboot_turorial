package com.example.tutorial;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<?> create(ProductEntity productEntity) {
        productRepository.save(productEntity);
        ResponseMessage<ProductEntity> msg = new ResponseMessage<ProductEntity>(200, "ok", productEntity);
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(Long id) {
        Optional<ProductEntity> ret = productRepository.findById(id);
        if (ret.isPresent()) {
            ResponseMessage<ProductEntity> msg = new ResponseMessage<ProductEntity>(200, "ok", ret.get());
            return  ResponseEntity.ok(msg);
        } else {
            ResponseMessage<ProductEntity> msg = new ResponseMessage<ProductEntity>(org.springframework.http.HttpStatus.NOT_FOUND.value(), "not found", null);
            return  ResponseEntity.ok(msg);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(Long id) {
        productRepository.deleteById(id);
        ResponseMessage<ProductEntity> msg = new ResponseMessage<ProductEntity>(org.springframework.http.HttpStatus.OK.value(), "ok", null);
        return  ResponseEntity.ok(msg);
    }
}
