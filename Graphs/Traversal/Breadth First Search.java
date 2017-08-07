import java.util.*;

public class Main {

    static Scanner in = new Scanner(System.in);

    static int nodes;
    static int INF = (int) 1e6;

    static boolean[][] adjMat;
    static boolean[] visited;
    static int[] distance;

    public static void main(String[] args){

        nodes = in.nextInt();
        adjMat = new boolean[nodes][nodes];
        visited = new boolean[nodes];
        distance = new int[nodes];
        Arrays.fill(distance, -INF);

        while (true){

            int nodeA = in.nextInt();
            int nodeB = in.nextInt();

            if (nodeA == 0 && nodeB == 0)
                break;
            adjMat[nodeA][nodeB] = true;
            adjMat[nodeB][nodeA] = true;
        }

        bfs_traverse(0);
        bfs_distance(0);
    }

    static void bfs_traverse(int start){

        Queue<Integer> queue = new LinkedList<>();

        //System.out.println("**BFS START**\n------------\n\n");

        queue.add(start);
        visited[start] = true;
        //System.out.printf("Enqueue start: %d\n\n", start);

        while (!queue.isEmpty()){

            //System.out.printf("**--------**\n");

            int current_node = queue.poll();
            //System.out.printf("Dequeue Parent: %d\n", current_node);

            for (int next_node = 0 ; next_node < nodes ; next_node++){

                if (adjMat[current_node][next_node] && !visited[next_node]){
                    queue.add(next_node);
                    visited[next_node] = true;
                    //System.out.printf("Enqueue child: %d\n", next_node);
                }
            }
        }
    }

    static void bfs_distance(int start){

        Queue<Integer> queue = new LinkedList<>();

        //System.out.println("**BFS START**\n------------\n\n");

        queue.add(start);
        distance[start] = 0;
        //System.out.printf("Enqueue start: %d\n\n", start);

        while (!queue.isEmpty()){

            //System.out.printf("**--------**\n");

            int current_node = queue.poll();
            //System.out.printf("Dequeue Parent: %d\n", current_node);

            for (int next_node = 0 ; next_node < nodes ; next_node++){

                if (adjMat[current_node][next_node] && distance[next_node] == -INF){
                    queue.add(next_node);
                    distance[next_node] = distance[current_node] + 1;
                    //System.out.printf("Enqueue child: %d\n", next_node);
                    //System.out.printf("Distance from start to next node: %d\n", distance[next_node]);
                }
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
