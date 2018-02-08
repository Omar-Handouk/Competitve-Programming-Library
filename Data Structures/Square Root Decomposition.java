/**
 * @Resources-Used:
 * http://e-maxx.ru/algo/sqrt_decomposition (Used Yandex translate)
 * https://www.geeksforgeeks.org/sqrt-square-root-decomposition-technique-set-1-introduction/
 * https://github.com/AhmadElsagheer/Competitive-programming-library/blob/master/data_structures/SQRT_Decomposition.java
 * https://www.topcoder.com/community/data-science/data-science-tutorials/range-minimum-query-and-lowest-common-ancestor/#Introduction
 */

public class Square_Root_Decomposition {

    private int arr[];
    private int blocks[];
    private int arr_size;
    private int block_size;

    public Square_Root_Decomposition(int arr[])
    {
        this.arr = arr;
        arr_size = arr.length;
        this.pre_process();
    }

    private void pre_process()
    {
        block_size = (int) Math.sqrt(arr_size) + 1;

        blocks = new int[(arr_size + block_size - 1) / block_size];

        for (int i = 0 ; i < arr_size ; ++i)
            blocks[i / block_size] += arr[i];
    }

    public int query(int left, int right)
    {
        int sum = 0;

        int block_left = left / block_size;
        int block_right = right / block_size;

        if (block_left == block_right)
            for (int i = left ; i <= right ; ++i)
                sum += arr[i];
        else
        {
            for (int i = left ; i <= (block_left + 1) * block_size - 1; ++i)
                sum += arr[i];
            for (int i = block_left + 1; i <= block_right - 1 ; ++i)
                sum += blocks[i];
            for (int i = block_right * block_size; i <= right ; ++i)
                sum += arr[i];
        }

        return sum;
    }

    public void update(int idx, int val)
    {
        int block = idx / block_size;

        blocks[block] += val - arr[idx];

        arr[idx] = val;
    }
}
