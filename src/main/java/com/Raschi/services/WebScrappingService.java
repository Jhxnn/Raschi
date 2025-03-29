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
            Document webMotors = Jsoup.connect(webMotorsUrl).get();
            Elements nameElements = webMotors.select("h3._body-regular-small_qtpsh_152");
            Elements priceElements = webMotors.select("p._web-subtitle-medium_qtpsh_69");
            Elements imageElements = webMotors.select("img._Image_452ae_9");
            Elements yearElements = webMotors.select("p._body-regular-small_qtpsh_152");

            for (int i = 0; i < Math.min(3, nameElements.size()); i++) {
                String carName = nameElements.get(i).text();
                String priceText = priceElements.get(i).text();
                String imageUrl = imageElements.get(i).attr("src");
                String carYear = yearElements.get(i).text();

                priceText = priceText.replace("R$", "").replace(".", "").replace(",", ".");
                double price = Double.parseDouble(priceText);


                SiteDto siteDto = new SiteDto("Webmotors", imageUrl);
                Site site = siteService.createSite(siteDto);

                CarDto carDto = new CarDto(price, searchCarDto.model(), searchCarDto.brand(),carName, carYear,site);
                Car car = carService.createCar(carDto);
                listCar.add(car);


            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCar;
    }


}
