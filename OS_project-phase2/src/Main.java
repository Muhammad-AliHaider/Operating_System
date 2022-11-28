import javax.print.attribute.SetOfIntegerSyntax;
import java.awt.image.MemoryImageSource;
import java.io.*;
import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
        ISA operations = new ISA();
        String opcode = "";
        //setting code_reg and data_reg values
//        SPRs.code_reg[0] = Memory.pc;
//        SPRs.code_reg[1] = 25000;
//        SPRs.data_reg[0] = 25001;
//        SPRs.data_reg[1] = 30000;



        File file1 = new File("E:\\projects\\OS_project\\OS_project-phase2\\src\\flags");
        File file2 = new File("E:\\projects\\OS_project\\OS_project-phase2\\src\\noop");
        File file3 = new File("E:\\projects\\OS_project\\OS_project-phase2\\src\\large0");
        File file4 = new File("E:\\projects\\OS_project\\OS_project-phase2\\src\\p5");
        File file5 = new File("E:\\projects\\OS_project\\OS_project-phase2\\src\\power");
        File file6 = new File("E:\\projects\\OS_project\\OS_project-phase2\\src\\sfull");

        File[] file = {file1,file2,file3,file4,file5,file6};
        int frame_number= 0;
        int Ccount = 0;
        Memory.Init_Memory();
//        Memory.Display();
        for(int o = 0 ;o < 6 ; o++) {
//            System.out.println("1st loop");
//            Memory.Display();
//            System.out.println("-------------------------------------------------------------------------------------------------------------------------");
            try {
                FileInputStream input = new FileInputStream(file[o]);
                PCB pcb = null;
                int character;
                ArrayList<Integer> array = new ArrayList<Integer>();
                // read() function return int between 0 and 255.
                int process_pri = 0;
                int processID = 0;
                int process_size = 0;
                int data_size = 0;
                int code_size = 0;
                int count = 0;
                // read() function return int between 0 and 255.

                while ((character = input.read()) != -1) {

                    array.add(character);
//                    System.out.println(character);
                }
//                System.out.println(file[o].getName());
                process_pri = array.get(0);

                processID = Integer.parseInt(Integer.toString(array.get(1)) + Integer.toString(array.get(2)));

                process_size = array.size() ;

                String a = Page.ZeroAppender(Integer.toHexString(array.get(3)& 0xff));
                String b = Page.ZeroAppender(Integer.toHexString(array.get(4)& 0xff)) ;
                data_size = Integer.parseInt((a +b),16) ;

                code_size = process_size - data_size - 8;
//                System.out.println(code_size);
//               ----------------------------------------------------------------------------------------------------------------------
                int total_size = process_size - 8 + 50;
                int page_numbers = (int)(total_size/128);

                System.out.println(file[o].getName() + " " + page_numbers + " " + process_size);

//              -----------------------------------------------------------------------------------------------------------------------
//                System.out.println("data");
                count = 0;
                int i = 0;
                int k = 8;
                int offset = 0;
                SPRs.data_reg[0] = (short)Ccount;
                while (i < data_size) {

//                    System.out.print(Integer.toHexString(array.get(k)));
//                    System.out.println(" ");

                    if(offset >= 128 ){
                        offset  = 0;
                        frame_number++;
                    }
                    Memory.memory1[frame_number].page[offset] = (byte)((int)array.get(k));
//                    System.out.println(Byte.toUnsignedInt(Memory.memory1[frame_number].page[offset]));
                    k++;
                    i++;
                    offset++;
                    count++;
                    Ccount++;
//                    System.out.println("ppppp");
                }
                SPRs.data_reg[1] = (short) (Ccount);
                System.out.println();
//                System.out.println("code");
//                Ccount++;
                i = 0;
                SPRs.code_reg[0] = (short) Ccount;
                while (i < code_size) {
//                    System.out.print(Integer.toHexString(array.get(k)));
//                    System.out.print(" ");
//                    Memory.memory1[frame_number].page[offset] = (byte)((int)array.get(k));

                    if(offset >= 128 ){
                        offset  = 0;
                        frame_number++;
                    }
                    Memory.memory1[frame_number].page[offset] = (byte)((int)array.get(k));
//                    System.out.println(Byte.toUnsignedInt(Memory.memory1[frame_number].page[offset]));
                    k++;
                    i++;
                    offset++;
                    Ccount++;
                    count++;

                }
                SPRs.code_reg[1] =(short) Ccount;
                int[] frames = new int[page_numbers + 1];
                int x = 0;
                for(int l = frame_number-page_numbers ; l <= frame_number;l++){
                    frames[x] = l;
                    x++;
                }
                short[] gprs_to_form = new short[16];
                short[] sprs_to_form = new short[16];
                short[] SPRS_formed = SPRs.formSPRS();

                for (int index = 0 ; index < 16; index++){
                    gprs_to_form[index] = GPRS.gprs[index];
                    sprs_to_form[index] = SPRS_formed[index];
                }

                pcb = new PCB(processID, process_pri, process_size, data_size, file[o].getName(), sprs_to_form, gprs_to_form, frames);
//                System.out.println(pcb.File_name);
//                System.out.println(pcb.SPRs[7]);
                Scheduling.add_to_queue(pcb);
//                System.out.println("lkl");
//                System.out.println("1st loop");
//                System.out.println(frame_number);

                frame_number++;
                Ccount = frame_number * 128;
//                System.out.println(Ccount);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            Ccount++;

        }

        PCB Runningpcb = Scheduling.priority();
        System.out.println(Runningpcb.File_name);
        System.out.println(Runningpcb.SPRs[1]);
//        Runningpcb = Scheduling.priority();
//        System.out.println(Runningpcb.File_name);

//        System.out.println(Runningpcb.process_pri);

        Memory.Display();

        for(int i = (int)Runningpcb.SPRs[1] ; i < (int) Runningpcb.SPRs[2] ; i++){
            int[] arr = Memory.tranlation(i);
//            System.out.println(Runningpcb.SPRs[1]);
//            System.out.println(arr[0]);
//            System.out.println(arr[1]);
            System.out.println(Integer.toHexString(Byte.toUnsignedInt(Memory.memory1[arr[0]].page[arr[1]])));
        }

//        Memory.Display();




//        for(int i = 0  ; i <3 ; i++){
//            PCB Runningpcb = Scheduling.priority();
//            System.out.println(Runningpcb.File_name);
//        }



        // switch cases showing, in which every instruction is passed as a hex string , 0xFF ensures that the value staying with in byte limit
        opcode = "";
        Memory.pc = SPRs.code_reg[0];
        while (!(Memory.pc >= SPRs.code_reg[2]) || !(Memory.memory[Memory.pc] == (byte)Byte.parseByte("f3"))){

            opcode = Integer.toHexString(Byte.toUnsignedInt(Memory.memory[Memory.pc]) & 0xFF);
            switch (opcode) {
                case "16":
                    Memory.pc++;
                    operations.Move(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), Integer.toHexString(Memory.memory[Memory.pc + 1] & 0xFF));
                    Memory.pc += 2;
                    break;
                case "17":
                    Memory.pc++;
                    operations.Add(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), Integer.toHexString(Memory.memory[Memory.pc + 1] & 0xFF));
                    Memory.pc += 2;
                    break;
                case "18":
                    Memory.pc++;
                    operations.Sub(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), Integer.toHexString(Memory.memory[Memory.pc + 1] & 0xFF));
                    Memory.pc += 2;
                    break;
                case "19":
                    Memory.pc++;
                    operations.Mul(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), Integer.toHexString(Memory.memory[Memory.pc + 1] & 0xFF));
                    Memory.pc += 2;
                    break;
                case "1A":
                    Memory.pc++;
                    operations.Div(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), Integer.toHexString(Memory.memory[Memory.pc + 1] & 0xFF));
                    Memory.pc += 2;
                    break;
                case "1B":
                    Memory.pc++;
                    operations.And(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), Integer.toHexString(Memory.memory[Memory.pc + 1] & 0xFF));
                    Memory.pc += 2;
                    break;
                case "1C":
                    Memory.pc++;
                    operations.Or(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), Integer.toHexString(Memory.memory[Memory.pc + 1] & 0xFF));
                    Memory.pc += 2;
                    break;
                case "30":
                    Memory.pc++;
                    operations.Movi(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), (short) (Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 1] & 0xFF) )+ Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 2] & 0xFF))));
                    Memory.pc += 3;
                    break;
                case "31":
                    Memory.pc++;
                    operations.Addi(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), (short) (Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 1]& 0xFF)) + Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 2]& 0xFF))));
                    Memory.pc += 3;
                    break;
                case "32":
                    Memory.pc++;
                    operations.Subi(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), (short) (Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 1]& 0xFF)) + Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 2]& 0xFF))));
                    Memory.pc += 3;
                    break;
                case "33":
                    Memory.pc++;
                    operations.Muli(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), (short) (Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 1]& 0xFF)) + Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 2]& 0xFF))));
                    Memory.pc += 3;
                    break;
                case "34":
                    Memory.pc++;
                    operations.Divi(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), (short) (Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 1]& 0xFF)) + Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 2]& 0xFF))));
                    Memory.pc += 3;
                    break;
                case "35":
                    Memory.pc++;
                    operations.Andi(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), (short) (Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 1]& 0xFF)) + Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 2]& 0xFF))));
                    Memory.pc += 3;
                    break;
                case "36":
                    Memory.pc++;
                    operations.Ori(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), (short) (Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 1]& 0xFF)) + Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 2]& 0xFF))));
                    Memory.pc += 3;
                    break;
                case "37":
                    Memory.pc++;
                    operations.BZ((short)(Byte.toUnsignedInt(Memory.memory[Memory.pc])));
                case "38":
                    Memory.pc++;
                    operations.BNZ((short)(Byte.toUnsignedInt(Memory.memory[Memory.pc])));
                case "39":
                    Memory.pc++;
                    operations.BC((short)(Byte.toUnsignedInt(Memory.memory[Memory.pc])));
                case "3A":
                    Memory.pc++;
                    operations.BS((short)(Byte.toUnsignedInt(Memory.memory[Memory.pc])));
                case "3B":
                    Memory.pc++;
                    operations.JMP((short)(Byte.toUnsignedInt(Memory.memory[Memory.pc])));
                    Memory.pc++;
//                case "3D":
//                    Memory.pc++;
//                    operations.Act((short) (Integer.parseInt(mem.mem_hex[Memory.pc])));
//                    Memory.pc++;
                case "51":
                    Memory.pc++;
                    operations.MOVL(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF),(Memory.memory[Memory.pc] & 0xFF));
                    Memory.pc++;
                    Memory.pc++;
                case "52":
                    Memory.pc++;
                    operations.MOVS(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF),(Memory.memory[Memory.pc] & 0xFF));
                    Memory.pc++;
                    Memory.pc++;
                case "71":
                    Memory.pc++;
                    operations.SHL(Integer.toHexString(Memory.memory[Memory.pc]));
                    Memory.pc += 1;
                    break;
                case "72":
                    Memory.pc++;
                    operations.SHR(Integer.toHexString(Memory.memory[Memory.pc]));
                    Memory.pc += 1;
                    break;
                case "73":
                    Memory.pc++;
                    operations.RTL(Integer.toHexString(Memory.memory[Memory.pc]));
                    Memory.pc += 1;
                    break;
                case "74":
                    Memory.pc++;
                    operations.RTR(Integer.toHexString(Memory.memory[Memory.pc]));
                    Memory.pc += 1;
                    break;
                case "75":
                    Memory.pc++;
                    operations.INC(Integer.toHexString(Memory.memory[Memory.pc]));
                    Memory.pc += 1;
                    break;
                case "76":
                    Memory.pc++;
                    operations.DEC(Integer.toHexString(Memory.memory[Memory.pc]));
                    Memory.pc += 1;
                    break;
                case "f2":
                    operations.NOOP();
                    Memory.pc++;
                    break;
                case "f3":
                    System.out.println("----------------------------------------------------------------------------------------------------");
                    System.out.println("GPRS");
                    GPRS.show_in_decimal();
                    System.out.println("----------------------------------------------------------------------------------------------------");
                    System.out.println("SPRS");
                    SPRs.display_sprs();
                    System.exit(0);
                    break;
            }
        }
    }
}