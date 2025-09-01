package com.football.app.service;

import com.football.app.entity.PlayerStat;
import com.football.app.entity.Match;
import com.football.app.entity.User;
import java.time.LocalDateTime;
import java.util.List;

public interface PlayerStatService {
    PlayerStat saveStat(PlayerStat stat);
    List<PlayerStat> getStatsByUser(User user);
    List<PlayerStat> getStatsByMatch(Match match);
    List<PlayerStat> getStatsBetween(LocalDateTime start, LocalDateTime end);
}
