package com.klu.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dept")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deptId;

    private String deptName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}