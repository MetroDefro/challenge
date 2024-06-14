package com.sparta.challenge.week05;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    // JPQL에서 성능 최적화를 위해 제공하는 기능으로 연관된 엔티티나 컬렉션을 한 번에 조회.
    // 일반 조인은 연관된 엔티티를 함께 조회하지 않음.
    // 모든 team을 조회하며 team과 연관된 user도 같이 조회했다.
    @Query("select t from Team t join fetch t.users")
    List<Team> findAllFetchJoin();
}