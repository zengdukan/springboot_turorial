package com.example.tutorial;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    @Query(value = "select count(*) from product where name=:name", nativeQuery = true)
    long countByName(@Param("name")String name);
}
