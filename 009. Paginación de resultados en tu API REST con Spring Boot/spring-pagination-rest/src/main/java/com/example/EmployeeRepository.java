package com.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findAllByMarriedTrue(Pageable pageable);

    List<Employee> findAllBySalaryBetween(Double min, Double max, Sort sort);

    Page<Employee> findAllByBirthDateBefore(LocalDate birthDate, Pageable pageable);
}
