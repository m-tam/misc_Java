/*
1. Пересекающиеся прямоугольники
Дан набор прямоугольников, заданных двумерными координатами пары противоположных вершин (левой нижней и правой верхней).
Прямоугольники могут пересекаться друг с другом.
Найдите общую площадь, которую покрывают эти прямоугольники.

Пример входных данных:
0 1 3 3
2 2 6 4
1 0 3 5

Пример выходных данных:
18
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class HH_school_1
{
    /*Number of coords corresponding to 2 points*/
    public final static int NUM_INPUT_COORDS = 4;    
    /*Initial number of points on the coordinate axis (excluding zero)*/
    public final static int INIT_NUM_POINTS = 99;
    /*Points multiplier for higher accuracy of Monte Carlo method)*/
    public final static int ACC_MULT = 100;          

    public static class Rectangle                    
    {
        private int bottomLeftX;
        private int bottomLeftY;
        private int topRightX;
        private int topRightY;

        public Rectangle(int bottomLeftX, int bottomLeftY, int topRightX, int topRightY)
        {
            this.bottomLeftX = bottomLeftX;
            this.bottomLeftY = bottomLeftY;
            this.topRightX = topRightX;
            this.topRightY = topRightY;

        }
        
        @Override
        public String toString()
        {
            String this_rect = "" + this.getBottomLeftX() + "" + this.getBottomLeftY() + "" + this.getTopRightX() + "" + this.getTopRightY() + "";
            return this_rect;
        }

    }

    public static double MonteCarlo (ArrayList<Rectangle> input)
{
	/*Number of points hit the target area*/
    int numPoints = 0; 
	
	/*Total number of points in the area*/
    int initPoints = (INIT_NUM_POINTS * ACC_MULT + 1) * (INIT_NUM_POINTS * ACC_MULT + 1);       
    int initSquare = INIT_NUM_POINTS * INIT_NUM_POINTS;   
	
	/*Square of the target area*/
    double finalSquare; 
	
    int matrix [][] = new int[INIT_NUM_POINTS * ACC_MULT + 1][INIT_NUM_POINTS * ACC_MULT + 1]; 

	/*Indices of matrix (matrix of points) elements: x (i) coord varying from x1 (iStart) to x2 (iMax); y (j) coord varying from y1 (jStart) to y2 (jMax)*/	
    int i, iStart, iMax, j, jStart, jMax;                                                       

    Iterator<Rectangle> r_iter = input.iterator();

    Rectangle rect;
    while (r_iter.hasNext())                                                                    
    {
        rect = r_iter.next();
		/*Converting the coords into a new scale system for reaching higher accuracy of the method*/
        iStart = rect.getBottomLeftX() * ACC_MULT;                                              
        i = iStart;
        iMax = rect.getTopRightX() * ACC_MULT;
        jStart = rect.getBottomLeftY() * ACC_MULT;
        j = jStart;
        jMax = rect.getTopRightY() * ACC_MULT;

        do {

            do {
				/*Setting the matrix element into 1 to indicate the point hit the target area*/
                matrix[i][j] = 1;                                                                
                j++;

            } while (j <= jMax);

            i++;
            j = jStart;

        } while(i <= iMax);
    }



    for (int k = 0; k < matrix.length; k++)
    {
        for (int l = 0; l < matrix[0].length; l++)
        {
			/*Counting the number of points hit the target area*/
            numPoints += matrix[k][l];                                                          
        }
    }
	/*Estimating the square of the target area*/
    finalSquare = initSquare * ((double)numPoints/(double)initPoints);                          

    return finalSquare;
}

    public static void main(String[] args)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Rectangle> input = new ArrayList<Rectangle>();                                 
        /*An input string containing coords*/
		String tryInput = "";                                                                   
        String spaces = "[ ]+";

        System.out.println("Please, insert " + NUM_INPUT_COORDS + " integer numbers in {0, 1, ..., 99} range, " +
                "corresponding to bottom left and top right coordinates \nof the rectangle accordingly, " +
                "separating them by space (each rectangle coordinates on the new line).\n" +
                "Press Enter 2 times after you have finished.");
        try {
			/* Reading the input data and inserting it into a list of rectangles*/
            while (true)                                                                           
            {
	
                tryInput = reader.readLine();
                String[] tryInputArray = tryInput.split(spaces);

                if(!(tryInput.equals("")))
                {

                input.add(new Rectangle(Integer.parseInt(tryInputArray[0]), Integer.parseInt(tryInputArray[1]), Integer.parseInt(tryInputArray[2]), Integer.parseInt(tryInputArray[3])));

                }

                else
                    break;
            }

        System.out.println((int)MonteCarlo(input));
        reader.close();
        }
        catch (Exception e)
        {
            System.out.println("Please, try running the program again! You haven't provided any sensible input.");
        }

    }
}
