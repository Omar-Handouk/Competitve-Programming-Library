package Dynamic_Programming;

/**
 * @Space-Complexity: O(N^2)
 * @Time-Complexity: O(N^4) or using Kadane's Algorithm in O(N^3)
 */

import java.util.Arrays;

public class Max_2D_Range_Sum {

    public static int[][] preCal(int[][] arr, int length, int width)
    {
        int[][] Accumulate = new int[length][width];

        for (int i = 0 ; i < length ; ++i)
        {
            for (int j = 0 ; j < width ; ++j)
            {
                Accumulate[i][j] = arr[i][j];
                if (i > 0)
                    Accumulate[i][j] += Accumulate[i - 1][j];
                if (j > 0)
                    Accumulate[i][j] += Accumulate[i][j - 1];
                if (i > 0 && j > 0)
                    Accumulate[i][j] -= Accumulate[i - 1][j - 1];
            }
        }

        //System.err.println(Arrays.deepToString(Accumulate));

        return Accumulate;
    }

    public static int max2D(int[][] arr, int length, int width)
    {
        int[][] preCal = preCal(arr, length, width);

        int max = 0;

        for (int i_1 = 0 ; i_1 < length ; ++i_1)
        {
            for (int j_1 = 0 ; j_1 < width ; ++j_1)
            {
                for (int i_2 = i_1 ; i_2 < length ; ++i_2)
                {
                    for (int j_2 = j_1 ; j_2 < width ; ++j_2)
                    {
                        int subRec = preCal[i_2][j_2];

                        if (i_1 > 0)
                            subRec -= preCal[i_1 - 1][j_2];
                        if (j_1 > 0)
                            subRec -= preCal[i_2][j_1 - 1];
                        if (i_1 > 0 && j_1 > 0)
                            subRec += preCal[i_1 - 1][j_1 - 1];

                        max = Math.max(max, subRec);
                    }
                }
            }
        }

        return max;
    }


    public static int max2DKadane(int[][] arr, int length, int width)
    {
        int max = 0;

        int[] Accumulate = new int[length];

        for (int i = 0 ; i < length ; ++i)
        {

            Arrays.fill(Accumulate, 0);

            for (int j = i ; j < width ; ++j)
            {
                for (int k = 0 ; k < length ; ++k)
                    Accumulate[k] += arr[k][j];

                int sum = max1Dkadane(Accumulate);

                max = Math.max(sum, max);
            }
        }

        return max;
    }

    public static int max1Dkadane(int[] arr)
    {
        int[] Accumulate = new int[arr.length];

        int max = 0;
        int currSum = 0;

        for (int i = 0 ; i < arr.length ; ++i)
        {
            currSum += arr[i];
            max = Math.max(max, currSum);
            if (currSum < 0)
                currSum = 0;
        }

        return max;
    }

    public static void main(String[] args)
    {
        //int[][] arr = {{0,-2,-7,0}, {9,2,-6,2}, {-4,1,-4,1}, {-1,8,0,-2}};
        int[][] arr = {{2,1,-3,-4,5}, {0,6,3,4,1}, {2,-2,-1,4,-5}, {-3,3,1,0,3}};
        System.out.printf("Maximum 2D range sum using n^4: %d\n", max2D(arr,arr.length,arr[0].length));
        System.out.printf("Maximum 2D range sum using Kadane: %d\n", max2DKadane(arr,arr.length,arr[0].length));
    }
}
