package com.phpbae.jpa.business.service;

import com.phpbae.jpa.business.domain.Department;
import com.phpbae.jpa.dao.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department getDepartment() {
        return departmentRepository
                .findById(3)
                .orElse(null);
    }

    public List<Department> getDepartments() {
        return departmentRepository
                .findAll();
    }
}
