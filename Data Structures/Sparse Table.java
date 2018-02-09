package Data_Structures;
/**
 * @Resources_Used:
 * https://www.topcoder.com/community/data-science/data-science-tutorials/range-minimum-query-and-lowest-common-ancestor/#Sparse_Table_(ST)_algorithm
 * https://github.com/AhmadElsagheer/Competitive-programming-library/blob/master/data_structures/SparseTable.java
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/misc/SparseTableRangeMinimumQuery.java
 * https://www.youtube.com/watch?v=c5O7E_PDO4U
 * @Special_Thanks: Ahmad El-Sagheer & Tushar Roy
 */
public class SparseTable
{
    private int arr[];
    private int table[][];
    private int arrSize;
    private int blocksNum;

    public SparseTable(int arr[])
    {
        this.arr = arr;
        arrSize = arr.length;
        this.preProcess();
    }

    private void preProcess()
    {
        blocksNum = (int) Math.floor(Math.log(arrSize) / Math.log(2)) + 1;

        table = new int[arrSize][blocksNum];

        for (int i = 0 ; i < arrSize ; ++i)
            table[i][0] = i;

        for (int j = 1 ; (1 << j) <= arrSize ; ++j)
        {
            for (int i = 0 ; i + (1 << j) - 1 < arrSize ; ++i)
            {
                func(i, j);
            }
        }
    }

    private void func(int i, int j)
    {
        //Minimization Function
        int nextLogarithm = i + (1 << (j - 1));

        if (arr[table[i][j - 1]] <= arr[table[nextLogarithm][j - 1]])
            table[i][j] = table[i][j - 1];
        else
            table[i][j] = table[nextLogarithm][j - 1];
    }

    public int query(int i, int j)
    {
        int range = j - i + 1;
        int block = (int) Math.floor(Math.log(range) / Math.log(2));
        int remElements = j + 1 - (1 << block);

        return queryFunc(block, remElements, i);

    }

    private int queryFunc(int block, int remElements, int i)
    {
        //Minimization function
        if (arr[table[i][block]] <= arr[table[remElements][block]])
            return table[i][block];
        else
            return table[remElements][block];
    }
}
