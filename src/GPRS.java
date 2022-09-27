public class GPRS {

    public static short[] gprs = new short[16];

    public static void show_in_decimal(){
        for(int i = 0 ; i < 16; i++)
        {
            if(gprs[i]<=255)
            System.out.println("R" + (i)   + " = 0 | " + gprs[i]  );
            else
            {
                System.out.println("R" + (i)   + " = " + Short.toString((short)(gprs[i]-(short)(255))) + " | 255 " );
            }
        }
    }
    public void show_in_hex(){
        for(int i = 0 ; i < 16; i++)
            System.out.println( Integer.toHexString(i) + " = " + gprs[i] );
    }



}
