package btl;

import btl.enums.Agence;
import javaslang.*;
import javaslang.control.Option;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

public class MainRestTest {

    private static final Logger LOGGER = Logger.getLogger(MainRestTest.class.getName());

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

    @Test
    public void functions() {

        // ***********
        // Composition
        // ***********

        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

        // soit comme ça
        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);

        // soit en compose
        Function1<Integer, Integer> add1AndMultiplyBy2Bis = multiplyByTwo.compose(plusOne);

        Integer number = 5;
        Integer result = add1AndMultiplyBy2Bis.apply(number);

        System.out.println("(" + number + " + 1) * 2 = " + result);


        // ***********
        // Lifting
        // ***********
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;

        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);


        // = None
        Option<Integer> i1 = safeDivide.apply(1, 0);

        System.out.println("1 / 0 = " + i1.getOrElse(-1));

        // = Some(2)
        Option<Integer> i2 = safeDivide.apply(4, 2);

        System.out.println("4 / 2 = " + i2.getOrElse(-1));

    }
}