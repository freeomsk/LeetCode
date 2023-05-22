package leetcode._0003;

/*
Given a string s, find the length of the longest substring without repeating characters.

Для заданной строки s найдите длину самой длинной подстроки без повторяющихся символов.

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstring2(s));
        System.out.println(lengthOfLongestSubstring3(s));
    }

    // 1 вариант
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {

            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                maxLength = Math.max(maxLength, right - left + 1);

            } else {
                while (s.charAt(left) != s.charAt(right)) {
                    set.remove(s.charAt(left));
                    left++;
                }
                set.remove(s.charAt(left));
                left++;
                set.add(s.charAt(right));
            }
        }
        return maxLength;
    }

    // 2 вариант
    public static int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        int j = 0;
        int max = 0;
        while (j < s.length()) {
            map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
            if (map.size() == j - i + 1) {
                max = Math.max(max, j - i + 1);
                j++;
            } else if (map.size() < j - i + 1) {
                while (map.size() < j - i + 1) {
                    map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                    if (map.get(s.charAt(i)) == 0) map.remove(s.charAt(i));
                    i++;
                }
                j++;
            }
        }
        return max;
    }

    // 3 вариант
    // Runtime - 4 ms
    // Beats - 91,32%
    // Memory - 43,3
    // beats -22,10%
    public static int lengthOfLongestSubstring3(String s) {
        int maxlen = 0;
        int left = 0 ;
        int[] charCount = new int[128];
        for(int right = 0 ; right < s.length() ; right++){
            charCount[s.charAt(right)]++;

            while(charCount[s.charAt(right)] > 1){
                charCount[s.charAt(left++)]--;
            }
            maxlen = Math.max(maxlen , right - left +1);
        }
        return maxlen;
    }
}