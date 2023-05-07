package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class EmployeeService {

    @Autowired
    private MongoTemplate mongoTemplate;

}
