package com.example.springzecoproject.repository;

import com.example.springzecoproject.model.Suza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuzaRepo extends JpaRepository<Suza,Integer> {
}
