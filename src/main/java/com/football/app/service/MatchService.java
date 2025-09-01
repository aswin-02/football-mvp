package com.football.app.service;

import com.football.app.entity.Match;
import com.football.app.entity.Location;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MatchService {
    Match saveMatch(Match match);
    Optional<Match> getMatchById(Long id);
    List<Match> getMatchesByLocationAndDate(Location location, LocalDate date);
    List<Match> getMatchesByDate(LocalDate date);
}
