package com.emintufan.carrentalsystem.dao;

import com.emintufan.carrentalsystem.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeDao extends JpaRepository<Office,Integer> {
}
