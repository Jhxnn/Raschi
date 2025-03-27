package com.Raschi.controllers;

import com.Raschi.dtos.SellerDto;
import com.Raschi.models.Seller;
import com.Raschi.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<Seller>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody SellerDto sellerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.createSeller(sellerDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(@RequestBody SellerDto sellerDto,
                                               @PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.updateSeller(sellerDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Seller> deleteSeller(@PathVariable(name = "id") UUID id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}
