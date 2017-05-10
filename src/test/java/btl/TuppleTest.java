package btl;

import btl.enums.Agence;
import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.Tuple3;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

public class TuppleTest {

    private static final Logger LOGGER = Logger.getLogger(TuppleTest.class.getName());

    @Before
    public void setup() {
        System.out.println("***** TUPPLES *****");
    }

    @Test
    public void tupples() {
        Tuple3<String, Integer, String> netapsysBretagne = Tuple.of("Netapsys", Agence.NETAPSYS_BRETAGNE.getCode(), Agence.NETAPSYS_BRETAGNE.getLibelle());

        // getters
        String typeNetapsysBretagne = netapsysBretagne._1;
        Integer codeNetapsysBretagne = netapsysBretagne._2;
        String nomNetapsysBretagne = netapsysBretagne._3;

        System.out.println(netapsysBretagne.toString());

        // un par un
        Tuple3<String, Integer, String> map = netapsysBretagne.map(
                type -> "Alkéris " + "(" + type + ")",
                code -> code,
                libelle -> libelle
        );

        System.out.println(map.toString());

        // en meme temps
        Tuple3<String, Integer, String> map1 = netapsysBretagne.map(
                (type, code, libelle) -> Tuple.of("Alkéris " + "(" + type + ")", code, libelle)
        );

        System.out.println(map1.toString());

        // transform

        Tuple2<String, String> transform = netapsysBretagne.transform(
                (type, code, libelle) -> Tuple.of("Alkéris " + "(" + type + "-" + code + ")", libelle)
        );

        System.out.println(transform.toString());
    }
}