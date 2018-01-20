package Dynamic_Programming;

/**
 * @Time-Complexity: O(n^2)
 * @Memory-Complexity: O(n)
 */

import java.util.Arrays;

public class LIS_In_N_Squared {

    public static int LIS(int[] arr)
    {
        int[] temp = new int[arr.length];
        Arrays.fill(temp, 1);

        for (int i = 1 ; i < arr.length ; ++i)
        {
            for (int j = 0 ; j < i ; ++j)
            {
                if (arr[i] > arr[j])
                    temp[i] = Math.max(temp[i], temp[j] + 1);
            }
        }

        Arrays.sort(temp);

        return temp[arr.length - 1];
    }

    public static void main(String[] args)
    {
        int arr[] = { 10, 22, 9, 9, 21, 50, 41, 60 };
        int n = arr.length;
        System.out.printf("<<Longest Increasing Sub-sequence length is: %d>>", LIS(arr));
    }
}
