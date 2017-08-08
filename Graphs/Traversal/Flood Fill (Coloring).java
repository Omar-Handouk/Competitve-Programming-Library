import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main{

    static ArrayList<char[]> grid = new ArrayList<>();
    static ArrayList<int[]> queries = new ArrayList<>();
    static boolean[][] visited;

    static int[] dx = {0,-1,1,1,0,1,-1,-1};
    static int[] dy = {1,1,0,1,-1,-1,0,-1};

    static int flood(int x, int y){

        if (!isValid(x,y)) return 0;

        visited[x][y] = true;

        int c = 1;

        for (int i = 0 ; i < 8 ; i++){
            int a = x + dx[i], b = y + dy[i];

            if (isValid(a, b) && !visited[a][b] && grid.get(a)[b] == 'W')
                c += flood(a, b);
        }

        return c;
    }

    static boolean isValid(int x, int y){

        return 0 <= x && x < grid.size() && 0 <= y && y < grid.get(x).length;
    }

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args){

        //********This is Just the Input********

        while (true){

            char[] seq = in.nextLine().toCharArray();

            if (seq.length == 0)
                break;
            else if ('0' <= seq[0] && seq[0] <= '9') {

                String s = "";
                for (char e : seq)
                    s += e;

                String[] t = s.split(" ");
                queries.add(new int[]{Integer.parseInt(t[0]), Integer.parseInt(t[1])});
            }
            else
                grid.add(seq);
        }
        visited = new boolean[grid.size()][grid.get(0).length];

        for (int[] e : queries){
            System.out.println(flood(e[0] - 1, e[1] - 1));

            for (boolean[] row : visited)
                Arrays.fill(row, false);
        }
        System.out.println();

        grid = new ArrayList<>();
        queries = new ArrayList<>();
    }
}

/*
**Sample Graph (UVa-469-Wet Lands of Florida)**
LLLLLLLLL
LLWWLLWLL
LWWLLLLLL
LWWWLWWLL
LLLWWWLLL
LLLLLLLLL
LLLWWLLWL
LLWLWLLLL
LLLLLLLLL
3 2
7 5

 */
