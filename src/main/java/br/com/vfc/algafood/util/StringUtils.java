package br.com.vfc.algafood.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtils {

    public static String toCamelCase(String snakeCase) {

        return toCamelCase(snakeCase, true);
    }

    public static String toCamelCase(String snakeCase, boolean firstInLowerCase) {

        if (!isSnakeCase(snakeCase)) {
            return snakeCase;
        }
        String camelCase = Arrays.stream(snakeCase.split("_"))
                .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase())
                .collect(Collectors.joining());

        if (firstInLowerCase) {

            camelCase = Character.toLowerCase(camelCase.charAt(0)) + camelCase.substring(1);
        }
        return camelCase;
    }

    public static String toKebabCase(String string) {
        return Arrays.stream(string.split(" "))
                .map(String::toLowerCase)
                .collect(Collectors.joining("-"));
    }

    public static boolean isSnakeCase(String string) {

        return string.contains("_");
    }
}
