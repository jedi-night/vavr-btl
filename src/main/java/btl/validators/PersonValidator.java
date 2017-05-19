package btl.validators;

import btl.models.Person;
import javaslang.collection.CharSeq;
import javaslang.collection.List;
import javaslang.control.Validation;

public class PersonValidator {

    private static final String VALID_NAME_CHARS = "[a-zA-Z ]";
    private static final int MIN_AGE = 0;

    public Validation<List<String>, Person> validatePerson(Person personne) {
        return Validation.combine(validateName(personne.name), validateAge(personne.age)).ap(Person::new);
    }


    public Validation<List<String>, Person> validatePerson(String name, int age) {
        return Validation.combine(validateName(name), validateAge(age)).ap(Person::new);
    }

    private Validation<String, String> validateName(String name) {
        return CharSeq.of(name).replaceAll(VALID_NAME_CHARS, "").transform(seq -> seq.isEmpty()
                ? Validation.valid(name)
                : Validation.invalid("Name contains invalid characters: '"
                + seq.distinct().sorted() + "'"));
    }

    private Validation<String, Integer> validateAge(int age) {
        return age < MIN_AGE
                ? Validation.invalid("Age must be at least " + MIN_AGE)
                : Validation.valid(age);
    }

}
