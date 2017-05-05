package btl;

import btl.services.PersonneService;
import javaslang.collection.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/personnes")
public final class MainRest {

    @Autowired private PersonneService personneService;

    @RequestMapping(value = "vavr/agence/{idAgence}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> documents(@PathVariable("idAgence") final Integer idAgence) {
        return personneService.getAllPersonnesByAgence(idAgence).map(personne -> personne.getNom() + " " + personne.getPrenom());
    }

    @RequestMapping(value = "java/agence/{idAgence}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public java.util.List<String> documentsOld(@PathVariable("idAgence") final Integer idAgence) {
        return personneService.getJavaAllPersonnesByAgence(idAgence)
                .stream()
                .map(personne -> personne.getNom() + " " + personne.getPrenom())
                .collect(Collectors.toList());
    }
}
