package com.sparta.challenge;

import com.sparta.challenge.week05.Team;
import com.sparta.challenge.week05.TeamRepository;
import com.sparta.challenge.week05.User;
import com.sparta.challenge.week05.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NPlusOneTest {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    // user가 5명씩 있는 팀 5개 생성하는 메서드입니당
    @Test
    @DisplayName("생성")
    void create() {
        for(int i = 0; i < 5; i++) {
            Team team = new Team();
            for(int j = 0; j < 5; j++) {
                User user = new User();
                user.setTeam(team);
            }
            teamRepository.save(team);
        }
    }

    @Test
    @DisplayName("N+1 발생")
    @Transactional
    void test1() {
        List<Team> all = teamRepository.findAll(); // findAll 사용
        System.out.println("============== N+1 시점 확인용 ===================");
        for(Team team : all) {
            team.getUsers().size(); // 5번 찾아온다.
        }
    }

    @Test
    @DisplayName("N+1 해결")
    @Transactional
    void test2() {
        List<Team> all = teamRepository.findAllFetchJoin(); // Fetch Join 사용
        System.out.println("============== N+1 시점 확인용 ===================");
        for(Team team : all) {
            team.getUsers().size(); // 이미 위에서 user entity까지 싹 다 조회해서 쿼리 더 안 날림.
        }
    }
}
