package es.upm.miw.apaw_practice.adapters.mongodb.videoclub.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.videoclub.daos.CustomerAssociateRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videoclub.entities.CustomerAssociateEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.videoclub.CustomerAssociate;
import es.upm.miw.apaw_practice.domain.models.videoclub.RentalFilm;
import es.upm.miw.apaw_practice.domain.persistence_ports.videoclub.CustomerAssociatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository("customerAssociatePersistence")
public class CustomerAssociatePersistenceMongodb implements CustomerAssociatePersistence {

    private CustomerAssociateRepository customerAssociateRepository;

    @Autowired
    public CustomerAssociatePersistenceMongodb(CustomerAssociateRepository customerAssociateRepository) {
        this.customerAssociateRepository = customerAssociateRepository;
    }

    @Override
    public CustomerAssociate updateName(String documentId, String name) {
        CustomerAssociateEntity customerAssociateEntity = this.customerAssociateRepository
                .findByDocumentId(documentId)
                .orElseThrow(() -> new NotFoundException("Document_id: " + documentId));
        customerAssociateEntity
                .setName(name);
        return this.customerAssociateRepository
                .save(customerAssociateEntity)
                .toCustomerAssociate();
    }

    @Override
    public CustomerAssociate readByDocumentId(String documentId) {
        return this.customerAssociateRepository
                .findByDocumentId(documentId)
                .orElseThrow(() -> new NotFoundException("Customer document id: " + documentId))
                .toCustomerAssociate();
    }

    @Override
    public void deleteByDocumentId(String documentId) {
        this.customerAssociateRepository
                .findByDocumentId(documentId)
                .orElseThrow(() -> new NotFoundException("Customer document id: " + documentId));
        this.customerAssociateRepository.deleteByDocumentId(documentId);
    }

    @Override
    public Stream<CustomerAssociate> readAll() {
        return this.customerAssociateRepository
                .findAll().stream()
                .map(CustomerAssociateEntity::toCustomerAssociate);
    }

    @Override
    public Stream<String> findDistinctNameByRentalFilms(List<String> rentalFilmList) {
        return this.customerAssociateRepository.findAll().stream()
                .map(CustomerAssociateEntity::toCustomerAssociate)
                .filter(customerAssociate -> customerAssociate.getFilms().stream()
                        .map(RentalFilm::getReference)
                        .anyMatch(rentalFilmList::contains))
                .distinct()
                .map(CustomerAssociate::getName);
    }
}
