package btl;

import javaslang.collection.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/documents")
public final class MainRest {

    private static final List<String> VALUES = List.of("Manon", "Vincent", "Jean-Baptiste", "Sébastien");
    private static final java.util.List<String> VALUES_JAVA_8 = VALUES.toJavaList();

    @RequestMapping(value="vavr", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> documents() {
        return VALUES.filter(el -> el.contains("i"));
    }

    @RequestMapping(value="java", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public java.util.List<String> documentsOld() {
        return VALUES_JAVA_8
                .stream()
                .filter(el -> el.contains("i"))
                .collect(Collectors.toList());
    }
}
