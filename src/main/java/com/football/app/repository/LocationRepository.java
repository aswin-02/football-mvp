package com.football.app.repository;

import com.football.app.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByIsActiveTrue();
    Optional<Location> findByName(String name);
}
