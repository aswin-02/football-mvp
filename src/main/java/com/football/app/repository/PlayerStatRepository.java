package com.football.app.repository;

import com.football.app.entity.PlayerStat;
import com.football.app.entity.User;
import com.football.app.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlayerStatRepository extends JpaRepository<PlayerStat, Long> {
    List<PlayerStat> findByUser(User user);
    List<PlayerStat> findByMatch(Match match);
    List<PlayerStat> findByCreatedAtBetween(java.time.LocalDateTime start, java.time.LocalDateTime end);
}
