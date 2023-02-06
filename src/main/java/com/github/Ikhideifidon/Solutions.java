package com.github.Ikhideifidon;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Solutions {
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n <= 1)
            return n == 0 ? 0 : heights[0];

        // Use a monotonic non-increasing stack
        // 1, 2, 2, 3, 4, 5, 5, 6, 7
        Deque<Integer> stack = new LinkedList<>();
        int maxArea = 0;
        int currentArea = 0;
        int i;

        for (i = 0; i < n; i++) {
            if (stack.isEmpty() || heights[stack.peekFirst()] <= heights[i])
                stack.offerFirst(i++);

            else {
                int top = stack.pollFirst();
                if (stack.isEmpty())
                    currentArea = heights[top] * i;
                else
                    currentArea = heights[top] * (i - stack.peekFirst() - 1);
                if (currentArea > maxArea)
                    maxArea = currentArea;
            }
        }

        while (!stack.isEmpty()) {
            int top = stack.pollFirst();
            if (stack.isEmpty())
                currentArea = heights[top] * i;
            else
                currentArea = heights[top] * (i - stack.peekFirst() - 1);
        }

        if (currentArea > maxArea)
            maxArea = currentArea;
        return maxArea;
    }

    // Time Complexity = O(N*N)
    public static int[] nextGreaterElements(int[] nums) {
        if (nums == null) return null;
        int n = nums.length;
        if (n <= 1)
            return n == 0 ? new int[] {} : new int[] {-1};

        int[] result = new int[n];
        Arrays.fill(result, -1);
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n + i) {
                if (nums[j % n] > nums[i]) {
                    result[i] = nums[j % n];
                    break;
                } else
                    j++;
            }
        }
        return result;
    }
    

    // Can we do better?
    // Time Complexity = O(N)
    // Space Complexity = O(N)
    public static int[] nextGreaterElementsStack(int[] nums) {
        if (nums == null) return null;
        int n = nums.length;
        if (n <= 1)
            return n == 0 ? new int[] {} : new int[] {-1};

        int[] result = new int[n];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new LinkedList<>();
        int index;

        for (int i = 0; i < 2 * n; i++) {
            while (!stack.isEmpty() && nums[stack.peekFirst()] < nums[i % n]) {
                index = stack.pollFirst();
                result[index] = nums[i % n];
            }
            stack.offerFirst(i % n);
        }
        return result;
    }

    public static int nextGreaterElement(int n) {
        // If number of digits < 2
        if (n <= 9 || n == Integer.MAX_VALUE) return -1;
        // Convert n into strings
        // Example: n = 2147483876 will become "2147483876"
        String s = String.valueOf(n);
        // Convert s into char array
        // Example: s = "2147483876" will become ['2', '1', '4', '7', '4', '8', '3', '8', '7', 6]
        char[] array = s.toCharArray();
        // Iterate over the array
        // ['2',   '1',   '4',  '7',    '4',    '8', '  3',     '8',    '7',     6]
        //                                                               ^
        //                                                               |
        //                                                              left
        int left = array.length - 2;
        while (left >= 0 && array[left] >= array[left + 1]) {
            left--;
        }

        if (left < 0)
            return -1;

        int right = array.length - 1;
        // Find the smallest digit from the right > array[left]
        while (array[right] <= array[left])
            right--;

        swap(array, left, right);
        reverse(array, left + 1, array.length - 1);

        // Convert array into string
        String newString = new String(array);
        // Convert newString into long. We convert to long and not int because the nearest greater element might be greater than the Integer.MAX_VALUE.
        long value = Long.parseLong(newString);
        return (int) (value > Integer.MAX_VALUE ? -1 : value);
    }

    private static void reverse(char[] array, int left, int right) {
        // ['2',   '1',   '4',  '7',    '4',    '8', '  3',     '8',    '7',     '6']
        //  left                                                                right
        while (right > left)
            swap(array, left++, right--);
    }

    private static void swap(char[] array, int from, int to) {
        array[from] ^= array[to];
        array[to]   ^= array[from];
        array[from] ^= array[to];
    }

    public static void nextPermutation(int[] nums) {
        if (nums == null) return;
        int n = nums.length;
        if (n <= 1) return;
        // nums = [2,   3,  0,  2,  4,  1]
        //                         left

        int left = nums.length - 2;
        while (left >= 0 && nums[left] >= nums[left + 1]) {
            left--;
        }

        // nums = [2,   3,  0,  2,  4,  1]
        //                     left          

        if (left < 0) {
            reverse(nums, left + 1, nums.length - 1);
            return;
        }

        int right = nums.length - 1;
        // nums = [2,   3,  0,  2,  4,  1]
        //                     left    right      

        // Search for an element on the right of nums[left] that is greater than nums[left]
        while (nums[right] <= nums[left])
            right--;
        
        // Swap left and right
        swap(nums, left, right);
        reverse(nums, left + 1, nums.length - 1);
    }

    private static void swap(int[] nums, int from, int to) {
        nums[from] ^= nums[to];
        nums[to]   ^= nums[from];
        nums[from] ^= nums[to];
    }

    private static void reverse(int[] nums, int left, int right) {
        while(right > left)
            swap(nums, left++, right--);
    }
}
