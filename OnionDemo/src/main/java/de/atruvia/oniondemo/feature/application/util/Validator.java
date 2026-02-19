package de.atruvia.oniondemo.feature.application.util;




import java.util.ArrayList;

import java.util.List;

import java.util.function.Function;

import java.util.function.Predicate;



public class Validator<T> {

    private final T value;

    private final List<String> errors = new ArrayList<>();



    private Validator(T value) {

        this.value = value;

    }



    public static <T> Validator<T> of(T value) {

        return new Validator<>(value);

    }



    /**

     * Sammelt Fehler in einer Liste, ohne den Fluss sofort zu unterbrechen.

     */

    public Validator<T> validate(Predicate<T> predicate, String errorMessage) {

        if (value == null) {

            if (errorMessage.toLowerCase().contains("null")) errors.add(errorMessage);

        } else if (!predicate.test(value)) {

            errors.add(errorMessage);

        }

        return this;

    }



    /**

     * Wirft gesammelte Fehler als eine Exception.

     */

    public Validator<T> throwIfInvalid(Function<String, RuntimeException> exceptionSupplier) {

        if (!errors.isEmpty()) {

            throw exceptionSupplier.apply(String.join(", ", errors));

        }

        return this;

    }



    /**

     * Prüft eine Bedingung und wirft SOFORT eine spezifische Exception.

     * Ideal für Datenbank-Checks (AlreadyExists / NotFound).

     */

    public Validator<T> checkOrThrow(Predicate<T> predicate, Function<T, RuntimeException> exceptionSupplier) {

        if (value != null && !predicate.test(value)) {

            throw exceptionSupplier.apply(value);

        }

        return this;

    }

}
