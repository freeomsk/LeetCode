package leetcode._0004;

/*
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).

Даны два отсортированных массива nums1 и nums2 размера m и n соответственно.
Вернуть медиану двух отсортированных массивов.
Общая сложность времени выполнения должна быть O(log (m+n)).

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

 */

public class FindMedianSortedArrays {

    public static void main(String[] args) {
//        int[] nums1 = {1, 3};
//        int[] nums2 = {2};
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    // Runtime - 2 ms
    // Beats - 100%
    // Memory - 44,7
    // beats - 8,11%
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        // If nums1 is larger than nums2, swap them to ensure n1 is smaller than n2.
        if (n1 > n2) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int l = 0;
        int r = n1;
        while (l <= r) {
            int mid1 = (l + r) / 2;
            int mid2 = (n1 + n2 + 1) / 2 - mid1;

            int maxLeft1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[mid1 - 1];
            int minRight1 = (mid1 == n1) ? Integer.MAX_VALUE : nums1[mid1];

            int maxLeft2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[mid2 - 1];
            int minRight2 = (mid2 == n2) ? Integer.MAX_VALUE : nums2[mid2];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                if ((n1 + n2) % 2 == 0) {
                    return (double) (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2;
                } else {
                    return Math.max(maxLeft1, maxLeft2);
                }
            } else if (maxLeft1 > minRight2) {
                r = mid1 - 1;
            } else {
                l = mid1 + 1;
            }
        }

        return -1;
    }
}