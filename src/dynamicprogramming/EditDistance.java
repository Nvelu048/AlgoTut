package dynamicprogramming;

import static java.lang.Integer.min;

public class EditDistance {
    public static void main(String[] args) {
        String text1 = "saturday";
        String text2 = "sunday";
        System.out.println(computeEditDistance(text1, text2));
    }

    private static int computeEditDistance(String text1, String text2) {
        int[][] memory = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i <= text1.length(); i++) {
            for (int j = 0; j <= text2.length(); j++) {
                if (i == 0) {
                    memory[i][j] = j;
                } else if (j == 0) {
                    memory[i][j] = i;
                } else if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    memory[i][j] = memory[i - 1][j - 1];
                } else {
                    memory[i][j] = 1 + min(memory[i][j - 1], min(memory[i - 1][j], memory[i - 1][j - 1]));
                }

            }
        }
        return memory[text1.length()][text2.length()];
    }
}
