package com.Raschi.services;

import com.Raschi.dtos.SiteDto;
import com.Raschi.models.Site;
import com.Raschi.repositories.SiteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SiteService {

    @Autowired
    SiteRepository siteRepository;

    public Site findById(UUID id) {
        return siteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot be found"));
    }

    public List<Site> findAll() {
        return siteRepository.findAll();
    }

    public Site createSite(SiteDto siteDto) {
        var site = new Site();
        BeanUtils.copyProperties(siteDto, site);
        return siteRepository.save(site);
    }

    public Site updateSite(SiteDto siteDto, UUID id) {
        var site = findById(id);
        BeanUtils.copyProperties(siteDto, site);
        return siteRepository.save(site);
    }

    public void deleteSite(UUID id) {
        var site = findById(id);
        siteRepository.delete(site);
    }
}
