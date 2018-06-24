# spring-jpa-example
Java Standard ORM. JPA(Java Persistence API)


Spring boot 2.0.3

dependency 
- Lombok
- Configuration Processor
- Web
- JPA
- MySQL
- JDBC


```
 select member0_.member_idx as member_i1_1_, member0_.department_idx as departme6_1_, member0_.address as address2_1_, member0_.age as age3_1_, member0_.email as email4_1_, member0_.name as name5_1_ from member member0_
 select department0_.department_idx as departme1_0_0_, department0_.department_name as departme2_0_0_, department0_.part_idx as part_idx3_0_0_, part1_.part_idx as part_idx1_2_1_, part1_.part_name as part_nam2_2_1_ from department department0_ left outer join part part1_ on department0_.part_idx=part1_.part_idx where department0_.department_idx=?
 select department0_.department_idx as departme1_0_0_, department0_.department_name as departme2_0_0_, department0_.part_idx as part_idx3_0_0_, part1_.part_idx as part_idx1_2_1_, part1_.part_name as part_nam2_2_1_ from department department0_ left outer join part part1_ on department0_.part_idx=part1_.part_idx where department0_.department_idx=?
 select department0_.department_idx as departme1_0_0_, department0_.department_name as departme2_0_0_, department0_.part_idx as part_idx3_0_0_, part1_.part_idx as part_idx1_2_1_, part1_.part_name as part_nam2_2_1_ from department department0_ left outer join part part1_ on department0_.part_idx=part1_.part_idx where department0_.department_idx=?
 select department0_.department_idx as departme1_0_0_, department0_.department_name as departme2_0_0_, department0_.part_idx as part_idx3_0_0_, part1_.part_idx as part_idx1_2_1_, part1_.part_name as part_nam2_2_1_ from department department0_ left outer join part part1_ on department0_.part_idx=part1_.part_idx where department0_.department_idx=?

member를 조회하면, @ManyToOne 기본 페치전략이 EAGER(즉시로딩) 이기 때문에, 위에와 같이 부서를 가지고 오기 위해 쿼리를 4번 더 날린다.
지금은 member가 51개 -> 첫조회 (1) + 51개의 member의 department 조회(4) = 5
하지만, 데이터가 겁나 많다면..?

-> 위의 성능문제는 fetch join을 적용해서 회피했다.
select member0_.member_idx as member_i1_1_0_, department1_.department_idx as departme1_0_1_, member0_.department_idx as departme6_1_0_, member0_.address as address2_1_0_, member0_.age as age3_1_0_, member0_.email as email4_1_0_, member0_.name as name5_1_0_, department1_.department_name as departme2_0_1_, department1_.part_idx as part_idx3_0_1_ from member member0_ inner join department department1_ on member0_.department_idx=department1_.department_idx


 select department0_.department_idx as departme1_0_, department0_.department_name as departme2_0_, department0_.part_idx as part_idx3_0_ from department department0_
 select part0_.part_idx as part_idx1_2_0_, part0_.part_name as part_nam2_2_0_ from part part0_ where part0_.part_idx=?
 select part0_.part_idx as part_idx1_2_0_, part0_.part_name as part_nam2_2_0_ from part part0_ where part0_.part_idx=?

department를 조회하면, 역시나.. @ManyToOne으로 관계가 맵핑 되어있는 part를 조회해 올때, N+1 문제가 발생한다.

위의 같은 경우, fetch = FetchType.LAZY 이렇게 설정을 해주면 되지 않겠냐? 하겠지만.. 과연..?
department 의 part 관계를 lazy로 변경을 해보겠다.

Hibernate: select department0_.department_idx as departme1_0_, department0_.department_name as departme2_0_, department0_.part_idx as part_idx3_0_ from department department0_
쿼리가 1번만 날라간다. 그런데 여기서, part객체를 호출해보자. (IndexController nProblem3 method)

Hibernate: select department0_.department_idx as departme1_0_, department0_.department_name as departme2_0_, department0_.part_idx as part_idx3_0_ from department department0_
Hibernate: select part0_.part_idx as part_idx1_2_0_, part0_.part_name as part_nam2_2_0_ from part part0_ where part0_.part_idx=?
Hibernate: select part0_.part_idx as part_idx1_2_0_, part0_.part_name as part_nam2_2_0_ from part part0_ where part0_.part_idx=?

뭐여..? 결국에는 EAGER랑 똑같은 결과가 나온다. 결국에는 객체 참조를 하면서 저렇게 가공하면 만약 10명의 사용자가 위와 같은 메소드를 호출하면..?
10 * 쿼리 3번씩이면.. 어우야 30번이다.. 지금은 데이터가 적어서 그렇지.. 저게 100건씩 날린다하면..ㄷㄷ

-> 위의 성능문제는 @BatchSize를 적용하여 회피했다. N번 호출되는 Part 엔티티에 적용했다.
select department0_.department_idx as departme1_0_, department0_.department_name as departme2_0_, department0_.part_idx as part_idx3_0_ from department department0_
select part0_.part_idx as part_idx1_2_0_, part0_.part_name as part_nam2_2_0_ from part part0_ where part0_.part_idx in (?, ?)

N+1 해결방안
- join fetch 사용
- fetch 전략 EAGER는 피한다.
- EntityGraph를 사용(@EntityGraph)
- batchSize 사용(@BatchSize)
```

---

```
기존 JPA repository interface를 사용하고, queryDSL을 사용하는 repository를 정의할 수 없을까? 사용자 정의 리포지토리를 정의해 보자.
사용자 정의 리포지토리
- 직접 구현할 메소드를 가지고 있는 인터페이스 명은 자유롭게 작성 가능
- CustomMemberRepository 인터페이스 생성
- 해당 인터페이스를 구현하는 클래스명은 Impl 이라고 붙여줘야함.
만약 바꾸고 싶으면
@EnableJpaRepositories(basePackages = "com.chozza.repository", repositoryImplementationPostfix = "Impl")
```