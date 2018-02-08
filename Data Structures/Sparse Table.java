package Data_Structures;

/**
 * @Resources_Used:
 * https://www.topcoder.com/community/data-science/data-science-tutorials/range-minimum-query-and-lowest-common-ancestor/#Sparse_Table_(ST)_algorithm
 * https://github.com/AhmadElsagheer/Competitive-programming-library/blob/master/data_structures/SparseTable.java
 * @Special_Thanks: Ahmad El-Sagheer
 */

public class Sparse_Table {

    int arr[];
    int lookup[][];
    int arr_size;
    int block_num;

    public Sparse_Table(int arr[])
    {
        this.arr = arr;

        arr_size = arr.length;
        block_num = (int) Math.floor(Math.log(arr_size) / Math.log(2)) + 1;

        lookup = new int[arr_size][block_num];

        this.pre_process();
    }

    private void pre_process()
    {
        for (int i = 0; i < arr_size; ++i)
            lookup[i][0] = i;


        for (int j = 1; (1 << j) <= arr_size; ++j)
            for (int i = 0; i + (1 << j) - 1 < arr_size; ++i)
                if (arr[lookup[i][j - 1]] < arr[lookup[i + (1 << (j - 1))][j - 1]])
                    lookup[i][j] = lookup[i][j-1];
                else
                    lookup[i][j] = lookup[i+(1<<(j-1))][j-1];
    }

    public int query(int i, int j)
    {
        int block = (int) Math.floor(Math.log(j - i + 1) / Math.log(2));

        if (arr[lookup[i][block]] <= arr[lookup[j- (1 << block) + 1][block]])
            return lookup[i][block];
        else
            return lookup[j - (1 << block) + 1][block];
    }
}
