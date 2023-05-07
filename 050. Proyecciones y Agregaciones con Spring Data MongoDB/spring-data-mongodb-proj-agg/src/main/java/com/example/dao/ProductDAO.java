package com.example.dao;

import com.example.dto.PriceByManufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class ProductDAO {

    @Autowired
    MongoTemplate mongo;

    public List<Double> groupPrice(String category){
        var agg = newAggregation(
                match(Criteria.where("category").is(category)),
                group("manufacturer").sum("price").as("totalPrice"),
                project("totalPrice").and("manufacturer"),
                sort(Sort.Direction.ASC, "totalPrice")
        );
        return mongo.aggregate(agg, "products", Double.class).getMappedResults();
    }
}
