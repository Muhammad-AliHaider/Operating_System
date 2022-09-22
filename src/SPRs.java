public class SPRs{


    public static byte zero = 0;
    public static byte[] code_reg = new byte[3];
    public static byte[] stack_reg = new byte[3];
    public static byte[] data = new byte[2];
    public static byte[] register_future = new byte[6];
    private static byte flag = 4; // 0 = carry bit , 1 = zero bit , 2 = sign bit , 3 = overflow bit , 4 = unused




    public static void set_flag(int x){
        flag = (byte) x;
    }
    public static byte get_flag(){
        return flag;
    }

}
