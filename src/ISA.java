public class ISA {


    public short byte_short(byte a , byte b){
//        if(a < 0 && b < 0)
//            return (short)(Integer.parseInt(String.valueOf(a  + 256)) + Integer.parseInt(String.valueOf(b  + 256)));
//        else if (a > 0 && b < 0)
//            return (short)(Integer.parseInt(String.valueOf(a)) + Integer.parseInt(String.valueOf(b  + 256)));
//        else if(a < 0 && b > 0)
//            return (short)(Integer.parseInt(String.valueOf(a  + 256)) + Integer.parseInt(String.valueOf(b)));
//        else if(a > 0 && b > 0)
//            return (short)(Integer.parseInt(String.valueOf(a)) + Integer.parseInt(String.valueOf(b)));


        return (short)(Byte.toUnsignedInt(a) + Byte.toUnsignedInt(b));
    }
    public void Reset_carryBit() {
        SPRs.set_flag(4);
    }

    public void set_carryBit() {
        SPRs.set_flag(0);
    }

    public void set_ZeroBit() {
        SPRs.set_flag(1);
    }

    public void set_SignBit() {
        SPRs.set_flag(2);
    }

    public void set_overflowBit() {
        SPRs.set_flag(3);
    }

    public void setting_flag(int index) {
        if (Short.toUnsignedInt(GPRS.gprs[index]) < 0) {
            set_SignBit();
        } else if (Short.toUnsignedInt(GPRS.gprs[index]) == 0) {
            set_ZeroBit();
        }
    }

    public void Move(String R1, String R2) {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        int index_2 = Integer.parseInt(R2);

        GPRS.gprs[index_1 - 1] = GPRS.gprs[index_2 - 1];
        setting_flag(index_2 - 1);
    }

    public void Add(String R1, String R2) {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        int index_2 = Integer.parseInt(R2);

        if (Short.toUnsignedInt(GPRS.gprs[index_1 - 1]) + Short.toUnsignedInt(GPRS.gprs[index_2 - 1]) > 65536) {
            set_overflowBit();
        } else {
            GPRS.gprs[index_1 - 1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1 - 1]) + Short.toUnsignedInt(GPRS.gprs[index_2 - 1]));
            setting_flag(index_1 - 1);
        }
    }

    public void Sub(String R1, String R2) {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        int index_2 = Integer.parseInt(R2);
        GPRS.gprs[index_1 - 1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1 - 1]) + Short.toUnsignedInt(GPRS.gprs[index_2 - 1]));
        setting_flag(index_1 - 1);
    }

    public void Mul(String R1, String R2) {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        int index_2 = Integer.parseInt(R2);

        if ((Short.toUnsignedInt(GPRS.gprs[index_1 - 1]) * Short.toUnsignedInt(GPRS.gprs[index_2 - 1])) > 65536) {
            set_overflowBit();
        } else {
            GPRS.gprs[index_1 - 1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1 - 1]) * Short.toUnsignedInt(GPRS.gprs[index_2 - 1]));
            setting_flag(index_1 - 1);
        }
    }

    public void Div(String R1, String R2) {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        int index_2 = Integer.parseInt(R2);
        GPRS.gprs[index_1 - 1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1 - 1]) / Short.toUnsignedInt(GPRS.gprs[index_2 - 1]));
        setting_flag(index_1 - 1);

    }

    public void And(String R1, String R2) {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        int index_2 = Integer.parseInt(R2);
        GPRS.gprs[index_1 - 1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1 - 1]) & Short.toUnsignedInt(GPRS.gprs[index_2 - 1]));
        setting_flag(index_1 - 1);
    }

    public void Or(String R1, String R2) {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        int index_2 = Integer.parseInt(R2);
        GPRS.gprs[index_1 - 1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1 - 1]) | Short.toUnsignedInt(GPRS.gprs[index_2 - 1]));
        setting_flag(index_1 - 1);
    }

    public void Addi(String R1, short num) {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        if ((Short.toUnsignedInt(GPRS.gprs[index_1 - 1]) + Short.toUnsignedInt(num)) > 65536) {
            set_overflowBit();
        } else {
            GPRS.gprs[index_1 - 1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1 - 1]) + Short.toUnsignedInt(num));
            setting_flag(index_1 - 1);
        }
    }

    public void Subi(String R1, short num) {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);

        GPRS.gprs[index_1 - 1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1 - 1]) - Short.toUnsignedInt(num));
        setting_flag(index_1 - 1);

    }

    public void Muli(String R1, short num) {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        if ((Short.toUnsignedInt(GPRS.gprs[index_1 - 1]) * Short.toUnsignedInt(num)) > 65536) {
            set_overflowBit();
        } else {
            GPRS.gprs[index_1 - 1] = (short) ((Short.toUnsignedInt(GPRS.gprs[index_1 - 1]) * Short.toUnsignedInt(num)));
            setting_flag(index_1 - 1);
        }
    }

    public void Divi(String R1, short num) {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);

        GPRS.gprs[index_1 - 1] = (short) ((Short.toUnsignedInt(GPRS.gprs[index_1 - 1]) / Short.toUnsignedInt(num)));
        setting_flag(index_1 - 1);

    }

    public void Andi(String R1, short num) {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);

        GPRS.gprs[index_1 - 1] = (short) (GPRS.gprs[index_1 - 1] & num);
        setting_flag(index_1 - 1);

    }

    public void Ori(String R1, short num) {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);

        GPRS.gprs[index_1 - 1] = (short) (GPRS.gprs[index_1 - 1] * num);
        setting_flag(index_1 - 1);

    }


    public void BZ(short num) {
        if (SPRs.get_flag() == 1) {
            Memory.pc = Memory.jc + num;
        }
    }

    public void BNZ(short num) {
        if (SPRs.get_flag() != 1) {
            Memory.pc = Memory.jc + num;
        }
    }

    public void BC(short num) {
        if (SPRs.get_flag() == 0) {
            Memory.pc = Memory.jc + num;
        }
    }

    public void BS(short num) {
        if (SPRs.get_flag() == 0) {
            Memory.pc = Memory.jc + num;
        }
    }

    public void JMP(short num) {
        Memory.pc = Memory.jc + num;
    }


//    public void MOVL(String R1) {
//        int index_1 = Integer.parseInt(R1);
//
//        GPRS.gprs[index_1 - 1] = Byte.valueOf((byte) (Memory.memory[Memory.pc] + Memory.memory[Memory.pc + 1])); // issue is how to put 2 bytes in 1 byte instance of an array gprs
//    }


    public void MOVL(String R1 , int x )// x shouldnt be a multiple of 2 (add check)
    {
        int index_1 = Integer.parseInt(R1);

        GPRS.gprs[index_1 - 1] = byte_short( Memory.memory[Memory.pc + x], Memory.memory[Memory.pc +x + 1]);
    }

    public void MOVS(String R1, int offset )
    {
        Reset_carryBit();
        int index_1 = Integer.parseInt(R1);
        String temp = String.valueOf(GPRS.gprs[index_1 - 1]);
        String[] arr = new String[temp.length()];
        for (int i = 0; i < temp.length(); i++) {
            arr = temp.split("");
        }
        String s1 = "";
        String s2 = "";
        switch (arr.length) {
            case 1:
                s1 = "00";
                s2 = "0" + arr[0];
                break;

            case 2:
                s1 = "00";
                s2 = arr[0] + arr[1];
                break;
            case 3:
                s1 = "0" + arr[0];
                s2 = arr[1] + arr[2];
                break;
            case 4:
                s1 = arr[0] + arr[1];
                s2 = arr[2] + arr[3];
                break;
        }

        Memory.memory[Memory.pc + offset] = Byte.valueOf(s1);
        Memory.memory[Memory.pc + offset+ 1] = Byte.valueOf(s2);

        setting_flag(GPRS.gprs[index_1-1]);
    }

    public void SHL(String R1){
        int index_1 = Integer.parseInt(R1);
        if((GPRS.gprs[index_1-1] & 0x8000) == 0x8000 )
        {
            set_carryBit();
        }
        GPRS.gprs[index_1-1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1-1]) << 1);
        setting_flag(index_1 - 1);
    }

    public void SHR(String R1){
        int index_1 = Integer.parseInt(R1);

//        if(((GPRS.gprs[index_1-1] )  >> 1) > 0)
//            GPRS.gprs[index_1-1] = (short)((GPRS.gprs[index_1-1] )  << 1);
//
//        else if(((GPRS.gprs[index_1-1] )  << 1) < 0){
//            GPRS.gprs[index_1-1] = (short)(((GPRS.gprs[index_1-1] )  << 1)+32768);
//        }

        GPRS.gprs[index_1-1] = (short) ((short)(Short.toUnsignedInt(GPRS.gprs[index_1-1]) >> 1)& 0x0FFF); // ensures that the most sig bit is 0
        setting_flag(index_1 - 1);
    }

    public void RTL(String R1){
        int index_1 = Integer.parseInt(R1);
        if((GPRS.gprs[index_1-1] & 0x8000) == 0x8000 )
        {
            set_carryBit();
        }
        if(Short.toUnsignedInt(GPRS.gprs[index_1-1]) >=  32768){
//            set_carryBit();
            GPRS.gprs[index_1-1] = (short)((GPRS.gprs[index_1-1] << 1)  + 1);
        }
        else{
            GPRS.gprs[index_1-1] = (short)(GPRS.gprs[index_1-1]<<1);
        }
        setting_flag(index_1 -1);
    }

    public void RTR(String R1){
        int index_1 = Integer.parseInt(R1);
        if(Short.toUnsignedInt(GPRS.gprs[index_1]) %2 == 1 && Short.toUnsignedInt(GPRS.gprs[index_1]) < 32768){
//            System.out.println(Byte.toUnsignedInt((byte)((a>>1) + 32768)));
            GPRS.gprs[index_1-1] = (short) (GPRS.gprs[index_1-1] + 32768);
//            a = (byte)((a >> 1)  + 128);
//            System.out.println(a);
        }
        else if(Short.toUnsignedInt(GPRS.gprs[index_1]) >=32768 && Short.toUnsignedInt(GPRS.gprs[index_1]) %2 == 1 ){
            GPRS.gprs[index_1-1] =  (short)((GPRS.gprs[index_1-1]>>1));
        }
        else if(Short.toUnsignedInt(GPRS.gprs[index_1]) >=32768 && Short.toUnsignedInt(GPRS.gprs[index_1]) %2 == 0 ){
            GPRS.gprs[index_1-1] =  (short)((GPRS.gprs[index_1-1]>>1) & 0x7FFF);
        }
        else{
            GPRS.gprs[index_1-1] =  (short)((GPRS.gprs[index_1-1]>>1));
        }
    }

    public void INC(String R1){
        Reset_carryBit();
        int index_1 = Integer.parseInt(R1);
        if((GPRS.gprs[index_1-1]&0xFFFF) == 0xFFFF)
            set_overflowBit();
        else
        GPRS.gprs[index_1-1] = (short)(Short.toUnsignedInt(GPRS.gprs[index_1-1]) + 1);

        setting_flag(index_1 - 1);
    }

    public void DEC(String R1){
        Reset_carryBit();
        int index_1 = Integer.parseInt(R1);
        GPRS.gprs[index_1-1] = (short)(Short.toUnsignedInt(GPRS.gprs[index_1-1]) - 1);

        setting_flag(index_1 - 1);
    }

    public void NOOP()
    {
    }






}

