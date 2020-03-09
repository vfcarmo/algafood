package br.com.vfc.algafood.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {

    public int solutionSimulation(int[] A) {

        Set<Integer> positives = Arrays.stream(A)
                .filter(number -> number  > 0)
                .boxed()
                .collect(Collectors.toSet());

        for (int i = 1; i <= A.length + 1; i++) {
            if (!positives.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 1; j < A.length; j++) {
                if (A[i] == A[j] && j > i) {
                    result++;
                }
            }
        }
        return result;
    }

    public int solution(int N) {
        // write your code in Java SE 8
        int result = 0;
        String value = "" + Math.pow(11, N);
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == '1') {
                result++;
            }
        }

        int MAX_VALUE = 100000000;
        if (new BigDecimal(value).intValue() > MAX_VALUE) {
            return MAX_VALUE;
        }
        return result;
    }


}
