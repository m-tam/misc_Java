/*
2. Бесконечная последовательность
Возьмём бесконечную цифровую последовательность, образованную склеиванием последовательных положительных чисел: S = 123456789101112131415...
Определите первое вхождение заданной последовательности A в бесконечной последовательности S (нумерация начинается с 1).

Пример входных данных:
6789
101

Пример выходных данных:
6
10
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HH_school_2_v1                     // Variant 1
{
    public static void main(String[] args)
    {
        String sequence = "";                   // A future sequence of concatenated successive positive integer numbers from 1 to 10000
        String input = "";                      // A String for an input subsequence

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please, insert a subsequnce, which you're intending to find in the sequence of concatenated successive positive integer numbers from 1 to 10000: ");

        try                                                         // Creating an infinite (not really) sequence of concatenated successive positive integer numbers from 1 to 10000
        {
            input = reader.readLine();
            for (int i = 1; i < 10000; i++)
            {
                sequence += Integer.toString(i);
            }

            if (sequence.contains(input))
            {
                System.out.println(sequence.indexOf(input) + 1);     // Performing a naive search of the index
                reader.close();
            }
            else
            {
                System.out.println("Sorry, the sequence does not contain anything resembling the input you've provided!");
                reader.close();
            }

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
