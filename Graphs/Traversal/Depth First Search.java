import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static Scanner in = new Scanner(System.in);

    static int nodes;

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

        dfs_iterative(0);
        System.out.println("--------\n");
        Arrays.fill(visited,false);
        dfs_recursive(0);
    }

    static void dfs_iterative(int start){

        Stack<Integer> stack = new Stack<>();

        visited[start] = true;

        //System.out.printf("Push start vertex: %d\n\n",start);
        stack.push(start);

        while (!stack.isEmpty()){

            int current_node = stack.pop();
            //System.out.printf("Pop parent/current node: %d\n",current_node);

            for (int next_node = 0 ; next_node < nodes ; next_node++){
                if (adjMat[current_node][next_node] && !visited[next_node]){
                    stack.push(next_node);
                    visited[next_node] = true;
                    //System.out.printf("Push child/next node: %d\n\n", next_node);
                }
            }
        }
    }

    static void dfs_recursive(int vertex){

        //System.out.printf("Currently in node number: %d\n",vertex);

        visited[vertex] = true;

        for (int next_node = 0 ; next_node < nodes ; next_node++){

            if (adjMat[vertex][next_node] && !visited[next_node]){
                dfs_recursive(next_node);
            }
        }
    }
}

/*Sample graph
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
      5     */
