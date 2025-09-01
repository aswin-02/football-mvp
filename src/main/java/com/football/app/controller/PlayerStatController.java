package com.football.app.controller;

import com.football.app.entity.PlayerStat;
import com.football.app.entity.Match;
import com.football.app.entity.User;
import com.football.app.service.PlayerStatService;
import com.football.app.service.MatchService;
import com.football.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/player-stats")
@RequiredArgsConstructor
public class PlayerStatController {

    private final PlayerStatService playerStatService;
    private final UserService userService;
    private final MatchService matchService;

    @PostMapping("/add")
    public ResponseEntity<PlayerStat> addStat(
            @RequestParam Long userId,
            @RequestParam Long matchId,
            @RequestParam Integer goals) {

        Optional<User> user = userService.getUserById(userId);
        Optional<Match> match = matchService.getMatchById(matchId);
        if (user.isEmpty() || match.isEmpty()) return ResponseEntity.badRequest().build();

        PlayerStat stat = new PlayerStat();
        stat.setUser(user.get());
        stat.setMatch(match.get());
        stat.setGoals(goals);

        return ResponseEntity.ok(playerStatService.saveStat(stat));
    }

    @GetMapping("/byUser")
    public ResponseEntity<List<PlayerStat>> getStatsByUser(@RequestParam Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(playerStatService.getStatsByUser(user.get()));
    }

    @GetMapping("/byMatch")
    public ResponseEntity<List<PlayerStat>> getStatsByMatch(@RequestParam Long matchId) {
        Optional<Match> match = matchService.getMatchById(matchId);
        if (match.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(playerStatService.getStatsByMatch(match.get()));
    }
}
