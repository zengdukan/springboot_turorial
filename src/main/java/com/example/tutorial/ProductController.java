package com.example.tutorial;

import java.util.Optional;

import com.example.tutorial.dto.AddProductReq;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private Config config;

    private GitConfig gitConfig;

    private GitConfig2 gitConfig2;

    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

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
    public ResponseMessage<ProductEntity> create(@Valid @RequestBody AddProductReq productReq) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCount(productReq.getCount());
        productEntity.setPrice(productReq.getPrice());
        productEntity.setName(productReq.getName());
        productRepository.save(productEntity);
        return new ResponseMessage<ProductEntity>(200, null, productEntity);
    }

    @PutMapping
    public ResponseMessage<ProductEntity> update(@RequestBody ProductEntity productEntity) {
        productRepository.save(productEntity);
        return new ResponseMessage<ProductEntity>(200, null, productEntity);
    }

    @GetMapping("/{id}")
    public ResponseMessage<ProductEntity> get(@PathVariable Long id) {
        Optional<ProductEntity> ret = productRepository.findById(id);
        if (ret.isPresent()) {
            return new ResponseMessage<ProductEntity>(200, null, ret.get());
        } else {
            throw new RuntimeException("not found");
        }
    }

    @GetMapping("/all")
    public ResponseMessage<Iterable<ProductEntity>> getAll() {
        Iterable<ProductEntity> ret = productRepository.findAll();
        return new ResponseMessage<Iterable<ProductEntity>>(org.springframework.http.HttpStatus.OK.value(), null, ret);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<Void> deleteById(@PathVariable Long id) {
        productRepository.deleteById(id);
        return new ResponseMessage<Void>(org.springframework.http.HttpStatus.OK.value(), null, null);
    }

    @GetMapping("/count")
    public ResponseMessage<Long> countByName(@RequestParam(value = "name") String name) {
        long ret = productRepository.countByName(name);
        return new ResponseMessage<Long>(org.springframework.http.HttpStatus.OK.value(), null, ret);
    }

    @GetMapping("/set_price")
    public ResponseMessage<Void> setPrice(@RequestParam(value = "id") Long id, @RequestParam(value = "price") Double price) {
        productRepository.updatePriceById(id, price);
        return ResponseMessage.success(null, "ok");
    }
    
    @GetMapping("/set_price_name")
    public ResponseMessage<?> setPriceAndNameById(@RequestParam(value = "id") Long id, @RequestParam(value = "price") Double price , @RequestParam(value = "name") String name) {
        try {
            productService.setNameAndPriceForTestTx(id, name, price);
            return ResponseMessage.success(null, "ok");
        } catch (Exception e) {
            return ResponseMessage.error(404, "error");
        }
    }
}
