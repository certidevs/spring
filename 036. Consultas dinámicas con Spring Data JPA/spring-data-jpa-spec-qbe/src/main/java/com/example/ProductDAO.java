package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Expression;
import java.util.List;

@Repository
public class ProductDAO {

    public static final String PREMIUM_PATTERN = "%PREMIUM%";
    private static final Double PREMIUM_PRICE = 1000.0;

    @Autowired
    private ProductRepository repository;

    public List<Product> findAllByManufacturerLike(String manufacturer){

        Specification<Product> specification = (root, query, criteriaBuilder) ->
                criteriaBuilder
                        .like(root.get(Product_.MANUFACTURER), "%" + manufacturer + "%");

        return this.repository.findAll(specification);
    }

    public List<Product> findAllByPriceBetween(Double min, Double max){

        Specification<Product> specification = (root, query, criteriaBuilder) ->
                criteriaBuilder
                        .between(root.get(Product_.PRICE), min, max);

        return this.repository.findAll(specification);
    }

    public List<Product> findAllByIdIn(List<Long> ids){

        Specification<Product> specification = (root, query, criteriaBuilder) ->
                criteriaBuilder.in(root.get(Product_.ID)).value(ids);

        return this.repository.findAll(specification);
    }

    public List<Product> findAllPremium(){

        Specification<Product> specification = (root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.like(root.get(Product_.NAME), PREMIUM_PATTERN),
                        criteriaBuilder.greaterThanOrEqualTo(root.get(Product_.PRICE), PREMIUM_PRICE)
                );

        return this.repository.findAll(specification);
    }


}
