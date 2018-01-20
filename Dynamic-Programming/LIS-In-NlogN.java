package Dynamic_Programming;

/**
 * @Time-Complexity: O(N * Log(N))
 * @Space-Complexity: O(N)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LIS_In_NlogN {

    int[] arr; // Input array

    public LIS_In_NlogN(int[] arr) {this.arr = arr;}

    int[] temp;
    /**
     * @Temp-Array
     * This array is the array used to actively update the best possible placement for an element in (arr "Input arr")
     * i.e : if an element a[i] < a[j], where i > j, therefore it may be better to replace the element a[j], since it
     * could result in a better sequence.
     * @Example: for Arr {2, 1, 4, 10, 3, 5, 6}
     * if it was found the the best sequence till index  index (3) is either {2, 4, 10} or {1, 4, 10};
     * then it may be better to replace (4) with (3) as some other element in the array can come after 3
     * so if we replace (4) by (3) we will find out that the best achieved sequence is either {2, 3, 4, 5, 6} or {1, 3, 4, 5, 6};
     * @Size: len(arr)
     * */

    int[] finalSequence;
    /**
     * @finalSequence-Array
     * This array is used to retrieve the best LIS.
     * Basically the indices of this array represent the index of the element in array (arr) that the last possible
     * sequence ends at.
     * i.e : The last element in the Longest LIS.
     * The cell itself hold the value of index in which the element before last one in the valid LIS.
     */

    /**
     * @<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<IMPORTANT>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     * @LINK: https://www.youtube.com/watch?v=S9oUiVYEq7E&t=205s (Tushar Roy's Explanation)
     * @lINK: https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/ (Geeks4Geeks tutorial)
     * @LINK: https://github.com/mission-peace/interview/blob/master/src/com/interview/array/LongestIncreasingSubSequenceOlogNMethod.java (Tushar Roy's Implementation)
     */

    public int ceilIndex(int len, int key)
    {
        int low = 0;
        int high = len;
        int middle;

        while (low <= high)
        {
            middle = (low + high) / 2;

            if (middle < len && arr[temp[middle]] < key && key <= arr[temp[middle + 1]])
                return middle + 1;
            else if (arr[temp[middle]] < key)
                low = middle + 1;
            else
                high = middle - 1;
        }

        return -1; 
        /*This state is never reached since we are sure that the key is between index 0 ---> Len 
        "Last index that is occupied in temp"*/
    }

    public int LIS()
    {
        temp = new int[arr.length];
        finalSequence = new int[arr.length];

        Arrays.fill(finalSequence, -1);

        temp[0] = 0;

        int len = 0;

        for (int i = 1 ; i < arr.length ; ++i)
        {
            if (arr[i] < arr[temp[0]]) {
                temp[0] = i;
            }
            else if (arr[i] > arr[temp[len]])
            {
                len = len + 1;
                temp[len] = i;
                finalSequence[i] = temp[len - 1];
            }
            else
            {
                int index = ceilIndex(len, arr[i]);
                temp[index] = i;
                finalSequence[i] = temp[index - 1];
            }
        }

        constructSequence(len); //Used to Construct the best sequence in an ArrayList and return it.

        return len + 1;
    }

    ArrayList<Integer> answer = new ArrayList<>();

    public void constructSequence(int last)
    {
        int idx = temp[last];

        while (idx != -1)
        {
            answer.add(arr[idx]);
            idx = finalSequence[idx];
        }

        Collections.sort(answer); //Just to make it ascending
    }

    public ArrayList<Integer> sequence()
    {
        return answer;
    }
}

class Main // Tester Class
{
    public static void main(String[] args)
    {
        int A[] = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };
        LIS_In_NlogN lis = new LIS_In_NlogN(A);

        int longest = lis.LIS();
        ArrayList<Integer> ans = lis.sequence();

        System.out.println(longest);
        System.out.println(Arrays.toString(ans.toArray()));
    }
}
