package es.upm.miw.apaw_practice.adapters.rest.videoclub;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.videoclub.CustomerAssociate;
import es.upm.miw.apaw_practice.domain.services.videoclub.CustomerAssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(CustomerAssociateResource.CUSTOMERASSOCIATES)
public class CustomerAssociateResource {

    static final String CUSTOMERASSOCIATES = "/videoclub/customers";
    static final String DOCUMENT_DOCUMENT = "/{document_id}";
    static final String NAME_NAME = "/name";
    static final String SEARCH = "/search";

    private CustomerAssociateService customerAssociateService;

    @Autowired
    public CustomerAssociateResource(CustomerAssociateService customerAssociateService) {
        this.customerAssociateService = customerAssociateService;
    }

    @PutMapping(DOCUMENT_DOCUMENT + NAME_NAME)
    public CustomerAssociate updateName(@PathVariable String document_id, @RequestBody NameDto nameDto) {
        return this.customerAssociateService.updateName(document_id, nameDto.getName());
    }

    @DeleteMapping(DOCUMENT_DOCUMENT)
    public void updateName(@PathVariable String document_id) {
        this.customerAssociateService.delete(document_id);
    }

    @GetMapping(SEARCH)
    public Stream<NameDto> findDistinctNameByFilmMaker(@RequestParam String q) {
        String filmMakerName = new LexicalAnalyzer().extractWithAssure(q, "name");
        return this.customerAssociateService.findDistinctNameByFilmMaker(filmMakerName)
                .map(NameDto::new);
    }
}
