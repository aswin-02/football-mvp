package com.football.app.service.impl;

import com.football.app.entity.Location;
import com.football.app.repository.LocationRepository;
import com.football.app.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public Optional<Location> getLocationById(Long id){
        return locationRepository.findById(id);
    }

    @Override
    public List<Location> getAllActiveLocations() {
        return locationRepository.findByIsActiveTrue();
    }

    @Override
    public Location createLocation(Location location){
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(Long id, Location updatedLocation){
        Location existingLocation = locationRepository.findById(id).orElseThrow(()->new RuntimeException("Location not found with id :" + id));

        existingLocation.setName(updatedLocation.getName());
        existingLocation.setAddress(updatedLocation.getAddress());
        existingLocation.setIsActive(updatedLocation.getIsActive());

        return locationRepository.save(existingLocation);
    }

    @Override
    public void deleteLocation(Long id){
        Location location = locationRepository.findById(id).orElseThrow(()->new RuntimeException("Location not found with id :" + id));

        locationRepository.delete(location);
    }
}
