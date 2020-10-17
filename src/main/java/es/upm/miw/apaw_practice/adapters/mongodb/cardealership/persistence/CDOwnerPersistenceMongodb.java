package es.upm.miw.apaw_practice.adapters.mongodb.cardealership.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.cardealership.daos.CDOwnerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cardealership.daos.CarDealershipRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cardealership.entities.CDOwnerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.cardealership.entities.CarDealershipEntity;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_dealership.CDOwnerPersistence;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("cDOwnerPersistence")
public class CDOwnerPersistenceMongodb implements CDOwnerPersistence {

    private CDOwnerRepository cdOwnerRepository;
    private CarDealershipRepository carDealershipRepository;

    public CDOwnerPersistenceMongodb(CDOwnerRepository cdOwnerRepository, CarDealershipRepository carDealershipRepository) {
        this.cdOwnerRepository = cdOwnerRepository;
        this.carDealershipRepository = carDealershipRepository;
    }

    @Override
    public Stream<String> findOwnerByBrand(String brand) {
        return this.carDealershipRepository.findAll()
                .stream()
                .filter(item -> item.getVehicles().stream()
                    .anyMatch(cdVehicleEntity -> cdVehicleEntity.getBrand().equals(brand)))
                .map(CarDealershipEntity::getOwner)
                .map(CDOwnerEntity::getName);
    }
}
