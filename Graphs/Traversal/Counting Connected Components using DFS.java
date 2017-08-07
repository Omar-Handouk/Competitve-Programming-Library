import java.util.*;

public class Main {

    static Scanner in = new Scanner(System.in);

    static int nodes;
    static int INF = (int) 1e6;

    static boolean[][] adjMat;
    static boolean[] visited;

    public static void main(String[] args){

        nodes = in.nextInt();
        adjMat = new boolean[nodes][nodes];
        visited = new boolean[nodes];

        while (true){

            int nodeA = in.nextInt();
            int nodeB = in.nextInt();

            if (nodeA == 0 && nodeB == 0)
                break;
            adjMat[nodeA][nodeB] = true;
            adjMat[nodeB][nodeA] = true;
        }

        int countC = 0;

        for (int i = 0 ; i < nodes ; i++)
            if (!visited[i]){
                ++countC;
                dfs(i);
            }

        //System.out.printf("Number of connected components in the graph: %d\n", countC);
    }

    static void dfs(int v){

        visited[v] = true;

        for (int i = 0 ; i < nodes ; i++)
             if (adjMat[v][i] && !visited[i])
                 dfs(i);
    }
}

/*
**Sample graph**
7
0 1
0 2
1 3
1 4
3 5
4 5
4 6
2 6
0 0
*/

/*
         0
        / \
      1     2
     / \     \
    3   4 --- 6
    \   /
      5
*/
