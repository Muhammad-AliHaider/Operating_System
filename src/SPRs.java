public class SPRs{

public static byte[] sprs = new byte[16];

public void show_in_decimal(int index){
    for(int i = 0 ; i < 16; i++)
        System.out.println("R" + (i - 1)   + " = " + sprs[i] );
}
public void show_in_hex(){
    for(int i = 0 ; i < 16; i++)
        System.out.println( Integer.toHexString(i+1) + " = " + sprs[i] );
}

}
