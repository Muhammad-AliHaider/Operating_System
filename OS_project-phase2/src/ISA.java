import java.lang.management.MemoryPoolMXBean;

public class ISA {


    public short byte_short(byte a , byte b) // for joining two bytes
    {
        return (short)(Byte.toUnsignedInt(a) + Byte.toUnsignedInt(b));
    }


    public boolean is_stack_full(){
        if (SPRs.stack_reg[2] >=SPRs.stack_reg[1]){
            return true;
        }
        return false;
    }
    public void Reset_flag() // setting the 4th flag bit on , indicating that flag bit it unused  
    {
        SPRs.set_flag(4);
    }

    public void set_carryBit() // setting the 0th flag bit on, indicating that carry-bit is on 
    {
        SPRs.set_flag(0);
    }

    public void set_ZeroBit() // setting the 1th flag bit on, indicating that zero-bit is on (i.e. value is 0)
    {
        SPRs.set_flag(1);
    }

    public void set_SignBit() // setting the 2nd flag bit on, indicating that sign-bit is on (i.e. value is negative)
    {
        SPRs.set_flag(2);
    }

    public void set_overflowBit() // setting the 3rd flag bit on, indicating that overflow-bit is on (i.e. the value resulted in number greater than what can be stored in 2 bytes) 
    {
        SPRs.set_flag(3);
    }

    public void setting_flag(int index) {
        if (Short.toUnsignedInt(GPRS.gprs[index]) < 0) // checking for negative value 
        { 
            set_SignBit();
        }
//        System.out.println(Short.toUnsignedInt(GPRS.gprs[index]));
//        else if (Short.toUnsignedInt(GPRS.gprs[index]) == 0) // checking for value to be zero
        if (Short.toUnsignedInt(GPRS.gprs[index]) == 0)
        {
            set_ZeroBit();
        }
    }

    public void Move(String R1, String R2) {
//        Reset_flag(); //reseting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        int index_2 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R2)));

        GPRS.gprs[index_1] = GPRS.gprs[index_2];
        setting_flag(index_2); // setting the flag
    }

    public void Add(String R1, String R2) {
//        Reset_flag(); // resetting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        int index_2 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R2)));

        if (Short.toUnsignedInt(GPRS.gprs[index_1]) + Short.toUnsignedInt(GPRS.gprs[index_2]) > 65536) // checking for overflow
        {
            set_overflowBit();
        } else 
        {
            GPRS.gprs[index_1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1]) + Short.toUnsignedInt(GPRS.gprs[index_2]));
            setting_flag(index_1); // setting the flag
        }
    }

    public void Sub(String R1, String R2) {
//        Reset_flag(); // resetting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        int index_2 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R2)));
        if (Short.toUnsignedInt(GPRS.gprs[index_1]) - Short.toUnsignedInt(GPRS.gprs[index_2]) < -65536) { // checking for overflow
            set_overflowBit();
        } else {
            GPRS.gprs[index_1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1]) - Short.toUnsignedInt(GPRS.gprs[index_2]));
            setting_flag(index_1); // setting the flag
        }
    }

    public void Mul(String R1, String R2) {
//        Reset_flag(); // resetting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        int index_2 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R2)));

        if ((Short.toUnsignedInt(GPRS.gprs[index_1 ]) * Short.toUnsignedInt(GPRS.gprs[index_2 ])) > 65536) {// checking for overflow
            set_overflowBit();
        } else {
            GPRS.gprs[index_1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1]) * Short.toUnsignedInt(GPRS.gprs[index_2]));
            setting_flag(index_1); // setting the flag
        }
    }

    public void Div(String R1, String R2) {
//        Reset_flag(); // resetting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        int index_2 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R2)));
        GPRS.gprs[index_1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1]) / Short.toUnsignedInt(GPRS.gprs[index_2]));
        setting_flag(index_1); // setting the flag

    }

    public void And(String R1, String R2) {
//        Reset_flag(); // resetting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        int index_2 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R2)));
        GPRS.gprs[index_1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1]) & Short.toUnsignedInt(GPRS.gprs[index_2]));
        setting_flag(index_1); // setting the flag
    }

    public void Or(String R1, String R2) {
//        Reset_flag(); // resetting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        int index_2 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R2)));
        GPRS.gprs[index_1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1]) | Short.toUnsignedInt(GPRS.gprs[index_2 ]));
        setting_flag(index_1); // setting the flag
    }

    public void Movi(String R1,short num){
//        Reset_flag(); // resetting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        GPRS.gprs[index_1 ] = (short) (Short.toUnsignedInt(num));
        setting_flag(index_1); // setting the flag
    }


    public void Addi(String R1, short num) {
//        Reset_flag(); // resetting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        if ((Short.toUnsignedInt(GPRS.gprs[index_1]) + Short.toUnsignedInt(num)) > 65536) {// checking for overflow
            set_overflowBit();
        } else {
            GPRS.gprs[index_1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1 ]) + Short.toUnsignedInt(num));
            setting_flag(index_1); // setting the flag
        }
    }

    public void Subi(String R1, short num) {
//        Reset_flag(); // resetting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        if ((Short.toUnsignedInt(GPRS.gprs[index_1]) - Short.toUnsignedInt(num)) < -65536) {// checking for overflow
            set_overflowBit();
        } else {
            GPRS.gprs[index_1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1]) - Short.toUnsignedInt(num));
            setting_flag(index_1); // setting the flag
        }

    }

    public void Muli(String R1, short num) {
//        Reset_flag(); // resetting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        if ((Short.toUnsignedInt(GPRS.gprs[index_1]) * Short.toUnsignedInt(num)) > 65536) {// checking for overflow
            set_overflowBit();
        } else {
            GPRS.gprs[index_1] = (short) ((Short.toUnsignedInt(GPRS.gprs[index_1]) * Short.toUnsignedInt(num)));
            setting_flag(index_1); // setting the flag
        }
    }

    public void Divi(String R1, short num) {
//        Reset_flag(); // resetting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));

        GPRS.gprs[index_1 ] = (short) ((Short.toUnsignedInt(GPRS.gprs[index_1 ]) / Short.toUnsignedInt(num)));
        setting_flag(index_1); // setting the flag

    }

    public void Andi(String R1, short num) {
//        Reset_flag(); // resetting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));

        GPRS.gprs[index_1] = (short) (GPRS.gprs[index_1] & num);
        setting_flag(index_1); // setting the flag

    }

    public void Ori(String R1, short num) {
//        Reset_flag(); // resetting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));

        GPRS.gprs[index_1] = (short) (GPRS.gprs[index_1 ] | num);
        setting_flag(index_1); // setting the flag

    }


    public void BZ(short num) {
//        System.out.println(SPRs.get_flag());
//        System.out.println((SPRs.get_flag() & 0x0002));
        if ((SPRs.get_flag() & 0x0002) == 0x0002) { // checking if flag has a 0th bit on
            SPRs.code_reg[2] = (short)(SPRs.code_reg[0] + num);
        }
        else{
            SPRs.code_reg[2] += 2;
//            SPRs.code_reg[2]++;

        }
    }

    public void BNZ(short num) {
        if ((SPRs.get_flag() & 0x0002) != 0x0002) { // checking if flag has a 0th bit off
            SPRs.code_reg[2] = (short)(SPRs.code_reg[0] + num);
        }
        else{
            SPRs.code_reg[2] += 2;
//            SPRs.code_reg[2]++;
        }
    }

    public void BC(short num) {
        if ((SPRs.get_flag() & 0x0001) == 0x0001) { // checking if flag has a 1th bit on
            SPRs.code_reg[2] = (short)(SPRs.code_reg[0] + num);
        }
        else{
            SPRs.code_reg[2] += 2;
//            SPRs.code_reg[2]++;
        }
    }

    public void BS(short num) {
        if ((SPRs.get_flag() & 0x0004) == 0x0004) {// checking if flag has a 2th bit on
            SPRs.code_reg[2] = (short)(SPRs.code_reg[0] + num);
        }
        else{
            SPRs.code_reg[2] += 2;
//            SPRs.code_reg[2]++;
        }
    }

    public void JMP(short num) {
        SPRs.code_reg[2] = (short)(SPRs.code_reg[0] + num);
    }



    public void MOVL(String R1 , int x )
    {
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        int[] arr = Memory.tranlation(SPRs.data_reg[0] + x);
        byte s1 = Memory.memory1[arr[0]].page[arr[1]];

        arr = Memory.tranlation(SPRs.data_reg[0] + x + 1);
        byte s2 = Memory.memory1[arr[0]].page[arr[1]];


        String S_combined_1 = Integer.toHexString(Byte.toUnsignedInt(s1));
        String S_combined_2 = convert(s2);

        String S_combined = S_combined_1 + S_combined_2;

//        byte s2 = Memory.memory1[arr[0]].page[arr[1]];
//        GPRS.gprs[index_1] = byte_short(s1,s2); // taking the value form memory to register
        GPRS.gprs[index_1] = (short)(Integer.parseInt(Integer.toString(Integer.decode("0x"+S_combined))));
    }

    public void MOVS(String R1, int offset )
    {
//        Reset_flag(); // resetting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        String temp = "";
        temp = Integer.toHexString(Short.toUnsignedInt(GPRS.gprs[index_1]));
        String[] arr = temp.split("");
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

        int[] arr1 = Memory.tranlation(SPRs.data_reg[0] + offset);
        Memory.memory1[arr1[0]].page[arr1[1]] = (byte)((int)Integer.decode("0x"+s1));
        SPRs.code_reg[2]++;

        arr1 = Memory.tranlation(SPRs.data_reg[0] + offset + 1);
        Memory.memory1[arr1[0]].page[arr1[1]] = (byte)((int)Integer.decode("0x"+s2));
        SPRs.code_reg[2]++;

        setting_flag(index_1);// setting the flag
    }

    public void SHL(String R1){
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        if((GPRS.gprs[index_1] & 0x8000) == 0x8000 ) // checking if the most significant bit is open
        {
            set_carryBit();
        }
        GPRS.gprs[index_1] = (short) (Short.toUnsignedInt(GPRS.gprs[index_1]) << 1);
        setting_flag(index_1); // setting the flag
    }

    public void SHR(String R1){
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        GPRS.gprs[index_1] = (short) ((short)(Short.toUnsignedInt(GPRS.gprs[index_1]) >> 1)& 0x7FFF); // ensures that the most sig bit is 0
        setting_flag(index_1); // setting the flag
    }

    public void RTL(String R1){
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
//        if((GPRS.gprs[index_1] & 0x8000) == 0x8000 ) // checking if the most significant bit is open
//        {
//            set_carryBit();
//        }
        if((GPRS.gprs[index_1] & 0x8000) == 0x8000)
        {
            GPRS.gprs[index_1] = (short)((GPRS.gprs[index_1] << 1)  | 0x0001);
        }
        else
        {
            GPRS.gprs[index_1] = (short)(GPRS.gprs[index_1]<<1);
        }
        setting_flag(index_1); // setting the flag
    }

    public void RTR(String R1){
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        if(((GPRS.gprs[index_1] & 0x0001) == 0x0001)  && ((GPRS.gprs[index_1]) & 0x8000) == 0x0000){ // checking if least significant bit is on  and most sig bit is off
            GPRS.gprs[index_1] = (short) ((GPRS.gprs[index_1] >>1) | 0x8000);// ensures most sig bit is on
        }
        else if(((GPRS.gprs[index_1] & 0x0001) == 0x0001)  && ((GPRS.gprs[index_1]) & 0x8000) == 0x8000 ){ // checking if least significant bit is on  and most sig bit is on
            GPRS.gprs[index_1] =  (short)((GPRS.gprs[index_1]>>1) | 0x8000); // ensures most sig bit is on
        }
        else if(((GPRS.gprs[index_1] & 0x0001) == 0x0000)  && ((GPRS.gprs[index_1]) & 0x8000) == 0x8000 ){ // checking if least significant bit is off  and most sig bit is on
            GPRS.gprs[index_1] =  (short)((GPRS.gprs[index_1]>>1) & 0x7FFF); // ensures most sig bit is zero
        }
        else{
            GPRS.gprs[index_1] =  (short)((GPRS.gprs[index_1]>>1)); // shifts right
        }
    }

    public void INC(String R1){
//        Reset_flag(); // resetting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        if((GPRS.gprs[index_1]&0xFFFF) == 0xFFFF) // checking if overflow
            set_overflowBit();
        else
        GPRS.gprs[index_1] = (short)(Short.toUnsignedInt(GPRS.gprs[index_1]) + 1);

        setting_flag(index_1); // setting the flag
    }

    public void DEC(String R1){
//        Reset_flag(); // resetting the flag
        int index_1 = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
        GPRS.gprs[index_1] = (short)(Short.toUnsignedInt(GPRS.gprs[index_1]) - 1);

        setting_flag(index_1); // setting the flag
    }

    public void NOOP()
    {
        Reset_flag(); // resetting the flag
    }

    public void PUSH(String R1){
        int index = Integer.parseInt(Integer.toString(Integer.decode("0x"+R1)));
//        System.out.println(index);
        int[] arr1;
//        short r1 = GPRS.gprs[index];
        String temp = Integer.toHexString(Short.toUnsignedInt(GPRS.gprs[index]));
        String[] arr = temp.split("");
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
        arr1 = Memory.tranlation(SPRs.stack_reg[2]);
//        Memory.memory1[arr1[0]].page[arr1[1]] = s1;
        Memory.memory1[arr1[0]].page[arr1[1]] = (byte)((int)Integer.decode("0x"+s1));
        SPRs.stack_reg[2]++;

        arr1 = Memory.tranlation(SPRs.stack_reg[2]);
        Memory.memory1[arr1[0]].page[arr1[1]] = (byte)((int)Integer.decode("0x"+s2));
        SPRs.stack_reg[2]++;
    }

    public short POP(){
        SPRs.stack_reg[2]--;
        int[] arr = Memory.tranlation(SPRs.stack_reg[2]);
        byte a = Memory.memory1[arr[0]].page[arr[1]];

        SPRs.stack_reg[2]--;
        arr = Memory.tranlation(SPRs.stack_reg[2]);
        byte b = Memory.memory1[arr[0]].page[arr[1]];

        String S_combined_1 = Integer.toHexString(Byte.toUnsignedInt(b));
        String S_combined_2 = convert(a);

        String S_combined = S_combined_1 + S_combined_2;



        short c = (short)((int)Integer.decode("0x"+S_combined));
        return(c);
    }

    public void CALL(short num){
        int[] arr1;
//        short r1 = GPRS.gprs[index];
        String temp = Integer.toHexString(Short.toUnsignedInt(SPRs.code_reg[2]));
        String[] arr = temp.split("");
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
        arr1 = Memory.tranlation(SPRs.stack_reg[2]);
//        Memory.memory1[arr1[0]].page[arr1[1]] = s1;
        Memory.memory1[arr1[0]].page[arr1[1]] = (byte)((int)Integer.decode("0x"+s1));
        SPRs.stack_reg[2]++;

        arr1 = Memory.tranlation(SPRs.stack_reg[2]);
        Memory.memory1[arr1[0]].page[arr1[1]] = (byte)((int)Integer.decode("0x"+s2));
        SPRs.stack_reg[2]++;

//        System.out.println(num);
        SPRs.code_reg[2] = (short)(SPRs.code_reg[0] + num);
    }

    public static String convert(byte x){
        String o = Integer.toHexString(Byte.toUnsignedInt(x));
        if (o.length() == 1){
            o = "0" + o;
        }
        return o;
    }

    public static String convert(int x){
        String o = Integer.toHexString((x));
        if (o.length() == 1){
            o = "0" + o;
        }
        return o;
    }



}

