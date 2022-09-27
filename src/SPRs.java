import java.sql.SQLOutput;

public class SPRs{


    public static short zero = 0;
    public static short[] code_reg = new short[3];
    public static short[] stack_reg = new short[3];
    public static short[] data_reg = new short[2];
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
                flag = (short)  (flag & 0x0010);
                flag = (short)  (flag | 0x0010);
                break;
        }
    }
    public static short get_flag(){
        return flag;
    }

    public static void display_sprs(){

        System.out.println("Zero Register");
        System.out.println("Z = " +  Short.toString(zero));
        System.out.println("Code_registers");
        for(int i = 0 ; i < code_reg.length;i++)
        {
            System.out.println("c"+ i + " = " + Short.toString(code_reg[i]));
        }

        System.out.println("Data_Registers");
        for(int i = 0 ; i < data_reg.length;i++)
        {
            System.out.println("d"+ i + " = " + Short.toString(data_reg[i]));
        }

        System.out.println("Stack_Registers");
        for(int i = 0 ; i < stack_reg.length;i++)
        {
            System.out.println("s"+ i + " = " + Short.toString(stack_reg[i]));
        }

        System.out.println("Future_Registers");
        for(int i = 0 ; i < register_future.length;i++)
        {
            System.out.println("FR"+ i + " = " + Short.toString(register_future[i]));
        }

        System.out.println("Flag Register");
        System.out.println("Flag = " + get_flag());

    }
}
