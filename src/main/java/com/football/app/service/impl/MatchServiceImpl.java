package com.football.app.service.impl;

import com.football.app.entity.Match;
import com.football.app.entity.Location;
import com.football.app.repository.MatchRepository;
import com.football.app.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    @Override
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public Optional<Match> getMatchById(Long id) {
        return matchRepository.findById(id);
    }

    @Override
    public List<Match> getMatchesByLocationAndDate(Location location, LocalDate date) {
        return matchRepository.findByLocationAndDate(location, date);
    }

    @Override
    public List<Match> getMatchesByDate(LocalDate date) {
        return matchRepository.findByDate(date);
    }
}
