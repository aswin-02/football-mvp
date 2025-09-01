package com.football.app.service;

import com.football.app.entity.PlayerMatch;
import com.football.app.entity.Match;
import com.football.app.entity.User;
import java.util.List;
import java.util.Optional;

public interface PlayerMatchService {
    PlayerMatch joinMatch(User user, Match match);
    void markAsPaid(PlayerMatch playerMatch);
    List<PlayerMatch> getPlayersByMatch(Match match);
    Optional<PlayerMatch> getPlayerMatch(User user, Match match);

    void generateTeams(Match match);
}
