package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Procedure(procedureName = "employee_move_to_history")
    boolean moveToHistory(@Param("employee_id_in") Long id);


    @Query(value = "CALL find_employee_after_salary(:salary_in)", nativeQuery = true)
    List<Employee> findAllBySalaryAfterProcedure(@Param("salary_in") Double salary);

//    @Procedure(name = "employee_move_to_history")
//    boolean moveToHistoryNamed(@Param("employee_id_in") Long id);

}

