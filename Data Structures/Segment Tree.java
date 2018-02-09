package Data_Structures;

/**
 * @Author: Omar-Handouk
 * @Resources:
 * https://www.geeksforgeeks.org/smallest-power-of-2-greater-than-or-equal-to-n/ (Used it to find the Powers of 2)
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/SegmentTreeMinimumRangeQuery.java (Implementation by Tushar Roy)
 * https://kartikkukreja.wordpress.com/2014/11/09/a-simple-approach-to-segment-trees (Simple and very easy Article)
 * https://www.hackerearth.com/practice/notes/segment-tree-and-lazy-propagation/ (In-depth explanation of lazy propagation)
 * https://www.youtube.com/watch?v=ZBHKZF5w4YU (Segment Tree Explanation by Tushar Roy)
 * https://www.youtube.com/watch?v=xuoQdt5pHj0 (Lazy Propagation explanation by Tushar Roy)
 * @Special_Thanks: Tushar Roy for his amazing efforts.
 */

/**
 * Properties of this implementation
 * This segment tree is implemented using the lazy propagation technique.
 * @Space_Complexity: O(2 * N - 1), where N is power of 2. (Applies for both tree and lazy arrays)
 * @methods_&_Time_Complexities:
 * findSize, O(Log(N))
 * construct, O(N)
 * getTreeHeight, O(1)
 * getNodeNum, O(1)
 * query, O(Log(N))
 * updateNode, Avg. Case O(Log(N)), Worst Case of multiple updates O(N)
 * updateRange, O(Log(N))
 */

import java.util.Arrays;

public class Segment_Tree {

    /**
     * @param arr: Original Array.
     * @param tree: Array used to model the segment tree, where each index represents a range (The nodes are zero indexed).
     * @param lazy: Array used to model a tree which hold the updates queries, for the lazy propagation optimization.
     * @param arrSize: Original Array size.
     * @param nodeNum: Number of nodes in the tree.
     * @param treeHeight: Height of the segment tree.
     * @Space_Complexity: O(2 * N - 1), where N is a power of 2.
     */

    private int arr[];
    private int tree[];
    private int lazy[];

    private int arrSize;
    private int nodeNum;
    private int treeHeight;

    public Segment_Tree(int arr[])
    {
        this.arr = arr;
        arrSize = arr.length;

        findSize(arrSize);

        tree = new int[nodeNum];
        Arrays.fill(tree, Integer.MAX_VALUE); //Initialization of Minimization array.

        lazy = new int[nodeNum];

        construct(0, 0, arrSize - 1);
    }

    /**
     * @function : Used to find the Closest power of two corresponding to the array size.
     * @Time_Complexity: O(Log(arrSize))
     */
    private void findSize(int arrSize)
    {
        if (arrSize == 0) {
            treeHeight = (int) (Math.log(1) / Math.log(2)) + 1;
            nodeNum = 1;
        }
        else if (arrSize > 0 && (arrSize & (arrSize - 1)) == 0) {
            treeHeight = (int) (Math.log(arrSize) / Math.log(2)) + 1;
            nodeNum = 2 * arrSize - 1;
        }
        else {
            while ((arrSize & (arrSize - 1)) > 0)
                arrSize = arrSize & (arrSize - 1);

            treeHeight = (int) (Math.log(arrSize << 1) / Math.log(2)) + 1;
            nodeNum = 2 * (arrSize << 1) - 1;
        }
    }

    public int getTreeHeight()
    {
        return treeHeight;
    }

    public int getNodeNum()
    {
        return nodeNum;
    }

    /**
     * @function: Used to find the minimum between two children and save it in the parent.
     * This function can be tweaked to be used for any other function.
     */

    private void merge(int parent, int leftChild, int rightChild)
    {
        tree[parent] = Math.min(tree[leftChild], tree[rightChild]);
    }

    /**
     *
     * @param node: Current node we are in.
     * @param left: the start of the range the current node represents.
     * @param right: The end of the range the current node represents.
     * @function: Used to construct the segment tree.
     * @Time_Complexity: O(N)
     */

    private void construct(int node, int left, int right)
    {
        if (left == right)
        {
            tree[node] = arr[left];

            return;
        }

        int mid = (left + right) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        construct(leftChild, left, mid);
        construct(rightChild, mid + 1, right);

        merge(node, leftChild, rightChild);
    }

    public int query(int qleft, int qright)
    {
        return query(0, qleft, qright, 0, arrSize - 1);
    }

    /**
     *
     * @param leftValue: Value of the left child.
     * @param rightValue: Value of the right child.
     * @return: Minimum of the left and right child.
     */

    private int func(int leftValue, int rightValue)
    {
        return Math.min(leftValue, rightValue);
    }

    /**
     *
     * @param node: Current Node.
     * @param qleft: the start of the query range.
     * @param qright: The end of the query range.
     * @param left: Start of the range of the current recursive call.
     * @param right: End of the range of the current recursive call.
     * @return: Minimum between the left and right children.
     * @function: Used to find the minimum in a given range.
     * @Time_Complexity: O(Log(N))
     */

    private int query(int node, int qleft, int qright, int left, int right)
    {
        if (left > right)
        {
            return Integer.MAX_VALUE;
        }

        if (lazy[node] != 0)
        {
            tree[node] += lazy[node];

            if (left != right)
            {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }

            lazy[node] = 0;
        }

        if (qleft > right || qright < left)
        {
            return Integer.MAX_VALUE;
        }
        else if (qleft <= left && right <= qright)
        {
            return tree[node];
        }
        else
        {
            int mid = (left + right) / 2;

            int leftValue = query(2 * node + 1, qleft, qright, left, mid);
            int rightValue = query(2 * node + 2, qleft, qright, mid + 1, right);

            return func(leftValue, rightValue);
        }
    }

    public void updateNode(int idx, int value)
    {
        arr[idx] += value;
        updateNode(0, idx, value, 0, arrSize - 1);
    }

    /**
     *
     * @param node: Current node.
     * @param idx: The index that is desired to be updated
     * @param value: The value we want to update the element.
     * @param left: start of the range of the current recursive call.
     * @param right: end of the range of the current recursive call.
     * @function: Used to update the value of a given index.
     * @Time_Complexity: Average Case O(Log(N)), Worst Case "Case of Multiple Updates" O(N)
     */

    private void updateNode(int node, int idx, int value, int left, int right)
    {
        if (idx < left || right < idx)
            return;
        else if (left == right)
        {
            tree[node] += value;

            return;
        }

        int mid = (left + right) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        updateNode(leftChild, idx, value, left, mid);
        updateNode(rightChild, idx, value, mid + 1, right);

        tree[node] = Math.min(tree[leftChild], tree[rightChild]);
    }

    public void updateRange(int uleft, int uright, int value)
    {
        updateRange(0, value, uleft, uright, 0, arrSize - 1);
    }

    /**
     *
     * @param node: Current Node.
     * @param value: The desired value for the given range to be updated.
     * @param uleft: start of the given update range.
     * @param uright: end of the given update range.
     * @param left: start of range of the current recursive call.
     * @param right: end of the range of the current recursive call.
     * @function: Used to update the value of a given range.
     * @Time_Complexity: O(Log(N))
     */

    private void updateRange(int node, int value, int uleft, int uright, int left, int right)
    {
        if (left > right)
            return;

        if (lazy[node] != 0)
        {
            tree[node] += lazy[node];

            if (left != right)
            {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }

            lazy[node] = 0;
        }

        if (uleft > right || uright < left)
            return;
        else if (uleft <= left && right <= uright)
        {
            tree[node] += value;

            if (left != right)
            {
                lazy[2 * node + 1] += value;
                lazy[2 * node + 2] += value;
            }

            return;
        }

        int mid = (left + right) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        updateRange(leftChild, value, uleft, uright, left, mid);
        updateRange(rightChild, value, uleft, uright, mid + 1, right);

        tree[node] = Math.min(tree[leftChild], tree[rightChild]);
    }
}
