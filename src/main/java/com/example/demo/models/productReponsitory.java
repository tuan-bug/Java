package com.example.demo.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface productReponsitory extends JpaRepository<products, Long> {
}
