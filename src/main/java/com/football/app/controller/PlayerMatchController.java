package com.football.app.controller;

import com.football.app.entity.Match;
import com.football.app.entity.PlayerMatch;
import com.football.app.entity.User;
import com.football.app.service.PlayerMatchService;
import com.football.app.service.MatchService;
import com.football.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/player-matches")
@RequiredArgsConstructor
public class PlayerMatchController {

    private final PlayerMatchService playerMatchService;
    private final UserService userService;
    private final MatchService matchService;

    @PostMapping("/join")
    public ResponseEntity<PlayerMatch> joinMatch(
            @RequestParam Long userId,
            @RequestParam Long matchId) {

        Optional<User> user = userService.getUserById(userId);
        Optional<Match> match = matchService.getMatchById(matchId);

        if (user.isEmpty() || match.isEmpty()) return ResponseEntity.badRequest().build();

        PlayerMatch pm = playerMatchService.joinMatch(user.get(), match.get());
        return ResponseEntity.ok(pm);
    }

    @PostMapping("/pay")
    public ResponseEntity<PlayerMatch> markPaid(@RequestParam Long playerMatchId) {
        Optional<PlayerMatch> pmOpt = playerMatchService.getPlayersByMatch(null).stream()
                .filter(pm -> pm.getId().equals(playerMatchId)).findFirst();

        if (pmOpt.isEmpty()) return ResponseEntity.badRequest().build();

        playerMatchService.markAsPaid(pmOpt.get());
        return ResponseEntity.ok(pmOpt.get());
    }

    @GetMapping("/byMatch")
    public ResponseEntity<List<PlayerMatch>> getPlayersByMatch(@RequestParam Long matchId) {
        Optional<Match> match = matchService.getMatchById(matchId);
        if (match.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(playerMatchService.getPlayersByMatch(match.get()));
    }
}
