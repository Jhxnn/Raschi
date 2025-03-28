package com.Raschi.services;


import com.Raschi.dtos.SearchCarDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.util.Elements;

@Service
public class WebScrappingService {

    @Autowired
    CarService carService;

    @Autowired
    SellerService sellerService;

    @Autowired
    SiteService siteService;

    private void findByBrandAndModel(SearchCarDto searchCarDto){
        String webMotorsUrl = "https://www.webmotors.com.br/carros/estoque/" + searchCarDto.brand() + "/" + searchCarDto.model();
        String marketPlaceUrl = "https://www.facebook.com/marketplace/"+ searchCarDto.location()+"/search/?query="+searchCarDto.brand() + "%20"+ searchCarDto.model()+ "&exact=false&locale=pt_BR";
        String olxUrl = "https://www.olx.com.br/estado-sc?q="+ searchCarDto.brand()+ "%20"+ searchCarDto.model();
        try {
            Document webMotors = Jsoup.connect(webMotorsUrl).get();
            Elements priceElements =

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
