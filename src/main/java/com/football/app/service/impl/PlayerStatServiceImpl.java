package com.football.app.service.impl;

import com.football.app.entity.PlayerStat;
import com.football.app.entity.Match;
import com.football.app.entity.User;
import com.football.app.repository.PlayerStatRepository;
import com.football.app.service.PlayerStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerStatServiceImpl implements PlayerStatService {

    private final PlayerStatRepository playerStatRepository;

    @Override
    public PlayerStat saveStat(PlayerStat stat) {
        return playerStatRepository.save(stat);
    }

    @Override
    public List<PlayerStat> getStatsByUser(User user) {
        return playerStatRepository.findByUser(user);
    }

    @Override
    public List<PlayerStat> getStatsByMatch(Match match) {
        return playerStatRepository.findByMatch(match);
    }

    @Override
    public List<PlayerStat> getStatsBetween(LocalDateTime start, LocalDateTime end) {
        return playerStatRepository.findByCreatedAtBetween(start, end);
    }
}
