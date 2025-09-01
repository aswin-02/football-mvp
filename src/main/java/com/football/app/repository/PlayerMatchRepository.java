package com.football.app.repository;

import com.football.app.entity.PlayerMatch;
import com.football.app.entity.Match;
import com.football.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PlayerMatchRepository extends JpaRepository<PlayerMatch, Long> {
    List<PlayerMatch> findByMatch(Match match);
    Optional<PlayerMatch> findByMatchAndUser(Match match, User user);
    List<PlayerMatch> findByPaidFalse(); // for reminding unpaid players
}
