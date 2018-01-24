package Dynamic_Programming;

/**
 * @Space-Complexity: O(N)
 * @Time-Complexity: O(N^2) or using Kadane's Algorithm in O(N)
 */


public class Max_1D_Range_Sum {

    public static int[] preCal(int[] arr)
    {
        int[] Accumulate = new int[arr.length];

        Accumulate[0] = arr[0];
        for (int i = 1 ; i < arr.length ; ++i)
            Accumulate[i] = arr[i] + Accumulate[i - 1];

        return Accumulate;
    }

    public static int max1D(int[] arr)
    {
        int[] preCal = preCal(arr);

        int max = preCal[0];
        for (int i = 1 ; i < preCal.length ; ++i)
            for (int j = i ; j < preCal.length ; ++j)
                max = Math.max(max, preCal[j] - preCal[i - 1]);

        return max;
    }

    public static int max1DKadane(int[] arr)
    {
        int currSum = 0;
        int max = 0;

        for (int i = 0 ; i < arr.length ; ++i)
        {
            currSum = currSum + arr[i];
            max = Math.max(max, currSum);
            if (currSum < 0) currSum = 0;
        }

        return max;
    }

    public static void main(String[] args)
    {
        int[] arr = {4,-5,4,-3,4,4,-4,4,-5};

        System.out.printf("Maximum 1D sum using Quadratic time: %d\n", max1D(arr));
        System.out.printf("Maximum 1D sum using Linear time: %d", max1DKadane(arr));
    }
}
