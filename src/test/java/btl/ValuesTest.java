package btl;

import javaslang.Lazy;
import javaslang.control.Option;
import javaslang.control.Try;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static javaslang.API.Match;
import static org.assertj.core.api.Assertions.assertThat;

public class ValuesTest {

    @Test
    public void option() {

        // JAVA 8
        Optional<String> maybeFoo = Optional.of("foo");
        assertThat(maybeFoo.get()).isEqualTo("foo");

        Optional<String> maybeFooBar = maybeFoo
                .map(s -> (String) null)
                .map(s -> s.toUpperCase() + "bar");
        assertThat(maybeFooBar.isPresent()).isFalse();

        // Vavr
        // NullPointer (ca oblige à faire gaffe aux null)
        Option<String> maybeFoo = Option.of("foo");
        assertThat(maybeFoo.get()).isEqualTo("foo");
        try {
            maybeFoo.map(s -> (String)null)
                    .map(s -> s.toUpperCase() + "bar");
            Assert.fail();
        } catch (NullPointerException e) {
            // pas la bonne approche
        }

        // du coup on fait ça

        Option<String> maybeFoo = Option.of("foo");
        assertThat(maybeFoo.get()).isEqualTo("foo");

        Option<String> maybeFooBar = maybeFoo
                .map(s -> (String)null)
                .flatMap(s -> Option.of(s)
                .map(t -> t.toUpperCase() + "bar"));
        assertThat(maybeFooBar.isEmpty()).isTrue();

        // petite simplification
        Option<String> maybeFoo = Option.of("foo");
        assertThat(maybeFoo.get()).isEqualTo("foo");

        Option<String> maybeFooBar = maybeFoo
                .flatMap(s -> Option.of((String)null))
                .map(s -> s.toUpperCase() + "bar");
        assertThat(maybeFooBar.isEmpty()).isTrue();
    }

    String bunchOfWork() throws Exception {
        throw new Exception();
    }

    @Test
    public void trY() {

        // pas besoin de catcher les exceptions
        String result = Try.of(() -> bunchOfWork()).getOrElse("No results ...");

    }

    @Test
    public void lazy() {

        Lazy<Double> lazy = Lazy.of(Math::random);
        lazy.isEvaluated(); // = false
        lazy.get();         // = 0.123 (random generated)
        lazy.isEvaluated(); // = true
        lazy.get();         // = 0.123 (memoized)
    }

}
