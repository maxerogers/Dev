/********************************************************************
*   This program is written to solve instances of the { 0,1 } knapsack
* problem.  
*
*  This program reads input from an external file, supplied to the
* program upon invocation (e.g.  ./knapsack <input.txt).
*  
*  The format of the input file is 
*     numItems  maxWeight
*       benefit_1  weight_1  benefit_2  weight_2 ... benefit_n weight_n
*     numItems maxWeight
*       benefit_1  weight_1  benefit_2  weight_2 ... benefit_k weight_k
*     ...
*     ...
*     0 0
* 
* In other words, the number of items in the instance, followed by the
* maximum allowed weight.  The next line contains all of the 
* benefit, weight pairs (but there's no commas in between them!!).   
*
*   The input is terminated by two 0s. 
*
********************************************************************
*  This software is released under the MIT (Expat) License.  
*
* Copyright (C) 2012  Russell Martin
*
* Permission is hereby granted, free of charge, to any person 
* obtaining a copy of this software and associated documentation 
* files (the "Software"), to deal in the Software without 
* restriction, including without limitation the rights to use, copy, 
* modify, merge, publish, distribute, sublicense, and/or sell copies 
* of the Software, and to permit persons to whom the Software is 
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be 
* included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
* OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
* HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
* WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
* DEALINGS IN THE SOFTWARE.
* 
*******************************************************************/

import java.io.*;
import java.util.*;

class Knapsack
{
   public static void main (String args[]) // entry point from OS
   {
      new MyClass(new Scanner(System.in));  // create dynamic entry point
   }
}

class MyClass {

   MyClass (Scanner s)
   {
      Scanner ls;
      int B[][];
      int numItems, maxWeight, w, k;
      int benefit[], weight[];
      int remainingWeight;
      int setNumber = 1;

    //***  Read in the initial numItems, maxWeight pair
      ls = new Scanner(s.nextLine());
      numItems = ls.nextInt();
      maxWeight = ls.nextInt();
 
    while ( (numItems != 0) && (maxWeight != 0) )
    {
      // *** Read in all the data for the items
          benefit = new int [numItems+1];
          weight = new int [numItems+1];
          ls = new Scanner(s.nextLine());
          for (k = 1; k <= numItems; k++)
              {  
                  benefit [k] = ls.nextInt();
                  weight [k] = ls.nextInt();
              }

      // *** initialize
          B = new int [numItems+1][maxWeight+1]; 
          for (w = 0; w <= maxWeight; w++) 
                    B[0][w] = 0; 

      // *** Now do the work!
          for (k = 1; k <= numItems; k++)
              {
                  for (w = maxWeight; w >= weight[k]; w--)
                      if (benefit[k] + B[k-1][w-weight[k]] > B[k-1][w])
                         B[k][w] = benefit[k] + B[k-1][w-weight[k]];
                      else
                         B[k][w] = B[k-1][w];
                  for (w = 0; w < weight[k]; w++)
                         B[k][w] = B[k-1][w];
              }

      // *** Print out the results.   
          System.out.println("Set #" + setNumber);
          System.out.println("Max benefit = " + B[numItems][maxWeight]);
          System.out.print("Items used:");
          for (k = numItems, remainingWeight=maxWeight; k > 0; k--)
              {
                if (remainingWeight >= weight[k])
                   if ( B[k][remainingWeight] == (benefit[k] + B[k-1][remainingWeight - weight[k]]) )
                    {
                       System.out.print("  " + k);
                       remainingWeight -= weight[k];
                    }
              }
              System.out.println();
              System.out.println();
              setNumber++;

      // ***  Read in the next numItems, maxWeight pair
      ls = new Scanner(s.nextLine());
      numItems = ls.nextInt();
      maxWeight = ls.nextInt();
   }  
 
 }

}  //**  end of the "MyClass" class