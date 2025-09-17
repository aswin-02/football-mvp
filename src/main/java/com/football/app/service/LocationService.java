package com.football.app.service;

import com.football.app.entity.Location;
import java.util.List;
import java.util.Optional;

public interface LocationService {
    Optional<Location> getLocationById(Long id);
    List<Location> getAllActiveLocations();
    Location createLocation(Location location);
    Location updateLocation(Long id, Location location);
    void deleteLocation(Long id);
}
