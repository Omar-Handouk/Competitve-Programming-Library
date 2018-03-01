import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(findDivisors(100000000).toArray()));
    }

    public static ArrayList<Integer> findDivisors(int number)
    {
        ArrayList<Integer> divisors = new ArrayList<>();

        for (int i = 1 ; i * i <= number ; ++i)
        {
            if (number % i == 0)
            {
                if (number / i != i)
                {
                    divisors.add(i);
                    divisors.add(number / i);
                }
                else
                    divisors.add(i);
            }
        }

        return divisors;
    }
}