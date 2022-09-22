public class SPRs{


    public static short zero = 0;
    public static short[] code_reg = new short[3];
    public static short[] stack_reg = new short[3];
    public static short[] data = new short[2];
    public static short[] register_future = new short[6];
    private static short flag = 0; // 0 = carry bit , 1 = zero bit , 2 = sign bit , 3 = overflow bit , 4 = unused




    public static void set_flag(int x){
        switch(x){
            case 0:
                flag = (short)  (flag | 0x0001);
                break;
            case 1:
                flag = (short)  (flag | 0x0002);
                break;
            case 2:
                flag = (short)  (flag | 0x0004);
                break;
            case 3:
                flag = (short)  (flag | 0x0008);
                break;

            case 4:
                flag = (short)  (flag | 0x0010);
                break;
        }



    }
    public static short get_flag(){
        return flag;
    }

}
