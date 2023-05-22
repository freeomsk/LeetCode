package leetcode._0001;

/*
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.

Дан массив целых чисел nums и целое число target.
Вернуть индексы двух чисел так, чтобы при их сложении получилось число target.
Предполагается, что каждый вход будет иметь ровно одно решение и нельзя использовать один и тот же элемент дважды.
Можно вернуть ответ в любом порядке.

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]

Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]

Constraints:

    2 <= nums.length <= 104
    -109 <= nums[i] <= 109
    -109 <= target <= 109
    Only one valid answer exists.


Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
Дополнение: можете ли вы придумать алгоритм, временная сложность которого меньше O(n2)?
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
//        int[] nums = {2, 7, 11, 15}; // 0, 1
//        int target = 9;
//        int[] nums = {3, 2, 4};
//        int target = 6;
        int[] nums = {3, 2, 3}; // [0, 2]
        int target = 6;
//        int[] nums = {2, 5, 5, 11}; // 1, 2
//        int target = 10;

        System.out.println(Arrays.toString(twoSum(nums, target)));
        System.out.println(Arrays.toString(twoSum2(nums, target)));
        System.out.println(Arrays.toString(twoSum3(nums, target)));
    }

    // 1 вариант
    // Runtime - 186 ms
    // Beats - 5,47%
    // Memory - 43,8
    // beats - 5,96%
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target & i != j) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    // 2 вариант
    // Runtime - 2 ms
    // Beats - 80,41%
    // Memory - 43,8
    // beats - 5,96%
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    // 3 вариант
    // Runtime - 85 ms
    // Beats - 25,46%
    // Memory - 43,8
    // beats -5,96%
    public static int[] twoSum3(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }
}