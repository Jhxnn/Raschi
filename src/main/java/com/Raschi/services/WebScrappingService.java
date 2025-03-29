package com.Raschi.services;


import com.Raschi.dtos.CarDto;
import com.Raschi.dtos.SearchCarDto;
import com.Raschi.dtos.SiteDto;
import com.Raschi.models.Car;
import com.Raschi.models.Site;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class WebScrappingService {

    @Autowired
    CarService carService;

    @Autowired
    SellerService sellerService;

    @Autowired
    SiteService siteService;

    public List<Car> findByBrandAndModel(SearchCarDto searchCarDto){
        String webMotorsUrl = "https://www.webmotors.com.br/carros/estoque/" + searchCarDto.brand() + "/" + searchCarDto.model();
        String marketPlaceUrl = "https://www.facebook.com/marketplace/"+ searchCarDto.location()+"/search/?query="+searchCarDto.brand() + "%20"+ searchCarDto.model()+ "&exact=false&locale=pt_BR";
        String olxUrl = "https://www.olx.com.br/estado-sc?q="+ searchCarDto.brand()+ "%20"+ searchCarDto.model();
        List<Car> listCar = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(olxUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36")
                    .header("Accept-Language", "pt-BR,pt;q=0.9")
                    .timeout(10000)
                    .get();

            Elements anuncios = doc.select("div.sc-12rk7z2-1"); // Seletor dos cards (pode mudar conforme o layout)

            for (Element anuncio : anuncios) {
                String titulo = anuncio.select("h2").text();
                String priceText = anuncio.select("span.sc-ifAKCX.eoKYee").text();

                priceText = priceText.replace("R$", "").replace(".", "").replace(",", ".");
                double price = Double.parseDouble(priceText);

                SiteDto siteDto = new SiteDto("Webmotors", olxUrl);
                Site site = siteService.createSite(siteDto);

                CarDto carDto = new CarDto(price, searchCarDto.model(), searchCarDto.brand(),titulo, "teste",site);
                Car car = carService.createCar(carDto);
                listCar.add(car);


            }



        }catch (Exception e){
            e.printStackTrace();
        }
        return listCar;
    }


}
