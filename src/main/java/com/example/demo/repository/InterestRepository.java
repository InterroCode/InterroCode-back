package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Interest;

public interface InterestRepository extends JpaRepository<Interest, Integer>{
    
}