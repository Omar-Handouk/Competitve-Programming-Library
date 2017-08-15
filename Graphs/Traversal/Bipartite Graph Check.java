import java.io.*;
import java.util.*;

public class Main {

    static Reader in = new Reader();
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    static Graph graph;
    static int[] color;

    static int unvisited = -1;

    public static void main(String[] args) throws IOException{

        int vertices = in.nextInt();
        int edges = in.nextInt();

        graph = new Graph(vertices);
        color = new int[vertices];
        Arrays.fill(color, unvisited);

        while (edges --> 0)
        {
            int u = in.nextInt();
            int v = in.nextInt();

            graph.addEdge(u, v);
            graph.addEdge(v, u);
        }

        boolean isPartite = bicolorable_dfs(0, 0);
        //boolean isPartite = bicolorable_bfs(0);

        out.println(isPartite);
        //----------
        out.flush();
        out.close();
    }

    static boolean bicolorable_bfs(int source)
    {
        Queue<Integer> queue = new LinkedList<>();

        boolean isBicolorable = true;

        queue.add(source);
        color[source] = 0;

        while (!queue.isEmpty() && isBicolorable)
        {
            int current_node = queue.poll();

            for (int next_node : graph.edgeList(current_node))
            {
                if (color[next_node] == unvisited)
                {
                    queue.add(next_node);
                    color[next_node] = 1 - color[current_node];
                }
                else if (color[next_node] == color[current_node])
                {
                    isBicolorable = false;
                    break;
                }
            }
        }

        return isBicolorable;
    }

    static boolean bicolorable_dfs(int vertex, int col)
    {
        color[vertex] = col;

        boolean isBicolorable = true;

        for (int next_node : graph.edgeList(vertex))
        {
            if (color[next_node] == unvisited)
            {
                isBicolorable = bicolorable_dfs(next_node, 1 - col);
            }
            else if (color[next_node] == color[vertex])
            {
                isBicolorable = false;
                break;
            }
        }

        return isBicolorable;
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

    public void removeEdge(int u, int v)
    {
        adjList[u].remove(v);
    }

    public boolean visit(int v)
    {
        return visited[v] = true;
    }

    public void clearVisit()
    {
        Arrays.fill(visited, false);
    }

    public boolean isVisited(int v)
    {
        return visited[v];
    }

    public ArrayList<Integer> edgeList(int v)
    {
        return adjList[v];
    }
}

class Reader
{
    BufferedReader bufferedReader;
    StringTokenizer stringTokenizer;

    public Reader(){
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String next(){
        while (stringTokenizer == null || !stringTokenizer.hasMoreElements()){
            try{
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            }
            catch (IOException Ex){
                Ex.printStackTrace();
            }
        }
        return stringTokenizer.nextToken();
    }

    public int nextInt(){
        return Integer.parseInt(this.next());
    }

    public long nextLong(){
        return Long.parseLong(this.next());
    }

    public double nextDouble(){
        return Double.parseDouble(this.next());
    }

    public String nextLine(){
        String str = "";
        try{
            str = bufferedReader.readLine();
        }
        catch (IOException Ex){
            Ex.printStackTrace();
        }
        return str;
    }

    public boolean ready() throws IOException {
        return bufferedReader.ready();
    }
}

/*
**Sample Graph**
Nodes : 6
Edges : 7
0 1
0 3
1 2
2 3
2 4
3 5
4 5
 */
