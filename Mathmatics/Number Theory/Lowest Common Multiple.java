import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        int numA = in.nextInt();
        int numB = in.nextInt();

        System.out.println(lcm(numA, numB));
    }

    static int gcd(int a, int b) {
        if (b == 0)
            return a;

        if (b > a)
            return gcd(b, a);

        return gcd(b, a % b);
    }

    static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
