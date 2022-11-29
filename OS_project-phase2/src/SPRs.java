import java.sql.SQLOutput;

public class SPRs{


    public static short zero = 0; // zero_register
    public static short[] code_reg = new short[3]; // code_reg[0] = code base , code_reg[1] = code limit , code_reg[2] = code counter
    public static short[] stack_reg = new short[3];// stack_reg[0] = stack base , stack_reg[1] = stack limit , stack_reg[2] = stack counter
    public static short[] data_reg = new short[2];// data_reg[0] = data base , data_reg[1] = data limit
    public static short[] register_future = new short[6]; // 6 Register for future use
    private static short flag = 0; // 0 = carry bit , 1 = zero bit , 2 = sign bit , 3 = overflow bit , 4 = unused

    public static short[] sprs = new short[16];
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

    public static short[] formSPRS(){
        sprs[0] = zero;
        sprs[1] = code_reg[0];
        sprs[2] = code_reg[1];
        sprs[3] = code_reg[2];
        sprs[4] = stack_reg[0];
        sprs[5] = stack_reg[1];
        sprs[6] = stack_reg[2];
        sprs[7] = data_reg[0];
        sprs[8] = data_reg[1];
        sprs[9] = register_future[0];
        sprs[10] = register_future[1];
        sprs[11] = register_future[2];
        sprs[12] = register_future[3];
        sprs[13] = register_future[4];
        sprs[14] = register_future[5];
        sprs[15] = flag;

        return sprs;


    }

    public static void toSPRS(short[] sprs1){
         zero = sprs1[0]  ;
         code_reg[0] = sprs1[1] ;
         code_reg[1] = sprs1[2] ;
         code_reg[2] = sprs1[3]  ;
         stack_reg[0] = sprs1[4] ;
        stack_reg[1] = sprs1[5] ;
        stack_reg[2] = sprs1[6]  ;
        data_reg[0] = sprs1[7]  ;
        data_reg[1] = sprs1[8]  ;
        register_future[0] = sprs1[9] ;
        register_future[1] = sprs1[10] ;
        register_future[2] = sprs1[11];
        register_future[3] = sprs1[12];
        register_future[4] = sprs1[13];
        register_future[5] = sprs1[14] ;
        flag = sprs1[15] ;



    }
}
