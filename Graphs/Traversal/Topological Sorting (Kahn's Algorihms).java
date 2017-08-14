import java.util.*;

public class Main
{

    static int vertices;
    static int edges;

    static int[] inDeg;
    static Graph graph;

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args)
    {
        vertices = in.nextInt();
        edges = in.nextInt();

        graph = new Graph(vertices);
        inDeg = new int[vertices];

        for (int i = 1; i <= edges ; i++)
        {
            int u = in.nextInt();
            int v = in.nextInt();

            ++inDeg[v];

            graph.addEdge(u ,v);
        }

        KahnsAlgorithm();
    }

    static Vector<Integer> ans = new Vector<>();

    public static void KahnsAlgorithm()
    {
        //PriorityQueue<Integer> queue = new PriorityQueue<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0 ; i < vertices ; i++)
        {
            if (!graph.visited[i] && inDeg[i] == 0)
            {
                queue.add(i);
                graph.visit(i);
            }
        }

        while (!queue.isEmpty())
        {
            int current_node = queue.poll();
            ans.add(current_node);

            for (int next_node : graph.adjList[current_node])
            {
                if (--inDeg[next_node] == 0)
                {
                    queue.add(next_node);
                    graph.visit(next_node);
                }
            }
        }

        for (int e : ans)
            System.out.print(e + " ");
    }
}

class Graph
{
    int vertices;

    ArrayList<Integer>[] adjList;
    boolean[] visited;

    public Graph(int vertices)
    {
        this.vertices = vertices;

        adjList = new ArrayList[vertices];
        for (int i = 0 ; i < vertices ; i++)
            adjList[i] = new ArrayList<>();

        visited = new boolean[vertices];
    }

    public void addEdge(int u, int v)
    {
        adjList[u].add(v);
    }

    public boolean visit(int v)
    {
        return visited[v] = true;
    }

    public void clearVisit()
    {
        Arrays.fill(visited, false);
    }
}