package com.a08r.pick_location.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
//TODO:http://localhost:9999/api/directions

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="*" )  // Allow CORS for Flutter Web
public class DirectionsController {

    private static final String GOOGLE_MAPS_API_KEY = "AIzaSyDRaJJnyvmDSU8OgI8M20C5nmwHNc_AMvk";

    @GetMapping("/directions")
    public ResponseEntity<String> getDirections(@RequestParam String origin, @RequestParam String destination) {
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin="
                + origin + "&destination=" + destination + "&key=" + GOOGLE_MAPS_API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        return ResponseEntity.ok(response);
    }
}
