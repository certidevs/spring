package com.example;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class ProductDAO {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Product> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteria = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);
        CriteriaQuery<Product> findAll = criteria.select(product);

        return entityManager.createQuery(findAll).getResultList();

    }


    public Product findById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteria = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);
        CriteriaQuery<Product> findById = criteria
                .select(product)
                .where(criteriaBuilder.equal(product.get(Product_.ID), id));

        return entityManager.createQuery(findById).getSingleResult();

    }

    public Product findByNameLike(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteria = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);
        CriteriaQuery<Product> findByName = criteria
                .select(product)
                .where(criteriaBuilder.like(product.get(Product_.NAME), "%" + name + "%"));

        return entityManager.createQuery(findByName).getSingleResult();

    }


    public List<Product> findByManufacturerAndColor(String manufacturer, List<String> colors) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteria = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);

        Predicate manufacturerPred = criteriaBuilder.equal(product.get(Product_.MANUFACTURER), manufacturer);

        Predicate colorsPred = criteriaBuilder.or(colors.stream()
                .map(color -> criteriaBuilder.equal(product.get(Product_.COLOR), color))
                .toArray(Predicate[]::new)
        );


        Predicate finalPred = criteriaBuilder.and(manufacturerPred, colorsPred);

        CriteriaQuery<Product> findByPredicates = criteria
                .select(product)
                .where(finalPred);


        return entityManager.createQuery(findByPredicates).getResultList();

    }

    public List<Product> findProjection(List<String> fields) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteria = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);

        criteria.multiselect(fields.stream()
                .map(product::get)
                .toArray(Selection[]::new)
        );

        return entityManager.createQuery(criteria).getResultList();

    }
    public List<Product> findJoin(String category) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteria = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);

        Join<Product,Category> join = product.join(Product_.CATEGORIES);

        criteria.where(criteriaBuilder.equal(join.get(Category_.NAME), category));

        return entityManager.createQuery(criteria).getResultList();

    }


}
