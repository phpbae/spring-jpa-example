package com.phpbae.jpa.business.service;

import com.phpbae.jpa.business.domain.Member;
import com.phpbae.jpa.dao.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getMembers() {
        return memberRepository
                .findAll();
    }

    public List<Member> getMembersFetchJoin(){
        return memberRepository
                .getMembers();
    }

    public List<Member> getMembersQueryDsl(){
        return memberRepository.getMembersQueryDSL();
    }

    public Member getMember() {
        return memberRepository
                .findById(1)
                .orElse(null);
    }
}
