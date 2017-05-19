package btl;

import btl.models.Person;
import btl.validators.PersonValidator;
import javaslang.Lazy;
import javaslang.collection.List;
import javaslang.concurrent.Future;
import javaslang.control.Either;
import javaslang.control.Option;
import javaslang.control.Try;
import javaslang.control.Validation;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
        Option<String> maybeFooVavr = Option.of("foo");
        assertThat(maybeFooVavr.get()).isEqualTo("foo");
        try {
            maybeFooVavr.map(s -> (String)null)
                    .map(s -> s.toUpperCase() + "bar");
            Assert.fail();
        } catch (NullPointerException e) {
            // pas la bonne approche
        }

        // du coup on fait ça

        maybeFooVavr = Option.of("foo");
        assertThat(maybeFooVavr.get()).isEqualTo("foo");

        Option<String> maybeFooBarVavr = maybeFooVavr
                .map(s -> (String)null)
                .flatMap(s -> Option.of(s)
                .map(t -> t.toUpperCase() + "bar"));
        assertThat(maybeFooBarVavr.isEmpty()).isTrue();

        // petite simplification
        maybeFooVavr = Option.of("foo");
        assertThat(maybeFooVavr.get()).isEqualTo("foo");

        maybeFooBarVavr = maybeFooVavr
                .flatMap(s -> Option.of((String)null))
                .map(s -> s.toUpperCase() + "bar");
        assertThat(maybeFooBarVavr.isEmpty()).isTrue();
    }

    @Test
    public void trY() {

        // pas besoin de catcher les exceptions
        String result = Try.of(() -> bunchOfWork()).getOrElse("No results ...");

    }

    String bunchOfWork() throws Exception {
        throw new Exception();
    }

    @Test
    public void lazy() {

        Lazy<Double> lazy = Lazy.of(Math::random);
        lazy.isEvaluated(); // = false
        lazy.get();         // = 0.123 (random generated)
        lazy.isEvaluated(); // = true
        lazy.get();         // = 0.123 (memoized)

    }

    @Test
    public void either() {
        System.out.println("Either true :");

        Either<String, Integer> compute = compute(true);
        if(compute.isRight()){
            System.out.print("Success !");
        } else {
            System.out.print(compute.getLeft());
        }

        System.out.println("\nEither false :");
        compute = compute(false);
        if(compute.isRight()){
            System.out.print("Success !");
        } else {
            System.out.print(compute.getLeft());
        }
    }

    private Either<String,Integer> compute(boolean status) {
        if(status){
            return Either.right(200);
        }
        return Either.left("Serveur inaccessible");
    }

    @Test
    public void futureSuccess() throws InterruptedException {
        final long sleepMillis = 1500;

        // SUCCESS
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            Try.run(() -> Thread.sleep(sleepMillis));
            Option.of(-10).toTry().flatMap(t -> Try.run(() -> foo(t))).get();
        });

        Future<Void> future = Future.run(() -> Thread.sleep(sleepMillis)).onComplete(ignored -> System.out.println("Slang future completed " + ignored));// #1

        executorService.shutdown();
        executorService.awaitTermination(2, TimeUnit.SECONDS);

        System.out.println("Normal termination? " + future.isSuccess());

    }

    private Integer foo(Integer t) throws Exception {
        return t * 2;
    }

    @Test
    public void futureError() throws InterruptedException {
        final long sleepMillis = 1500;

        // SUCCESS
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            Try.run(() -> Thread.sleep(sleepMillis));
            Option.of(-10).toTry().flatMap(t -> Try.run(() -> fooFailure(t))).get();
        });

        Future<Void> future = Future.run(() -> Thread.sleep(sleepMillis)).onComplete(ignored -> System.out.println("Slang future completed " + ignored));// #1

        executorService.shutdown();
        executorService.awaitTermination(2, TimeUnit.SECONDS);

        System.out.println("Normal termination? " + future.getValue());

    }

    private static Integer fooFailure(Integer t) throws Exception {
        throw new Exception("Bad request");
    }

    @Test
    public void validation() {
        PersonValidator personValidator = new PersonValidator();

        // Valid(Person(John Doe, 30))
        Validation<List<String>, Person> valid = personValidator.validatePerson("John Doe", 30);

        // Invalid(List(Name contains invalid characters: '!4?', Age must be greater than 0))
        Validation<List<String>, Person> invalid = personValidator.validatePerson("John? Doe!4", -1);

    }

}
