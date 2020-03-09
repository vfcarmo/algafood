package br.com.vfc.algafood.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @Test
    void shouldReturnMinElementInArray() {

        int[] input = { 1, 3, 6, 4, 1, 2 };
        int expected = 5;

        int actual = new Solution().solutionSimulation(input);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnNumberOfPairs() {

        int[] input = { 3, 5, 6, 3, 3, 5 };
        int expected = 4;

        int actual = new Solution().solution(input);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideIntsforSolution")
    void shouldReturnNumbersOfOne() {

        int maxValue = 100000000;

        int input = 3;
        int expected = 2;

        int actual = new Solution().solution(input);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideIntsforSolution() {
        return Stream.of(
                Arguments.of(3, 2),
                Arguments.of(5, 3),
                Arguments.of(11, 100000000)
        );
    }
}