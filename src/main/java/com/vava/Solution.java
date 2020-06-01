package com.vava;


/**
 * @author Steve
 * Created on 2020-05
 *     这种需要先看例子找规律，观察几个例子：
 *     1.如果结尾是升序，如 123，那好办得很，直接将最后两位交换，132，搞定
 *     2.如果是纯逆序，如 321，需要整体翻转，123
 *     3.如果先升后降，如 12321，就有点麻烦了，我们感觉应该要找到转折点 12|3|21，然后需要将第一个2，替换掉。替换成啥呢？为了保证题意，应该是他后面的，在他后面一个位置的，也就是3，替换后：13|221
 *     ，需要再把后面的221升序排起来，13122
 *     4.如果反复横跳，升降升降的，如 13|5|2|4|1，发现跟3类似，只要处理最后一次先升后降，也就是241，将2替换成4，然后升序排，135412。
 *
 *     于是大概的我们可以总结算法：
 *     1、找到最后一个升-》降
 *     2、替换，然后后面升序排序。
 *
 *
 *
 */
class Solution {
    public void nextPermutation(int[] nums) {
        int toSwap = findToSwapIndex(nums);
        if (toSwap == -1) {
            // 纯升序
            reverse(nums, 0);
            return;
        }
        // 后往前 找第一个大于 要交换的元素
        for (int i = nums.length-1; i > toSwap; i--) {
            if (nums[i] > nums[toSwap]) {
                swap(nums, i, toSwap);
                // 交换后面的位置，升序排列，也就是翻转
                reverse(nums, toSwap+1);
                return;
            }
        }
        //如果没有找到可以交换的，说明是纯升序，需要全体翻转
        reverse(nums, toSwap);
    }


    private int findToSwapIndex(int[] nums) {
        for (int j = nums.length - 1; j > 0; j--) {
            // 想要交换的元素
            if (nums[j-1] < nums[j]) {
                return j - 1;
            }
        }
        // 找不到，返回
        return -1;

    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }


}
