package com.example.repository;

import com.example.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Subscription findByUserId(Long id);

    Subscription findByUserUsername(String username);

}