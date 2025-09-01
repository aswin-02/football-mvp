package com.football.app.service.impl;

import com.football.app.entity.Location;
import com.football.app.repository.LocationRepository;
import com.football.app.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public List<Location> getAllActiveLocations() {
        return locationRepository.findByIsActiveTrue();
    }

}
