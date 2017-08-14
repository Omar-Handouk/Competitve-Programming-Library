import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main
{

    static ArrayList<Integer>[] adjList;
    static boolean[] visited;

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args)
    {
        usingDfsOnly();
        System.out.println();
        usingDfsAndInDegreeArray();
    }

    static Stack<Integer> ans;

    static void usingDfsOnly(){

        int nodes = in.nextInt();
        int edges = in.nextInt();

        adjList = new ArrayList[nodes];
        for (int i = 0 ; i < nodes ; i++)
            adjList[i] = new ArrayList<>();

        visited = new boolean[nodes];
        ans = new Stack<>();

        for (int i = 1 ; i <= edges ; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            adjList[a].add(b);
        }

        for (int i = 0 ; i < nodes ; i++)
            if (!visited[i])
                dfs_rec(i);

        while (!ans.isEmpty())
            System.out.print(ans.pop() + " ");
    }

    static void dfs_rec(int v){

        visited[v] = true;

        for (int e : adjList[v])
            if (!visited[e])
                dfs_rec(e);

        ans.add(v);
    }

    static ArrayList<Integer> answer = new ArrayList<>();
    static int[] inDeg;

    static void usingDfsAndInDegreeArray(){

        int nodes = in.nextInt();
        int edges = in.nextInt();

        adjList = new ArrayList[nodes];
        for (int i = 0 ; i < nodes ; i++)
            adjList[i] = new ArrayList<>();

        visited = new boolean[nodes];
        inDeg = new int[nodes];

        for (int i = 1 ; i <= edges ; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            ++inDeg[b];
            adjList[a].add(b);
        }

        for (int i = 0 ; i < nodes ; i++)
            if (!visited[i] && inDeg[i] == 0)
                dfs_rec2(i);

        for (int e : answer)
            System.out.print(e + " ");
    }

    static void dfs_rec2(int v){

        visited[v] = true;
        answer.add(v);

        for (int e : adjList[v]){
            --inDeg[e];
            if (inDeg[e] == 0)
                dfs_rec2(e);
        }
    }
}

/*
**Sample Graph**
17 22
0 3
0 4
1 3
1 4
2 3
2 4
3 5
4 5
5 6
5 7
5 8
6 9
6 10
7 9
7 10
8 9
8 10
9 11
10 11
12 13
13 14
13 15
 */
