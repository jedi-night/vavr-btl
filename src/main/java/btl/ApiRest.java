package btl;

import btl.services.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public final class ApiRest {

    @Autowired PersonneService personneService;

    @RequestMapping(value = "api", method = RequestMethod.GET)
    @ResponseBody
    public String documents(@RequestParam("ticket") String ticket) {
        return "Authentification via token effectué (" + ticket + ")";
    }

    @ResponseStatus(HttpStatus.SEE_OTHER)
    @ResponseBody
    @RequestMapping(value = "authentification/{idu}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> redirectToExternalUrl(@PathVariable("idu") final String idu) throws URISyntaxException {
        URI cas = new URI("http://localhost:3000/cas/login?service=http://localhost:80/api&idu=" + idu);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(cas);
        httpHeaders.setOrigin("http://localhost:80/api");
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
}
