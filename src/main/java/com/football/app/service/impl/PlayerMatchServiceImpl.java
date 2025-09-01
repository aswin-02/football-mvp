package com.football.app.service.impl;

import com.football.app.entity.PlayerMatch;
import com.football.app.entity.Match;
import com.football.app.entity.User;
import com.football.app.repository.PlayerMatchRepository;
import com.football.app.service.PlayerMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerMatchServiceImpl implements PlayerMatchService {

    private final PlayerMatchRepository playerMatchRepository;

    @Override
    public PlayerMatch joinMatch(User user, Match match) {
        Optional<PlayerMatch> existing = playerMatchRepository.findByMatchAndUser(match, user);
        if (existing.isPresent()) return existing.get(); // already joined
        PlayerMatch pm = new PlayerMatch();
        pm.setUser(user);
        pm.setMatch(match);
        return playerMatchRepository.save(pm);
    }

    @Override
    public void markAsPaid(PlayerMatch playerMatch) {
        playerMatch.setPaid(true);
        playerMatchRepository.save(playerMatch);
    }

    @Override
    public List<PlayerMatch> getPlayersByMatch(Match match) {
        return playerMatchRepository.findByMatch(match);
    }

    @Override
    public Optional<PlayerMatch> getPlayerMatch(User user, Match match) {
        return playerMatchRepository.findByMatchAndUser(match, user);
    }

    @Override
    public void generateTeams(Match match) {
        List<PlayerMatch> players = getPlayersByMatch(match)
                .stream()
                .filter(PlayerMatch::getPaid)
                .toList();

        if (players.size() < 2) return;

        Collections.shuffle(players);

        int mid = players.size() / 2;
        for (int i = 0; i < players.size(); i++) {
            if (i < mid) players.get(i).setTeam("RED");
            else players.get(i).setTeam("BLUE");
        }

        playerMatchRepository.saveAll(players);
    }
}
