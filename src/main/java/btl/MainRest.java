package btl;

import btl.enums.Agence;
import btl.services.PersonneService;
import javaslang.collection.List;
import javaslang.collection.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/personnes")
public final class MainRest {

    @Autowired PersonneService personneService;

//    @RequestMapping(value="vavr/{prenomPersonne}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public List<String> documents(@PathVariable("prenomPersonne") final String prenomPersonne) {
//        return personneService.getAllPersonnesByAgence(Agence.NETAPSYS_BRETAGNE.getCode()).transform(tuple2s -> tuple2s.);
//    }
//
//    @RequestMapping(value="java/{prenomPersonne}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public java.util.List<String> documentsOld(@PathVariable("prenomPersonne") final String prenomPersonne) {
//        return VALUES_JAVA_8
//                .entrySet()
//                .stream()
//                .filter(el -> el.getKey())
//                .map(el -> el.getValue().getNom())
//                .collect(Collectors.toList());
//    }
}
