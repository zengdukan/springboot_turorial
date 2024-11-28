package com.example.tutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService {
    private final static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void test(List<ProductEntity> products) {
        for (ProductEntity product : products) {
            logger.info("insert " + product.getName());
            productRepository.save(product);
        }
    }

    @Transactional
    public void setNameAndPriceForTestTx(Long id, String name, Double price) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.updatePriceById(id, price);

            ProductEntity p = product.get();
            p.setName(name);
            p.setPrice(price + 10);
            productRepository.save(p);
        }
    }

    public List<ProductEntity> findAllProducts() {
        List<ProductEntity> ret = new ArrayList<ProductEntity>();
        for (ProductEntity pro : productRepository.findAll()) {
            ret.add(pro);
        }
        return ret;
    }

    public Optional<ProductEntity> findProductById(Long id) {
        return productRepository.findById(id);
    }
}
