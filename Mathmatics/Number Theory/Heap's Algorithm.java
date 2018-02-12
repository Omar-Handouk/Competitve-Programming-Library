package Mathmatics;

import java.util.Arrays;

public class Heaps_Algorithm {

    public void permute(int[] arr, int n)
    {
        if (n == 1)
            func(arr);
        else
        {
            for (int i = 0 ; i < (n - 1) ; ++i)
            {
                permute(arr, n - 1);

                if (n % 2 == 0)
                    swap(arr, i, n - 1);
                else
                    swap(arr, 0, n - 1);
            }

            permute(arr, n - 1);
        }
    }

    private void swap(int arr[], int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /*
    This method can be modified to accommodate operation on the generated permutations.
     */
    
    private void func(int arr[])
    {
        System.out.println(Arrays.toString(arr));
    }

}
