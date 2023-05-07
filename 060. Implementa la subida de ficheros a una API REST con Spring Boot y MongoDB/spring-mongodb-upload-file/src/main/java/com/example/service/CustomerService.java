package com.example.service;

import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public Customer findById(String id){
        return repo.findById(id).orElseThrow();
    }

    public String save(Customer customer, MultipartFile avatar) throws IOException {
        customer.setAvatar(new Binary(BsonBinarySubType.BINARY,
                avatar.getBytes()));
        return repo.save(customer).getId();
    }


}
