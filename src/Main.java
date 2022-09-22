import java.math.BigInteger;

public class Main {

    private static short bytesToShort(String a, String b) {
        short sh = Short.parseShort(a);
        //sh <<= 8;
        short ret = (short)(sh | Short.parseShort(b));
        return ret;
    }

    public static void main(String[] args)
    {
        byte original = (byte) 145;
        byte originala = (byte) 127;

        System.out.println(bytesToShort(String.valueOf(original  + 256),String.valueOf(originala  + 256)));

        if (original < 0) {
            short x = (short)(Integer.parseInt(String.valueOf(original  + 256)) + Integer.parseInt(String.valueOf(original  + 256)));

            System.out.println(x);
        }
        else
            System.out.println(new BigInteger(String.valueOf(original)).toString());
        System.out.println("Hello world!");
    }
}