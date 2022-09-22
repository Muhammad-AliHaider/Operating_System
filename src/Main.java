import jdk.jfr.Unsigned;

import java.math.BigInteger;

public class Main {

    private static short bytesToShort(String a, String b) {
        short sh = Short.parseShort(a);
        //sh <<= 8;
        short ret = (short)(sh | Short.parseShort(b));
        return ret;
    }
//    public static void rotate(int N, int D)
//    {
//        // your code here
//        int t=16;
//        //System.out.println(N);
//        int left= ((N<<D) | N>>(t-D)) & 0xFFFF;
//        left = ((N << 1) | N>>(t-1)) & 0xFF;
//        int right=((N>>D) | N<<(t-D)) & 0xFFFF;
//        System.out.println(left);
////        System.out.println(right);
//    }

    public static byte rotateRight(byte a){
        if(Byte.toUnsignedInt(a) %2 == 1 && Byte.toUnsignedInt(a) < 128){
            System.out.println(Byte.toUnsignedInt((byte)((a>>1) + 128)));
//            a = (byte)((a >> 1)  + 128);
//            System.out.println(a);
        }
        else if(Byte.toUnsignedInt(a) >=128 && Byte.toUnsignedInt(a) %2 == 1 ){
            a =  (byte)((a>>1));
        }
        else if(Byte.toUnsignedInt(a) >=128 && Byte.toUnsignedInt(a) %2 == 0 ){
            a =  (byte)((a>>1) & 0x7F);
        }
        else{
            a = (byte)(a>>1);
        }
        return a;
    }
    public static void main(String[] args)
    {
        byte original = (byte) 66;
        byte originala = (byte) 128;

//        if(Byte.toUnsignedInt(original) >=  128)
//            System.out.println("llll");

        System.out.println(rotateRight(original));
        System.out.println(Byte.toUnsignedInt(rotateRight(original)));

        short p =(short) (Byte.toUnsignedInt(original) + Byte.toUnsignedInt(originala));

        originala = (byte)(Byte.toUnsignedInt(original) << 1);
        //System.out.println(Byte.toUnsignedInt(original) << 1);
        //System.out.println(Byte.toUnsignedInt(originala));

//        System.out.println(bytesToShort(String.valueOf(original  + 256),String.valueOf(originala  + 256)));

//        System.out.println(original + 256);
//        int q = Byte.toUnsignedInt(original >> 1);



//        if (original < 0) {
//            short x = (short)(Integer.parseInt(String.valueOf(original  + 256)) + Integer.parseInt(String.valueOf(original  + 256)));
//
////            System.out.println(x);
//        }
//        else
//            System.out.println(new BigInteger(String.valueOf(original)).toString());
        System.out.println("Hello world!");
    }
}