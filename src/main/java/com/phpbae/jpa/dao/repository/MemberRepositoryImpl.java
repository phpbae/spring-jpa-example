package com.phpbae.jpa.dao.repository;

import com.phpbae.jpa.business.domain.Member;
import com.phpbae.jpa.dao.repository.custom.CustomMemberRepository;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.phpbae.jpa.business.domain.QMember.member; //Q Domain을 생성해줘야 한다.

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements CustomMemberRepository {

// 이 생성자로 사용하지 말아라. 왠지 모르겠지만, 빈 생성시 에러 발생.
//    public MemberRepositoryImpl(Class<?> domainClass) {
//        super(domainClass);
//    }

    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

    @Override
    public List<Member> getMembersQueryDSL() {
        JPAQuery query = new JPAQuery(getEntityManager());
        return query.from(member).fetch();

    }
}
