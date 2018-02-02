public class Disjoint_Set {

    private int Parent[];
    private int setSize[];
    private int Rank[];

    private int setCount;

    public Disjoint_Set(int Members)
    {
        Parent = new int[Members];
        setSize = new int[Members];
        Rank = new int[Members];
        setCount = Members;
        initialize(Members);
    }

    private void initialize(int Members)
    {
        for (int i = 0 ; i < Members ; ++i)
        {
            Parent[i] = i;
            setSize[i] = 1;
        }
    }

    public int findSet(int Member)
    {
        if (Member == Parent[Member])
            return Member;

        return Parent[Member] = findSet(Parent[Member]);
    }

    public boolean sameSet(int Member_1, int Member_2)
    {
        return findSet(Member_1) == findSet(Member_2);
    }

    public void union(int Member_1, int Member_2)
    {
        if (sameSet(Member_1, Member_2))
            return;

        setCount = setCount - 1;

        int Parent_1 = findSet(Member_1);
        int Parent_2 = findSet(Member_2);

        if (Rank[Parent_1] > Rank[Parent_2])
        {
            Parent[Parent_2] = Parent_1;
            setSize[Parent_1] = setSize[Parent_1] + setSize[Parent_2];
        }
        else if (Rank[Parent_1] < Rank[Parent_2])
        {
            Parent[Parent_1] = Parent_2;
            setSize[Parent_2] = setSize[Parent_2] + setSize[Parent_1];
        }
        else
        {
            Parent[Parent_2] = Parent_1;
            setSize[Parent_1] = setSize[Parent_1] + setSize[Parent_2];
            Rank[Parent_1] = Rank[Parent_1] + 1;
        }
    }

    public int getSetSize(int Member) {
        int Parent = findSet(Member);
        return setSize[Parent];
    }

    public int getSetCount() {
        return setCount;
    }
}
