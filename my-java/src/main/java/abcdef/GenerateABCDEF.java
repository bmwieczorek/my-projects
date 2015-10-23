package abcdef;

public class GenerateABCDEF {
    
    private static final int USED_LETTERS_SET_SIZE = 26;
    private static final int OUTPUT_STRING_LENGHT = 6;


    public static void main(String[] args) {
        for (int n = 0; n < 100000; n++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < OUTPUT_STRING_LENGHT; i++) {
                sb.append(getChar(n, OUTPUT_STRING_LENGHT - i - 1));
            }
            System.out.println(sb);
        }
    }
    
    private static char getChar(int i, int power) {
        int number =  (i / ((int)Math.pow(USED_LETTERS_SET_SIZE, power))) % USED_LETTERS_SET_SIZE ;
        return (char)(number + 65);
    }
}

