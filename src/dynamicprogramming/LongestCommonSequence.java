package dynamicprogramming;

import static java.lang.Integer.max;

class LongestCommonSequence {
    public static void main(String[] args) {
        String text1 = "AGGTAB";
        String text2 = "GXTXAYB";
        System.out.println(computeLCS(text1, text2));
    }

    static int computeLCS(String text1, String text2){
        int[][] memory = new int[text1.length() + 1][text2.length() + 1];

        for (int i=0;i<=text1.length();i++){
            for (int j=0;j<=text2.length();j++){
                if (i==0||j==0){
                    memory[i][j] = 0;
                }
                else if (text1.charAt(i-1)==text2.charAt(j-1)){
                    memory[i][j] = memory[i-1][j-1] + 1;
                }
                else {
                    memory[i][j] = max(memory[i-1][j], memory[i][j-1]);
                }
            }
        }

        return memory[text1.length()][text2.length()];
    }

}