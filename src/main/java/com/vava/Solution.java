package com.vava;


/**
 * @author Steve
 * Created on 2020-05
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = {1, 3, 5, 7, 9};
        int lastLessEqual = s.findLastLessEqual(arr, 1);
        System.out.println(arr[lastLessEqual] + " 1");

        lastLessEqual = s.findLastLessEqual(arr, 2);
        System.out.println(arr[lastLessEqual] + " 1");

        lastLessEqual = s.findLastLessEqual(arr, 3);
        System.out.println(arr[lastLessEqual] + " 3");

        lastLessEqual = s.findLastLessEqual(arr, 4);
        System.out.println(arr[lastLessEqual] + " 3");

        lastLessEqual = s.findLastLessEqual(arr, 5);
        System.out.println(arr[lastLessEqual] + " 5");

        lastLessEqual = s.findLastLessEqual(arr, 9);
        System.out.println(arr[lastLessEqual] + " 9");

        lastLessEqual = s.findLastLessEqual(arr, 10);
        System.out.println(arr[lastLessEqual] + " 9");

        //        int [][] a=new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int[][] a = new int[][] {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}};
        s.searchMatrix(a, 19);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        // 先横着找找到最后一个小于等于target的
        int idx = findLastLessEqual(matrix[0], target);
        if (idx == -1) {
            return false;
        }
        if (matrix[0][idx] == target) {
            return true;
        }

        // 在这一列竖着找
        return find(matrix, idx, target);
    }

    public int findLastLessEqual(int[] row, int target) {
        int start = 0;
        int end = row.length - 1;
        while (start <= end) {
            int mid = (end + start) / 2;
            if (row[mid] == target) {
                return mid;
            } else if (row[mid] < target) {
                if (mid == row.length - 1 || row[mid + 1] > target) {
                    return mid;
                }
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public boolean find(int[][] matrix, int idx, int target) {
        int start = 0;
        int end = matrix.length - 1;
        while (start <= end) {
            int mid = (end + start) / 2;
            if (matrix[mid][idx] == target) {
                return true;
            } else if (matrix[mid][idx] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }
}
