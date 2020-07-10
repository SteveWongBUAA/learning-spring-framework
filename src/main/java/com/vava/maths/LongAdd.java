package com.vava.maths;

/**
 * @author Steve
 * Created on 2020-06
 * 大数相加，支持负数，利用补码原理，参考 https://blog.csdn.net/u013565368/article/details/51106330
 */
public class LongAdd {

    public String strAdd(String a, String b) {
        // 先处理 负数
        boolean aNegative = false;
        boolean bNegative = false;
        if (a.charAt(0) == '-') {
            aNegative = true;
            a = a.substring(1);
        }
        if (b.charAt(0) == '-') {
            bNegative = true;
            b = b.substring(1);
        }
        // 结果的长度，先取两者更长的，+1进位，+1符号位
        int maxLen = Math.max(a.length(), b.length());
        maxLen += 2;
        int[] aArr = new int[maxLen];
        int[] bArr = new int[maxLen];
        // 转换成int数组
        int i;
        for (i = 1; i <= a.length(); i++) {
            aArr[maxLen - i] = a.charAt(a.length() - i) - '0';
        }
        if (aNegative) {
            aArr[0] = 9;
        } else {
            aArr[0] = 0;
        }
        for (i = 1; i <= b.length(); i++) {
            bArr[maxLen - i] = b.charAt(b.length() - i) - '0';
        }
        if (bNegative) {
            bArr[0] = 9;
        } else {
            bArr[0] = 0;
        }
        // 补码相加
        int[] aComplement = complement(aArr);
        int[] bComplement = complement(bArr);

        int[] resArr = addArr(aComplement, bComplement);
        return arr2Str(resArr);
    }

    private String arr2Str(int[] arr) {
        StringBuilder sb = new StringBuilder();
        if (arr[0] == 9) {
            sb.append("-");
            arr = complement(arr);
        }
        for (int i = 1; i< arr.length; i++) {
            if (arr[i] == 0) continue;
            sb.append((char)(arr[i]+'0'));
        }
        return sb.toString();
    }

    private int[] addArr(int[] a, int[] b) {
        int carry = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            a[i] += b[i];
            a[i] += carry;
            carry = a[i] / 10;
            a[i] %= 10;
        }
        return a;
    }

    public int[] complement(int[] arr) {
        if (arr[0] == 0) {
            // 正数补码是自己
            return arr;
        } else {
            // 负数补码是取反+1
            for (int i = 1; i < arr.length; i++) {
                arr[i] = 9 - arr[i];
            }
            int[] tmp1 = new int[arr.length];
            tmp1[tmp1.length-1] = 1;
            addArr(arr, tmp1);
        }
        return arr;
    }

    public static void main(String[] args) {
        LongAdd la = new LongAdd();
        String s = la.strAdd("123", "456");
        System.out.println(s);
        System.out.println(s.equals("579"));

        s = la.strAdd("-123", "-456");
        System.out.println(s);
        System.out.println(s.equals("-579"));

        s = la.strAdd("-123", "456");
        System.out.println(s);
        System.out.println(s.equals("333"));

        s = la.strAdd("123", "-456");
        System.out.println(s);
        System.out.println(s.equals("-333"));

        s = la.strAdd("999", "-0");
        System.out.println(s);
        System.out.println(s.equals("999"));

    }
}
