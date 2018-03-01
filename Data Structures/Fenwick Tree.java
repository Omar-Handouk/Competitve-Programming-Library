public class Fenwick_Tree {

    private int size;
    private int[] tree;

    public Fenwick_Tree(int size, int[] arr) {
        this.size = size;
        tree = new int[size + 1];
        this.construct(arr);
    }

    public void construct(int[] arr) {
        for (int idx = 1; idx <= size; ++idx)
            pointUpdate(idx, arr[idx - 1]);
    }


    public int getSize() {
        return size;
    }

    public int rangeSumQuery(int idx) {
        int sum = 0;

        while (0 < idx) {
            sum += tree[idx];
            idx -= (idx & -idx);
        }

        return sum;
    }

    public int rangeSumQuery(int from, int to) {
        return rangeSumQuery(to) - rangeSumQuery(from - 1);
    }

    public int pointQuery(int idx) {
        int sum = tree[idx];

        if (0 < idx) {
            int root = idx - (idx & -idx);
            idx = idx - 1;

            while (idx != root) {
                sum -= tree[idx];
                idx -= (idx & -idx);
            }
        }

        return sum;
    }

    public void pointUpdate(int idx, int val) {
        while (idx <= size) {
            tree[idx] += val;
            idx += (idx & -idx);
        }
    }

    public void scale(int val) {
        for (int idx = 1; idx <= size; ++idx) {
            tree[idx] *= val;
        }
    }

    public int findIndex(int cumFreq) {
        int bitmask = size;

        while ((size & -size) != 0)
            size ^= (size & -size);

        int idx = 0;

        while ((bitmask != 0) && (idx < size)) {
            int mIdx = idx + bitmask;

            if (cumFreq == tree[mIdx]) //Remove this and make cumFreq >= tree[mIdx] if you want to get the highest Index with duplicate cumFreq
            {
                return mIdx;
            } else if (cumFreq > tree[mIdx]) {
                idx = mIdx;
                cumFreq -= tree[mIdx];
            }

            bitmask >>= 1;
        }

        if (cumFreq != 0)
            return -1;
        else
            return idx;
    }
}

