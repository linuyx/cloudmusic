package com.hanfz.utils;

import java.util.Random;

/**
 * @Author Linuyx
 * @Description 工具类
 * @Date Created in 2021-03-17 15:35
 */
public class LinuyxUtils {

    /**
     * 生成随机盐
     *
     * @param n 随机盐的长度
     * @return 随机盐
     */
    public static String getSalt(int n) {
        char[] chars = "ABCDEFGHIJKIMNOPQISTUVWXYZabcdefghijkimnopqistuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            builder.append(chars[random.nextInt(chars.length)]);
        }
        return builder.toString();
    }

    /**
     * 二分查找(返回查找元素所在数组中的位置,不存在返回-1)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            //如要防止计算溢出,可以改为: left + (right - left) / 2
            int mid = (left + right) / 2;

            if(nums[mid] == target){
                return mid;
            }

            if(target > nums[mid]){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 插入排序
     *
     * @param arr 需要排序的数组
     * @return
     */
    public static int[] insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int index = i;
            int temp;
            for (int j = i - 1; j >= 0; j--) {
                if (index == i && arr[i] > arr[j]) {
                    break;
                }

                if (arr[index] < arr[j]) {
                    temp = arr[index];
                    arr[index] = arr[j];
                    arr[j] = temp;
                    index--;
                }
            }
        }
        return arr;
    }

}
