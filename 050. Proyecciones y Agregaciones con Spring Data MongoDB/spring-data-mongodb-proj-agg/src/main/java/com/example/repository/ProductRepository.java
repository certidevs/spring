package com.example.repository;

import com.example.dto.PriceByCategory;
import com.example.model.Product;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {


    @Aggregation(pipeline = {

//            "{ $match: { $and: [ { active: true }, { quantity: { $gt: 0 } } ] } }",
//            "{ $match: { active: true, category: ?0, price: {$gte: ?1} } }",
            """
            {   $match: {
                    active: true,
                    category: ?0,
                    price: {$gte: ?1}
            } }
            """,
            "{ $sort: { title: -1 } }"
    })
    List<Product> findByCategoryAndPriceGreaterThanEqual(String category, Double price);


    @Aggregation(pipeline={
            "{ $group:  {_id: $category, totalPrice: {$sum: $price}}}",
            "{$project: {_id: 0, category: $_id, totalPrice: 1}}"
    })
    List<PriceByCategory> groupPriceByCategory();

    @Aggregation("{ $group: {_id: null, totalQuantity:  {$sum:  $quantity}}}")
    Long sumQuantities();

    @Query("{ manufacturer: ?0}")
    @Update("{ $inc: {price: ?1 }}")
    void incrementPriceByManufacturer(String manufacturer, Double price);

    @Aggregation("{ $project: {_id: 0, manufacturer: 1}}")
    List<String> findAllManufacturers();

    @Aggregation("{ $group: {_id: null, avgPrice: {$avg:  $price}}}")
    Double avgPrice();

    @Aggregation("{ $group: {_id: null, totalPrice: {$sum:  {$multiply:  [$price, $quantity]}}}}")
    Double totalPrice();

}
