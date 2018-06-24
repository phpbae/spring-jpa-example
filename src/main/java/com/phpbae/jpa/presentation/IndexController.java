package com.phpbae.jpa.presentation;


import com.phpbae.jpa.business.domain.Department;
import com.phpbae.jpa.business.domain.Member;
import com.phpbae.jpa.business.domain.Part;
import com.phpbae.jpa.business.service.DepartmentService;
import com.phpbae.jpa.business.service.MemberService;
import com.phpbae.jpa.business.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PartService partService;

    @GetMapping(value = "p", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity nProblem() {
        List<Member> members = memberService.getMembers();
        List<Member> memberList = memberService.getMembersFetchJoin(); // fetch join 적용
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping(value = "p2", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity nProblem2() {
        List<Department> departments = departmentService.getDepartments();
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping(value = "p3", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity nProblem3() {
        List<Department> departments = departmentService.getDepartments();
        //lazy 로 가지고 와서, 이렇게 호출을 하면?
        for(Department department: departments){
            department.getPart().getPartName();
        }
        return new ResponseEntity(null, HttpStatus.OK);
    }

}
