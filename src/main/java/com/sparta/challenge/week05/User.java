package com.sparta.challenge.week05;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)// 지연 로딩 // 다대일
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    public void setTeam(Team team) {
        this.team = team;
        team.getUsers().add(this);
    }
}
