package leetcode._0005;

/*
Given a string s, return the longest palindromic substring in s.

Дана строка s, вернуть самую длинную палиндромную подстроку в s.

Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

Example 2:

Input: s = "cbbd"
Output: "bb"

 */

public class LongestPalindrome {

    public static void main(String[] args) {
        String s = "babad";
//        String s = "cbbd";
        System.out.println(longestPalindrome(s)); //bab
        System.out.println(longestPalindrome2(s)); //bab
        System.out.println(longestPalindrome3(s)); //aba - also valid
    }
// 1 вариант
    public static boolean check(String s, int i, int j) {
        while(i < j) {
            if(s.charAt(i) != s.charAt(j) ){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    public static String longestPalindrome(String s) {
        int n = s.length();
        int starting_index = 0;
        int max_len = 0;
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                if(check(s, i, j)){
                    if(j - i + 1 > max_len) {
                        max_len = j - i + 1;
                        starting_index = i;
                    }
                }
            }
        }
        return s.substring(starting_index, starting_index + max_len);
    }
    // 2 вариант
    // Runtime - 8 ms
    // Beats - 96,52%
    // Memory - 41,7
    // beats - 99,42%
    static int maxLen = 0;
    static int lo = 0;
    public static String longestPalindrome2(String s) {
        char[] input = s.toCharArray();
        if(s.length() < 2) {
            return s;
        }

        for(int i = 0; i < input.length; i++) {
            expandPalindrome(input, i, i);
            expandPalindrome(input, i, i + 1);
        }
        return s.substring(lo, lo + maxLen);
    }

    public static void expandPalindrome(char[] s, int j, int k) {
        while(j >= 0 && k < s.length && s[j] == s[k]) {
            j--;
            k++;
        }
        if(maxLen < k - j - 1) {
            maxLen = k - j - 1;
            lo = j + 1;
        }
    }

    // 3 вариант - ChatGPT-4
    // Runtime - 25 ms
    // Beats - 57,44%
    // Memory - 41,3
    // beats - 99,71%
//    В этом решении используем метод expandAroundCenter, который проверяет палиндромность подстроки, расширяясь влево и вправо от центра.
//    Проходим по каждому символу строки и проверяем палиндромность для нечетной и четной длины подстроки.
//    Если найденная подстрока длиннее текущей самой длинной палиндромной подстроки,  обновляем индексы начала и конца.
//    Этот алгоритм имеет временную сложность O(n^2) и пространственную сложность O(1), где n - длина строки s.
    public static String longestPalindrome3(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}