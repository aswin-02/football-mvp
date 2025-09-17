package com.football.app.controller;

import com.football.app.entity.Location;
import com.football.app.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/list/{id}")
    public Optional<Location> getLocationById(@PathVariable Long id){
        return locationService.getLocationById(id);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Location>> getActiveLocations() {
        return ResponseEntity.ok(locationService.getAllActiveLocations());
    }

    @PostMapping("/create")
    public ResponseEntity<Location> createLocations(@RequestBody Location location){
        return ResponseEntity.ok(locationService.createLocation(location));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location){
        return ResponseEntity.ok(locationService.updateLocation(id, location));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long id){
        return ResponseEntity.ok("Location with id:"+ id +" deleted successfully");
    }
}
