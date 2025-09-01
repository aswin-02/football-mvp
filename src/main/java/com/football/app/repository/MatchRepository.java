package com.football.app.repository;

import com.football.app.entity.Match;
import com.football.app.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByLocationAndDate(Location location, LocalDate date);
    List<Match> findByDate(LocalDate date);
    List<Match> findByStatus(String status);
}
