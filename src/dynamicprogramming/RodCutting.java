package dynamicprogramming;
/**
 * Author: Nagavelu C
 * 
 * Rod cutting problem using Dynamic Programming
 * 
 */
public class RodCutting {
	/* Problem Statement: Given a rod of length n inches and a
	   table of prices , determine the maximum revenue obtainable 
	   by cutting up the rod and selling the pieces */
	public static void main(String[] args){
		int[] prices = {1,5,8,9,10,17,17,20,24,30};
		int size = 4;
		// top down approach using recursion without memoization
		int[] soln = new int[size+1];
		int maximumRevenue = Cut_Rod(prices, size);
		System.out.println("Top-down without memoization: "+maximumRevenue);
		int[] revenueForEachInch = new int[size];
		for(int i=0;i<size;i++){
			revenueForEachInch[i] = -1; 
		}
		// top down approach using recursion with memoization
		maximumRevenue = Cut_Rod(prices, size,soln, revenueForEachInch);
		
		System.out.println("Top-down with memoization: "+maximumRevenue);
		int sizeOfRod = size;
		while(sizeOfRod > 0){
			System.out.println(soln[sizeOfRod]);
			sizeOfRod = sizeOfRod - soln[sizeOfRod];
		}
		// bottom up approach
		maximumRevenue = Cut_Rod(prices, size, false);
		System.out.println("Bottom-up: without solution set: "+maximumRevenue);

		maximumRevenue = Cut_Rod(prices, size, true);
		System.out.println("Bottom-up: with solution set: "+maximumRevenue);
	}
	
	public static int Cut_Rod(int[] prices, int size){
		// If size of the rod is 0 then there is no way to obtain 
		// revenue
		if(size==0){
			return 0;
		}
		int revenue = -1;
		for(int i=0;i<size;i++){
			revenue = Max(revenue, prices[i]+Cut_Rod(prices, size - (i+1)));
		}
		return revenue;
	}
	
	public static int Cut_Rod(int[] prices, int size, int[] soln, int[] revenueForEachInch){
		if(size==0){
			return 0;
		}
		if(revenueForEachInch[size-1]>0){
			return revenueForEachInch[size-1];
		}
		int revenue = -1;
		for(int i=0;i<size;i++){
			int nextValue = prices[i]+Cut_Rod(prices, size - (i+1), soln, revenueForEachInch);
			if(revenue< nextValue){
				revenue = nextValue;
				soln[size] = i+1;
			}
//			revenue = Max(revenue, );
		}
		revenueForEachInch[size-1] = revenue;
		
		return revenue;
	}
	
	/* bottom_up approach
	 * 
	 * to solve a problem of size j, solve problem of size i where i<=j
	 *  */
	public static int Cut_Rod(int[] prices, int size, boolean printSolution){
		int[] revenue = new int[size+1];
		for(int i=1;i<size;i++){
			revenue[i] = -1;
		}
		// soln array stores optimal value to cut an inch
		int[] soln = new int[size+1];
 		for(int j=1;j<=size;j++){
			int r = -1;
			for(int i=1;i<=j;i++){
				int nextValue = prices[i-1] + revenue[j-i];
				if (r < nextValue){
					r = nextValue;
					soln[j] = i;
				}
			}
			revenue[j] = r;
		}
		if(printSolution){
			// will print solution
			int sizeOfRod = size;
			while(sizeOfRod > 0){
				System.out.println(soln[sizeOfRod]);
				sizeOfRod = sizeOfRod - soln[sizeOfRod];
			}
			
		}
		return revenue[size];
	}
	
	public static int Max(int a, int b){
		if(a > b){
			return a;
		}
		return b;
	}
}
