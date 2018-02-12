package Mathmatics;

public class Generating_Subsets {
    
    //Complexity O(2^n)
    
    public void generate(int arr[])
    {
        for (int i = 1 ; i < (1 << arr.length) ; ++i)
        {
            for (int j = 0 ; j < arr.length ; ++j)
            {
                if ((i & (1 << j)) != 0)
                    System.out.print(arr[j] + " ");
            }

            System.out.println();
        }
    }

    private void func(int num)
    {
        System.out.println(num);
    }
}
