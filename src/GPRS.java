public class GPRS {

    public static short[] gprs = new short[16];

    public static void show_in_decimal(){
        for(int i = 0 ; i < 16; i++)
            System.out.println("R" + (i)   + " = " + gprs[i] );
    }
    public void show_in_hex(){
        for(int i = 0 ; i < 16; i++)
            System.out.println( Integer.toHexString(i) + " = " + gprs[i] );
    }



}
