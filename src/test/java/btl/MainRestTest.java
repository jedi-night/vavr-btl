package btl;

import btl.enums.Agence;
import javaslang.Tuple;
import javaslang.Tuple3;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class MainRestTest {

    private static final Logger LOGGER = Logger.getLogger(MainRestTest.class.getName());

    public void tupples() {
        Tuple3<String, Integer, String> netapsysBretagne = Tuple.of("Netapsys", Agence.NETAPSYS_BRETAGNE.getCode(), Agence.NETAPSYS_BRETAGNE.getLibelle());
        Tuple3<String, Integer, String> sodifranceRennes = Tuple.of("Sodifrance", Agence.SODIFRANCE_RENNES.getCode(), Agence.SODIFRANCE_RENNES.getLibelle());

        // type
        String typeNetapsysBretagne = netapsysBretagne._1;
        String typeSodifranceRennes = sodifranceRennes._1;

        // code
        Integer codeNetapsysBretagne = netapsysBretagne._2;
        Integer codeSodifranceRennes = sodifranceRennes._2;

        // nom
        String nomNetapsysBretagne = netapsysBretagne._3;
        String nomSodifranceRennes = sodifranceRennes._3;
    }
}