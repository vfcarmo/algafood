package br.com.vfc.algafood.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void shouldConvertFromSnakeCaseToCamelCase() {

        String input = "default_payment_method";
        String expected = "defaultPaymentMethod";

        String actual = StringUtils.toCamelCase(input);

        assertEquals(expected, actual);
    }

    @Test
    void shouldConvertFromSnakeCaseToCamelCaseWithAllFirstCharacterInUpperCase() {

        String input = "default_payment_method";
        String expected = "DefaultPaymentMethod";

        String actual = StringUtils.toCamelCase(input, false);

        assertEquals(expected, actual);
    }

    @Test
    void shouldDoNothingWhenTryingToConvertFromCamelCase() {

        String input = "defaultPaymentMethod";
        String expected = "defaultPaymentMethod";

        String actual = StringUtils.toCamelCase(input);

        assertEquals(expected, actual);
    }

    @Test
    void shouldConvertFromStringWith3WordsToKebabCase() {

        String input = "Unsupported Media Type";
        String expected = "unsupported-media-type";

        String actual = StringUtils.toKebabCase(input);

        assertEquals(expected, actual);
    }

    @Test
    void shouldConvertFromStringWith1WordToKebabCase() {

        String input = "Conflict";
        String expected = "conflict";

        String actual = StringUtils.toKebabCase(input);

        assertEquals(expected, actual);
    }
}