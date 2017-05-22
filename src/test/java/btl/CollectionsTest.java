package btl;

import javaslang.collection.List;
import javaslang.test.Arbitrary;
import javaslang.test.Property;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static javaslang.API.*;
import static javaslang.Predicates.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CollectionsTest {

    @Test
    public void list() {
        // JAVA 8
        Arrays.asList(1, 2, 3).stream().reduce((i, j) -> i + j);
        int sumJava = IntStream.of(1, 2, 3).sum();

        // VAVR
        Number sumVavr = List.of(1.0, 2.20, 3.36).sum();


    }

    @Test
    public void property() {
        // génère un nombre aléatoire de int
        Arbitrary<Integer> ints = Arbitrary.integer();

        // square(int) >= 0: OK, passed 1000 tests.
        Property.def("square(int) >= 0")
                .forAll(ints)
                .suchThat(i -> i * i >= 0)
                .check()
                .assertIsSatisfied();

    }

    @Test
    public void patternMatching() {
        String output = Match(2).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(3), "three"),
                Case($(), "?"));

        assertThat(output).isEqualTo("two");

        Object obj = 5;
        String s = Match(obj).of(
                Case($(instanceOf(String.class)), "string matched"),
                Case($(), "not string"));

        assertThat(s).isEqualTo("not string");

        int i = 5;
        String s2 = Match(i).of(
                Case($(isIn(2, 4, 6, 8)), "Even Single Digit"),
                Case($(isIn(1, 3, 5, 7, 9)), "Odd Single Digit"),
                Case($(), "Out of range"));

        Integer year = 1990;
        String s3 = Match(year).of(
                Case($(noneOf(isIn(1990, 1991, 1992), is(1986))), "Age match"),
                Case($(), "No age match"));


    }
}
