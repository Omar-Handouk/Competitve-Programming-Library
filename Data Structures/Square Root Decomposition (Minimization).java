package Data_Structures;

import java.util.Arrays;
/**
 * @Resources-Used:
 * http://e-maxx.ru/algo/sqrt_decomposition (Used Yandex translate)
 * https://www.geeksforgeeks.org/sqrt-square-root-decomposition-technique-set-1-introduction/
 * https://github.com/AhmadElsagheer/Competitive-programming-library/blob/master/data_structures/SQRT_Decomposition.java
 * https://www.topcoder.com/community/data-science/data-science-tutorials/range-minimum-query-and-lowest-common-ancestor/#Introduction
 */
public class Square_Root_Decomposition {

    private int arr[];
    private int table[];
    private int arrSize;
    private int blockSize;

    public Square_Root_Decomposition(int arr[])
    {
        this.arr = arr;
        arrSize = arr.length;
        this.pre_process();
    }

    private void pre_process()
    {
        blockSize = (int) Math.sqrt(arrSize) + 1;

        table = new int[(arrSize + blockSize - 1) / blockSize];
        Arrays.fill(table, -1); //Uncalculated Value

        for (int i = 0; i < arrSize; ++i)
            func(i);
    }

    private void func(int i)
    {
        //Minimization Function
        if (table[i / blockSize] == -1)
            table[i / blockSize] = i;
        else if (arr[i] <= arr[table[i / blockSize]])
                table[i / blockSize] = i;
    }

    /**@Caution: Min value is of type int */

    public int query(int left, int right) //Return The index
    {
        int Min = Integer.MAX_VALUE;
        int minIdx = -1;

        int block_left = left / blockSize;
        int block_right = right / blockSize;

        if (block_left == block_right) {
            for (int i = left; i <= right; ++i) {
                minIdx = queryFunc(Min, i, minIdx, false);
                Min = arr[minIdx];
            }
        }
        else
        {
            for (int i = left; i <= (block_left + 1) * blockSize - 1; ++i) {
                minIdx = queryFunc(Min, i, minIdx, false);
                Min = arr[minIdx];
            }
            for (int i = block_left + 1; i <= block_right - 1 ; ++i) {
                minIdx = queryFunc(Min, i, minIdx, true);
                Min = arr[minIdx];
            }
            for (int i = block_right * blockSize; i <= right ; ++i) {
                minIdx = queryFunc(Min, i, minIdx, false);
                Min = arr[minIdx];
            }
        }

        return minIdx;
    }

    private int queryFunc(int Min, int i, int MinIdx, boolean check) //Check Variable is used to know which array to work With Arr or Table
    {
        if (!check)
        {
            if (arr[i] < Min)
                return i;
            else
                return MinIdx;
        }

        if (arr[table[i]] < Min)
            return table[i];
        else
            return MinIdx;
    }

    public void update(int idx, int val)
    {
        int block = idx / blockSize;

        if (val < arr[table[block]])
            table[block] = idx;

        arr[idx] = val;
    }
}
