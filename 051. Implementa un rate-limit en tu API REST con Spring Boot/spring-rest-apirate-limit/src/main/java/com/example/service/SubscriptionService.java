package com.example.service;

import com.example.model.Subscription;
import com.example.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository repo;

    public Subscription findByUserId(Long id){
        return this.repo.findByUserId(id);
    }
    public Subscription findByUserName(String username){
        return this.repo.findByUserUsername(username);
    }

}
