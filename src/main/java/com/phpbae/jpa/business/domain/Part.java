package com.phpbae.jpa.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "part")
@BatchSize(size = 10)
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "part_idx")
    private int partIdx;

    private String partName;

    @OneToMany(mappedBy = "part", targetEntity = Department.class) //default fetch type LAZY
    private List<Department> departments;

}
