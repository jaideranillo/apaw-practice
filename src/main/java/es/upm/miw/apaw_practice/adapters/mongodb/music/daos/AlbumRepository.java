package es.upm.miw.apaw_practice.adapters.mongodb.music.daos;

import org.springframework.data.mongodb.repository.MongoRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music.entities.AlbumEntity;

import java.util.Optional;

public interface AlbumRepository extends MongoRepository<AlbumEntity,String> {
    Optional<AlbumEntity> findByIsmn(String ismn);
}
