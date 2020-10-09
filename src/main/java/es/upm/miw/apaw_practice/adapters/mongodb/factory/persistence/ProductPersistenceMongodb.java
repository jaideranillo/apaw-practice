package es.upm.miw.apaw_practice.adapters.mongodb.factory.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.factory.daos.MachineRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.factory.daos.ProductRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.factory.entities.ProductEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.factory.Machine;
import es.upm.miw.apaw_practice.domain.models.factory.Product;
import es.upm.miw.apaw_practice.domain.persistence_ports.factory.ProductPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("productPersistence")
public class ProductPersistenceMongodb implements ProductPersistence {
    private ProductRepository productRepository;

    private MachineRepository machineRepository;

    @Autowired
    public ProductPersistenceMongodb(ProductRepository productRepository, MachineRepository machineRepository) {
        this.productRepository = productRepository;
        this.machineRepository = machineRepository;
    }

    @Override
    public Product readBySerialNumber(Long serialNumber) {
        return this.productRepository
                .findBySerialNumber(serialNumber)
                .orElseThrow(() -> new NotFoundException("Product serial number: " + serialNumber))
                .toProduct();
    }

    @Override
    public Stream<Product> readAll() {
        return this.productRepository.findAll().stream()
                .map(ProductEntity::toProduct);
    }

    @Override
    public Product update(Product product) {
        ProductEntity productEntity = this.productRepository
                .findById(product.getId())
                .orElseThrow(() -> new NotFoundException("Product id: " + product.getId()));
        productEntity.fromProduct(product);
        return this.productRepository
                .save(productEntity)
                .toProduct();
    }

    public Boolean compareTwoPrices(BigDecimal greaterPrice, BigDecimal lowerPrice) {
        int res = greaterPrice.compareTo(lowerPrice);
        return res > 0;
    }

    public List<Machine> activeMachines() {
        return this.machineRepository.findAll().stream()
                .map(machineEntity -> machineEntity.toMachine())
                .filter(machine -> machine.getActive() == true)
                .collect(Collectors.toList());
    }

    public List<Long> productsInActiveMachines() {
        return this.activeMachines().stream()
                .flatMap(pr -> pr.getProductEntities().stream())
                .map(pr -> pr.toProduct().getSerialNumber())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Long> productsWithAWholesalePriceGreaterThan(BigDecimal wholesalePrice) {
        return this.productRepository.findAll().stream()
                .filter(greaterWholesalePrice ->
                        compareTwoPrices(greaterWholesalePrice.getWholesalePrice(), wholesalePrice))
                .map(ProductEntity::getSerialNumber)
                .collect(Collectors.toList());
    }
/*
    @Override
    public Stream<Long> findProductsWithAnActiveMachineAndAWholesalePriceGreaterThan(BigDecimal wholesalePrice) {
        List<Long> productList = this.productsWithAWholesalePriceGreaterThan(wholesalePrice);
        List<Long> productsInActiveMachines = this.productsInActiveMachines();
        return productsInActiveMachines.stream()
                .filter(p -> productList.contains(p))
    }
*/
    @Override
    public Stream<Product> findProductsWithAnActiveMachineAndAWholesalePriceGreaterThan(BigDecimal wholesalePrice) {
        List<Long> productList = this.productsWithAWholesalePriceGreaterThan(wholesalePrice);
        List<Long> productsInActiveMachines = this.productsInActiveMachines();
        List<Product> x = this.productRepository.findAll().stream()
                .peek(c -> System.out.println("Current product  >>>>>  " + c))
                .map(productEntity -> productEntity.toProduct())
                .filter(product -> product.getWholesalePrice().compareTo(wholesalePrice) > 0)
                .peek(c -> System.out.println("First filter product  >>>>>  " + c.getSerialNumber()))
                .filter(product -> productsInActiveMachines.contains(product.getSerialNumber()))
                .peek(c -> System.out.println("Second filter product  >>>>>  " + c.getSerialNumber()))
                .collect(Collectors.toList());
        System.out.println("Products >>>> " + x);
        return null;
    }


}

