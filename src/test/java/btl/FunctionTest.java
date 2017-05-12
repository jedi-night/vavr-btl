package btl;

import javaslang.*;
import javaslang.control.Option;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

public class FunctionTest {

    private static final Logger LOGGER = Logger.getLogger(FunctionTest.class.getName());

    @Before
    public void setup() {
        System.out.println("***** FUNCTIONS *****");
    }

    @Test
    public void functions() {

        // ***********
        // Composition
        // ***********
        System.out.println("\n***** Composition *****\n");

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
        System.out.println("\n***** Lifting *****\n");

        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;

        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);


        // = None
        Option<Integer> i1 = safeDivide.apply(1, 0);

        System.out.println("1 / 0 = " + i1.getOrElse(-1));

        // = Some(2)
        Option<Integer> i2 = safeDivide.apply(4, 2);

        System.out.println("4 / 2 = " + i2.getOrElse(-1));


        // ***********
        // Partial application
        // ***********
        System.out.println("\n***** Partial application *****\n");

        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Function1<Integer, Integer> add2 = sum.apply(2);

        result = add2.apply(4);

        System.out.println("2 + 4 = " + result);

        Function5<Integer, Integer, Integer, Integer, Integer, Integer> sumOf5Numbers = (a, b, c, d, e) -> a + b + c + d + e;
        Function2<Integer, Integer, Integer> add6 = sumOf5Numbers.apply(2, 3, 1);

        result = add6.apply(6, 5);

        System.out.println("6 + 6 + 5 = " + result);

        // ***********
        // Currying
        // ***********
        System.out.println("\n***** Currying *****\n");

        // pareil qu'au dessus
        Function2<Integer, Integer, Integer> sumOf2Numbers = (a, b) -> a + b;
        add2 = sumOf2Numbers.curried().apply(2);

        result = add2.apply(4);

        System.out.println("2 + 4 = 17 => " + result);

        // Plus clair ici
        Function3<Integer, Integer, Integer, Integer> sumOf3numbers = (a, b, c) -> a + b + c;
        final Function1<Integer, Function1<Integer, Integer>> add2Bis = sumOf3numbers.curried().apply(2);

        result = add2Bis.apply(4).apply(3);

        System.out.println("2 + 4 + 3 = 9 => " + result);

    }
}