package com.phpbae.jpa.dao.repository.custom;

import com.phpbae.jpa.business.domain.Member;

import java.util.List;

public interface CustomMemberRepository {

    public List<Member> getMembersQueryDSL();
}
