package com.example.controller;

import com.example.model.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String index(Model model){
        return findAllPaginated(1, "firstName", "desc", model);
    }

    @GetMapping("/employees/page/{pageNum}")
    public String findAllPaginated(@PathVariable int pageNum,
                                   @RequestParam("sortField") String sortField,
                                   @RequestParam("sortDir") String sortDir,
                                   Model model){

        int pageSize = 5;
        Page<Employee> page = this.employeeService.findAllPaginated(pageNum, pageSize, sortField, sortDir);

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("employees", page.getContent());
        return "employee-list";

    }

}
