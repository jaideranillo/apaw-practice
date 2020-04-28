package es.upm.miw.apaw_practice.business.shop;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.data.shop.daos.TagRepository;
import es.upm.miw.apaw_practice.data.shop.entities.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;

@TestConfig
class TagServiceIT {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagService tagService;

    @Test
    void testDelete() {
        Tag oldTag = this.tagRepository.findById("tag3").get();
        this.tagService.delete("tag3");
        assertFalse(this.tagRepository.findById("tag3").isPresent());
        this.tagRepository.save(oldTag);
    }
}
