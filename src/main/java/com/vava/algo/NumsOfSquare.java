package com.vava.algo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Steve
 * Created on 2020-07
 */
public class NumsOfSquare {

    private Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        NumsOfSquare numsOfSquare = new NumsOfSquare();
        System.out.println(numsOfSquare.numSquares(12));

    }

    public int numSquares(int n) {
        if (n == 0) {
            return 0;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        // 找到最接近的平方数开始
        int m = (int) Math.sqrt(n);
        int res = Integer.MAX_VALUE;
        for (int i = m; i >= 1; i--) {
            res = Math.min(res, numSquares(n - i * i));
        }
        map.put(n, res + 1);
        return res + 1;
    }
}
