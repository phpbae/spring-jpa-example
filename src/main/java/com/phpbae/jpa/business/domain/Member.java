package com.phpbae.jpa.business.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_idx")
    private int memberIdx;

    @Embedded
    private MemberDetail memberDetail;

    @ManyToOne(targetEntity = Department.class) //default fetch type EAGER
    @JoinColumn(name = "department_idx")
    Department department;

}
