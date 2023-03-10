package com.github.Ikhideifidon;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(Solutions.largestRectangleArea(heights));

        int[] nums = {1, 2, 3, 2, 1};
        System.out.println(Arrays.toString(Solutions.nextGreaterElements(nums)));

        System.out.println(Arrays.toString(Solutions.nextGreaterElementsStack(nums)));

        System.out.println(Solutions.nextGreaterElement(230241));

        int[] numbers = {2, 3, 0, 2, 4, 1};
        Solutions.nextPermutation(numbers);
        System.out.println(Arrays.toString(numbers));

        System.out.println(Solutions.permute(new int[] {1, 2, 3}));
        System.out.println(Solutions.subsets(new int[] {1, 2, 3}));
        System.out.println(Solutions.subsetsWithDup(new int[] {1, 2, 2}));
        System.out.println(Solutions.permuteUnique(new int[] {1, 1, 2}));
        System.out.println(Solutions.combinationSum2(new int[] {2, 3, 6, 7}, 7));
        System.out.println(Solutions.combinationSum(new int[] {2, 3, 6, 7}, 7));

    }
}