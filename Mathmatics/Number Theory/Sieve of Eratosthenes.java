import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args){

        int range = in.nextInt();

        boolean[] unfilteredPrimes = sieve(range);

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 0 ; i <= range ; i++)
            if (unfilteredPrimes[i])
                primes.add(i);

        //System.out.println(primes.toString());
    }

    static boolean[] sieve(int range){

        boolean[] isPrime = new boolean[range + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;

        for (int i = 2 ; i * i <= range ; i++){

            if (isPrime[i]){
                for (int j = i ; i * j <= range ; j++)
                    isPrime[i * j] = false;
            }
        }

        return isPrime;
    }

}
