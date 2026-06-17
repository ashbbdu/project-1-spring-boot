package com.project1.repositories;

import com.project1.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<EmployeeEntity , Long> {
}
