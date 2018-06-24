package com.phpbae.jpa.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_idx")
    private int departmentIdx;

    private String departmentName;

    @OneToMany(mappedBy = "department", targetEntity = Member.class) //default fetch type LAZY
    private List<Member> members;

    @ManyToOne(targetEntity = Part.class, fetch = FetchType.LAZY) //default fetch type EAGER
    @JoinColumn(name = "part_idx", referencedColumnName = "part_idx")
    private Part part;

}
