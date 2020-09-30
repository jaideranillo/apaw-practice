package es.upm.miw.apaw_practice.adapters.mongodb;

import es.upm.miw.apaw_practice.adapters.mongodb.factory.daos.FactorySeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.ShopSeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseSeederService {

    private ShopSeederService shopSeederService;
    private FactorySeederService factorySeederService;

    @Autowired
    public DatabaseSeederService(ShopSeederService shopSeederService, FactorySeederService factorySeederService) {
        this.shopSeederService = shopSeederService;
        this.factorySeederService = factorySeederService;
        this.seedDatabase();
    }

    public void seedDatabase() {
        this.shopSeederService.seedDatabase();
    }

    public void deleteAll() {
        this.shopSeederService.deleteAll();
    }

    public void reSeedDatabase() {
        this.deleteAll();
        this.seedDatabase();
    }
}
