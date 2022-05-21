import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in= new Scanner(System.in);
        ArrayList<String> arrayList = new ArrayList<String>();
        String s=in.nextLine();
        while(s.charAt(0)!='#'){
            if(s.charAt(0) == '+')
                arrayList.add(s.substring(2,s.length()));
            else {
                String string=s.substring(2);
                boolean b=false;
                for(String st:arrayList)
                    if(st.equals(string)){
                        b=true;
                        break;
                    }
                System.out.println(b?"YES":"NO");
            }
            s=in.nextLine();
        }
        System.out.flush();
    }


    static class FastScanner
    {
        BufferedReader br;
        StringTokenizer stok;

        FastScanner (InputStream is)
        {
            br = new BufferedReader(new InputStreamReader(is));
        }

        String nextToken() throws IOException {
            while (stok == null || !stok.hasMoreTokens()) {
                String s = br.readLine();
                if (s == null) {
                    return null;
                }
                stok = new StringTokenizer(s);
            }
            return stok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(nextToken());
        }

        long nextLong() throws IOException {
            return Long.parseLong(nextToken());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(nextToken());
        }

        char nextChar() throws IOException {
            return (char) (br.read());
        }

        String nextLine() throws IOException {
            return br.readLine();
        }
    }
}