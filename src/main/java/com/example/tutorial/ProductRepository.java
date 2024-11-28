package com.example.tutorial;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;



public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    @Query(value = "select count(*) from product where name=:name", nativeQuery = true)
    long countByName(@Param("name")String name);


    @Transactional
    @Modifying
    @Query(value = "update product set price=?2 where id=?1", nativeQuery = true)
    void updatePriceById(Long id, Double price);
}
