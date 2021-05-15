package dynamicprogramming;

import java.util.Arrays;

import static java.lang.Integer.max;

public class LongestIncreasingSubSequence {
    public static void main(String[] args) {
        int[] firstArray = {50,3,10,7,40,80};
        int[] secondArray = Arrays.stream(firstArray).sorted().toArray();
        System.out.println(computeLIS(firstArray, secondArray));
    }

    static int computeLIS(int[] firstArray, int[] secondArray){
        int[][] memory = new int[firstArray.length + 1][secondArray.length + 1];

        for (int i=0;i<=firstArray.length;i++){
            for (int j=0;j<=secondArray.length;j++){
                if (i==0||j==0){
                    memory[i][j] = 0;
                }else if (firstArray[i - 1] == secondArray[j-1]){
                    memory[i][j] = 1 + memory[i-1][j-1];
                }else {
                    memory[i][j] = max(memory[i-1][j], memory[i][j-1]);
                }

            }
        }

        return memory[firstArray.length][secondArray.length];
    }

}
