package com.phpbae.jpa.dao.repository;

import com.phpbae.jpa.business.domain.Member;
import com.phpbae.jpa.dao.repository.custom.CustomMemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>, CustomMemberRepository {

    @Query("SELECT m FROM Member m join fetch m.department")
    public List<Member> getMembers();
}
