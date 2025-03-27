package com.Raschi.services;

import com.Raschi.dtos.SellerDto;
import com.Raschi.models.Seller;
import com.Raschi.repositories.SellerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public Seller findById(UUID id) {
        return sellerRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot be found"));
    }

    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    public Seller createSeller(SellerDto sellerDto) {
        var seller = new Seller();
        BeanUtils.copyProperties(sellerDto, seller);
        return sellerRepository.save(seller);
    }

    public Seller updateSeller(SellerDto sellerDto, UUID id) {
        var seller = findById(id);
        BeanUtils.copyProperties(sellerDto, seller);
        return sellerRepository.save(seller);
    }

    public void deleteSeller(UUID id) {
        var seller = findById(id);
        sellerRepository.delete(seller);
    }
}
