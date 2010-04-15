package tokens;

import java.util.StringTokenizer;

public class MyToken {

    public static void main(String[] args) {
        String str = "Token A#$%@&Token B#$%@&Token C#$%@&Token D#$%@&Token E";
        String delim = "#$%@";
        StringTokenizer st = new StringTokenizer(str, delim);
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }

}
