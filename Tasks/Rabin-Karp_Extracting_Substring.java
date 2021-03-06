/*
Возьмём цифровую последовательность, образованную склеиванием последовательных положительных чисел: S = 123456789101112131415...
Определите первое вхождение заданной последовательности A в "бесконечной" последовательности S (нумерация начинается с 1).

Пример входных данных:
6789
101

Пример выходных данных:
6
10
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Rabin-Karp_Extracting_Substring {                 

	/*Divisor value for Rabin-Karp method*/
    public final static int MOD_DIVISOR = 10;                   
    
    public static int RabinKarp (String input)
    {
		/*A future sequence of concatenated successive positive integer numbers from 1 to 10000*/
        String sequence = ""; 
		
		/*A subsequence extracted from the sequence to compare it with the input*/		
        String subSequence = "";
		
		/*A supposed sought-for index*/	
        int candidateIndex;                                     
        int output = 0;
		
		/*Remainder of dividing input sequence by a chosen divisor*/
        int checker = Integer.parseInt(input)%MOD_DIVISOR;      
		
		/*Creating an infinite (not really) sequence of concatenated successive positive integer numbers from 1 to 10000*/
        for (int i = 1; i < 10000; i++)                        
        {
            sequence += Integer.toString(i);
        }

        for (int i = input.length() - 1; i < sequence.length(); i++)
        {
            candidateIndex = i - input.length() + 1;
            subSequence = sequence.substring(candidateIndex, i + 1);
            if ((Integer.parseInt(subSequence) % MOD_DIVISOR) == checker)
            {

                if(input.equals(subSequence))
                {
                    output = candidateIndex + 1;
                    break;
                }



            }
        }
        return output;
    }
            

    public static void main(String[] args)
    {
        String input = "";                      
        int output = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please, insert a subsequnce, which you're intending to find in the sequence of concatenated successive positive integer numbers from 1 to 10000: ");

        try
        {
            input = reader.readLine();
            output = RabinKarp(input);          
            if(output == 0)
            {
                System.out.println("Please, try running the program again! You haven't provided any sensible input.");
            }
            else
            {
                System.out.println(output);
            }

        }

        catch (Exception e)
        {
            System.out.println("Please, try running the program again! You haven't provided any sensible input.");
            
        }
    }
}

