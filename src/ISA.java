public class ISA {

    public void Reset_carryBit(){
        GPRS.set_flag(4);
    }
    public void set_carryBit(){
        GPRS.set_flag(0);
    }
    public void set_ZeroBit(){
        GPRS.set_flag(1);
    }
    public void set_SignBit(){
        GPRS.set_flag(2);
    }
    public void set_overflowBit(){
        GPRS.set_flag(3);
    }

    public void setting_flag(int index){
        if(SPRs.sprs[index] < 0){
            set_SignBit();
        }
        else if(SPRs.sprs[index] == 0){
            set_ZeroBit();
        }
    }

    public void Move(String R1,String R2){
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        int index_2 = Integer.parseInt(R2);

        SPRs.sprs[index_1] = SPRs.sprs[index_2];
        setting_flag(index_2);
    }

    public void Add(String R1,String R2){
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        int index_2 = Integer.parseInt(R2);

        if(SPRs.sprs[index_1] + SPRs.sprs[index_2] > 65536 ){
            set_overflowBit();
        }
        else{
            SPRs.sprs[index_1] =(byte) ( SPRs.sprs[index_1] + SPRs.sprs[index_2]);
            setting_flag(index_1);
        }
    }

    public void Sub(String R1,String R2){
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        int index_2 = Integer.parseInt(R2);
        SPRs.sprs[index_1] =(byte) ( SPRs.sprs[index_1] - SPRs.sprs[index_2]);
        setting_flag(index_1);
    }

    public void Mul(String R1,String R2){
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        int index_2 = Integer.parseInt(R2);

        if(SPRs.sprs[index_1] * SPRs.sprs[index_2] > 65536 ){
            set_overflowBit();
        }
        else{
            SPRs.sprs[index_1] =(byte) ( SPRs.sprs[index_1] * SPRs.sprs[index_2]);
            setting_flag(index_1);
        }
    }

    public void Div(String R1,String R2){
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        int index_2 = Integer.parseInt(R2);
        SPRs.sprs[index_1] =(byte) ( SPRs.sprs[index_1] /SPRs.sprs[index_2]);
        setting_flag(index_1);

    }

    public void And(String R1,String R2)
    {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        int index_2 = Integer.parseInt(R2);
        SPRs.sprs[index_1] =(byte) ( SPRs.sprs[index_1] & SPRs.sprs[index_2]);
        setting_flag(index_1);
    }

    public void Or(String R1,String R2)
    {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        int index_2 = Integer.parseInt(R2);
        SPRs.sprs[index_1] =(byte) ( SPRs.sprs[index_1] | SPRs.sprs[index_2]);
        setting_flag(index_1);
    }

    public void Addi(String R1,byte num)
    {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        if(SPRs.sprs[index_1] + num > 65536 ){
            set_overflowBit();
        }
        else{
            SPRs.sprs[index_1] =(byte) ( SPRs.sprs[index_1] + num);
            setting_flag(index_1);
        }
    }

    public void Subi(String R1,byte num)
    {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);

        SPRs.sprs[index_1] =(byte) ( SPRs.sprs[index_1] - num);
        setting_flag(index_1);

    }

    public void Muli(String R1,byte num)
    {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);
        if(SPRs.sprs[index_1] * num > 65536 ){
            set_overflowBit();
        }
        else{
            SPRs.sprs[index_1] =(byte) ( SPRs.sprs[index_1] * num);
            setting_flag(index_1);
        }
    }

    public void Divi(String R1,byte num)
    {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);

        SPRs.sprs[index_1] =(byte) ( SPRs.sprs[index_1] / num);
        setting_flag(index_1);

    }

    public void Andi(String R1,byte num)
    {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);

        SPRs.sprs[index_1] =(byte) ( SPRs.sprs[index_1] & num);
        setting_flag(index_1);

    }

    public void Ori(String R1,byte num)
    {
        Reset_carryBit(); // if negative and carry then?????
        int index_1 = Integer.parseInt(R1);

        SPRs.sprs[index_1] =(byte) ( SPRs.sprs[index_1] * num);
        setting_flag(index_1);

    }


    public void BZ (byte num){
        if(GPRS.get_flag() == 1){
            Memory.pc = Memory.jc + num;
        }
    }

    public void BNZ (byte num){
        if(GPRS.get_flag() != 1){
            Memory.pc = Memory.jc + num;
        }
    }

    public void BC (byte num){
        if(GPRS.get_flag() == 0){
            Memory.pc = Memory.jc + num;
        }
    }

    public void BS (byte num){
        if(GPRS.get_flag() == 0){
            Memory.pc = Memory.jc + num;
        }
    }

    public void JMP(byte num){
        Memory.pc = Memory.jc + num;
    }



}
