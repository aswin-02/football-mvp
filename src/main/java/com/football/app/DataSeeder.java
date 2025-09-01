package com.football.app;

import com.football.app.entity.*;
import com.football.app.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepo;
    private final LocationRepository locationRepo;
    private final MatchRepository matchRepo;
    private final PlayerMatchRepository playerMatchRepo;
    private final PlayerStatRepository playerStatRepo;

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
        seedLocations();
        seedMatches();
        seedPlayerMatches();
        seedPlayerStats();
    }

    private void seedUsers() {
        if (userRepo.count() > 0) return; // already seeded
        List<User> users = List.of(
                new User(null, "Alice", "9990001111", "PLAYER", true, LocalDateTime.now()),
                new User(null, "Bob", "9990002222", "PLAYER", true, LocalDateTime.now()),
                new User(null, "Charlie", "9990003333", "PLAYER", true, LocalDateTime.now()),
                new User(null, "Admin", "9999999999", "ADMIN", true, LocalDateTime.now())
        );
        userRepo.saveAll(users);
    }

    private void seedLocations() {
        if (locationRepo.count() > 0) return;
        List<Location> locations = List.of(
                new Location(null, "Turf 1", null, true, LocalDateTime.now(), null),
                new Location(null, "Turf 2", null, true, LocalDateTime.now(), null),
                new Location(null, "Turf 3", null, true, LocalDateTime.now(), null)
        );
        locationRepo.saveAll(locations);
    }

    private void seedMatches() {
        if (matchRepo.count() > 0) return;
        Location turf1 = locationRepo.findByName("Turf 1").orElseThrow();
        Location turf2 = locationRepo.findByName("Turf 2").orElseThrow();

        List<Match> matches = List.of(
                new Match(null, turf1, LocalDate.now(), LocalTime.now(), 60, 99, 1, 14, "OPEN", true, LocalDateTime.now(), null),
                new Match(null, turf2, LocalDate.now(), LocalTime.now(), 60, 99, 1, 14, "OPEN", true, LocalDateTime.now(), null),
                new Match(null, turf1, LocalDate.now(), LocalTime.now(), 60, 100, 2, 14, "OPEN", true, LocalDateTime.now(), null),
                new Match(null, turf2, LocalDate.now(), LocalTime.now(), 60, 100, 2, 14, "OPEN", true, LocalDateTime.now(), null)
        );
        matchRepo.saveAll(matches);
    }

    private void seedPlayerMatches() {
        if (playerMatchRepo.count() > 0) return;
        User alice = userRepo.findByName("Alice").orElseThrow();
        User bob = userRepo.findByName("Bob").orElseThrow();
        Match match1 = matchRepo.findAll().get(0);

        PlayerMatch pm1 = new PlayerMatch(null, match1, alice, true, "BLUE", null);
        PlayerMatch pm2 = new PlayerMatch(null, match1, bob, true, "RED", null);

        playerMatchRepo.saveAll(List.of(pm1, pm2));
    }

    private void seedPlayerStats() {
        if (playerStatRepo.count() > 0) return;
        User alice = userRepo.findByName("Alice").orElseThrow();
        Match match1 = matchRepo.findAll().get(0);

        PlayerStat stat = new PlayerStat(null, alice, match1, 2, LocalDateTime.now());
        playerStatRepo.save(stat);
    }
}
