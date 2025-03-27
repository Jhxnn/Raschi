package com.Raschi.controllers;


import com.Raschi.dtos.SiteDto;
import com.Raschi.models.Car;
import com.Raschi.models.Site;
import com.Raschi.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/site")
public class SiteController {

    @Autowired
    SiteService siteService;

    @GetMapping
    public ResponseEntity<List<Site>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(siteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Site> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(siteService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Site> createSite(@RequestBody SiteDto siteDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(siteService.createSite(siteDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Site> updateSite(@RequestBody SiteDto siteDto,
                                           @PathVariable(name = "id")UUID id){
        return ResponseEntity.status(HttpStatus.CREATED).body(siteService.updateSite(siteDto, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Site> deleteSite(@PathVariable(name = "id")UUID id){
        siteService.deleteSite(id);
        return ResponseEntity.noContent().build();
    }
}
