package com.example.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Hello;

public interface HelloRepository extends JpaRepository<Hello, Integer> {

}
