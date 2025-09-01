package com.football.app.controller;

import com.football.app.entity.Match;
import com.football.app.entity.Location;
import com.football.app.service.MatchService;
import com.football.app.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;
    private final LocationService locationService;

    @PostMapping("/create")
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        return ResponseEntity.ok(matchService.saveMatch(match));
    }

    @GetMapping("/byLocation")
    public ResponseEntity<List<Match>> getMatchesByLocationAndDate(
            @RequestParam Long locationId,
            @RequestParam String date) {

        Optional<Location> loc = locationService.getAllActiveLocations()
                .stream().filter(l -> l.getId().equals(locationId)).findFirst();

        if (loc.isEmpty()) return ResponseEntity.badRequest().build();

        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(matchService.getMatchesByLocationAndDate(loc.get(), localDate));
    }

    @GetMapping("/byDate")
    public ResponseEntity<List<Match>> getMatchesByDate(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(matchService.getMatchesByDate(localDate));
    }
}
