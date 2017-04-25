package btl;

import javaslang.collection.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class MainRest {

    private static final List<String> VALUES = List.of("Manon", "Vincent", "Jean-Baptiste", "Sébastien");
    private static final java.util.List<String> VALUES_JAVA_8 = VALUES.toJavaList();

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/documents")
    @ResponseBody
    public List<String> documents() {
        return VALUES.filter(el -> el.contains("ti"));
    }

    @RequestMapping(path = "/documents-old", method = RequestMethod.GET)
    @ResponseBody
    public java.util.List<String> documentsOld() {
        return VALUES_JAVA_8
                .stream()
                .filter(el -> el.contains("ti"))
                .collect(Collectors.toList());
    }
}
