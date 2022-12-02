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

                SPRs.stack_reg[0] = (short) Ccount;
                SPRs.stack_reg[1] = (short) (Ccount+50);
                SPRs.stack_reg[2] = SPRs.stack_reg[0];

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
//        Runningpcb = Scheduling.priority();
//        Runningpcb = Scheduling.priority();
        System.out.println(Runningpcb.File_name);

        SPRs.toSPRS(Runningpcb.SPRs);
        for(int index = 0 ; index < 16 ; index++){
            GPRS.gprs[index] = Runningpcb.GPRS[index];
        }
        SPRs.code_reg[0] = Runningpcb.SPRs[1];
        SPRs.code_reg[1] = Runningpcb.SPRs[2];
        SPRs.code_reg[2] = SPRs.code_reg[0]; // <---pc
        int[] arr = Memory.tranlation(SPRs.code_reg[2]);
        while ((SPRs.code_reg[2] <= SPRs.code_reg[1]) ){
            arr = Memory.tranlation(SPRs.code_reg[2]);
            opcode = Integer.toHexString(Byte.toUnsignedInt(Memory.memory1[arr[0]].page[arr[1]]) & 0xFF);

            switch (opcode) {
                case "16":
                    SPRs.code_reg[2]++;
                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    String R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                    String R2 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    operations.Move(R1, R2);
                    SPRs.code_reg[2] += 2;
                    break;
                case "17":
                    SPRs.code_reg[2]++;

                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                    R2 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    operations.Add(R1,R2);
                    SPRs.code_reg[2] += 2;
                    break;
                case "18":
                    SPRs.code_reg[2]++;
                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                    R2 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    operations.Sub(R1,R2);
                    SPRs.code_reg[2] += 2;
                    break;
                case "19":
                    SPRs.code_reg[2]++;

                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                    R2 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    operations.Mul(R1,R2);
                    SPRs.code_reg[2] += 2;
                    break;
                case "1a":
                    SPRs.code_reg[2]++;

                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                    R2 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    operations.Div(R1,R2);
                    SPRs.code_reg[2] += 2;
                    break;
                case "1b":
                    SPRs.code_reg[2]++;

                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                    R2 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    operations.And(R1,R2);
                    SPRs.code_reg[2] += 2;
                    break;
                case "1c":
                    SPRs.code_reg[2]++;

                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                    R2 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    operations.Or(R1,R2);
                    SPRs.code_reg[2] += 2;
                    break;
                case "30":
                    SPRs.code_reg[2]++;

                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                    int S1 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) )) ;

                    arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                    int S2 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) ));

                    short S_combined = (short) (S1+S2);

                    operations.Movi(R1,S_combined );
                    SPRs.code_reg[2] += 3;
                    break;
                case "31":
                    SPRs.code_reg[2]++;

                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                    S1 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) )) ;

                    arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                    S2 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) ));

                    S_combined = (short) (S1+S2);

                    operations.Addi(R1,S_combined);
                    SPRs.code_reg[2] += 3;
                    break;
                case "32":
                    SPRs.code_reg[2]++;

                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                    S1 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) )) ;

                    arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                    S2 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) ));

                    S_combined = (short) (S1+S2);

                    operations.Subi(R1, S_combined);
                    SPRs.code_reg[2] += 3;
                    break;
                case "33":
                    SPRs.code_reg[2]++;

                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                    S1 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) )) ;

                    arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                    S2 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) ));

                    S_combined = (short) (S1+S2);

                    operations.Muli(R1, S_combined);
                    SPRs.code_reg[2] += 3;
                    break;
                case "34":
                    SPRs.code_reg[2]++;

                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                    S1 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) )) ;

                    arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                    S2 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) ));

                    S_combined = (short) (S1+S2);

                    operations.Divi(R1, S_combined);
                    SPRs.code_reg[2] += 3;
                    break;
                case "35":
                    SPRs.code_reg[2]++;

                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                    S1 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) )) ;

                    arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                    S2 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) ));

                    S_combined = (short) (S1+S2);

                    operations.Andi(R1,S_combined);
                    SPRs.code_reg[2] += 3;
                    break;
                case "36":
                    SPRs.code_reg[2]++;

                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                    S1 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) )) ;

                    arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                    S2 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) ));

                    S_combined = (short) (S1+S2);

                    operations.Ori(R1, S_combined);
                    SPRs.code_reg[2] += 3;
                    break;
                case "37":
                    SPRs.code_reg[2]++;
//                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    SPRs.code_reg[2]++;
                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    S1 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) )) ;

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                    S2 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) ));

                    S_combined = (short) (S1+S2);
                    operations.BZ(S_combined);
//                    SPRs.code_reg[2] += 2;

                    break;
                case "38":
                    SPRs.code_reg[2]++;
                    SPRs.code_reg[2]++;
                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    S1 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) )) ;

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                    S2 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) ));

                    S_combined = (short) (S1+S2);

                    operations.BNZ(S_combined);
                    break;
                case "39":
                    SPRs.code_reg[2]++;
                    SPRs.code_reg[2]++;
                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    S1 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) )) ;

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                    S2 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) ));

                    S_combined = (short) (S1+S2);

                    operations.BC(S_combined);
                    break;
                case "3a":
                    SPRs.code_reg[2]++;
                    SPRs.code_reg[2]++;
                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    S1 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) )) ;

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                    S2 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) ));

                    S_combined = (short) (S1+S2);
                    operations.BS(S_combined);
                    break;
                case "3b":
                    SPRs.code_reg[2]++;
                    SPRs.code_reg[2]++; // since Register is not used
                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    S1 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) )) ;

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                    S2 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) ));

                    S_combined = (short) (S1+S2);
                    operations.JMP(S_combined);
//                    SPRs.code_reg[2]++;
                    break;
                case"3c":
                    SPRs.code_reg[2]++;
                    SPRs.code_reg[2]++; // since Register is not used
                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    S1 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) )) ;

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                    S2 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) ));

                    S_combined = (short) (S1+S2);

                    operations.CALL(S_combined);
                    break;
//                case "3d":
//                    SPRs.code_reg[2]++;
//                    operations.Act((short) (Integer.parseInt(mem.mem_hex[SPRs.code_reg[2]])));
//                    SPRs.code_reg[2]++;
                case "51":
                    SPRs.code_reg[2]++;

                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                    S1 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) )) ;

                    arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                    S2 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) ));

                    int S_combined_1 =  (S1+S2);

                    operations.MOVL(R1,S_combined_1);
                    SPRs.code_reg[2]++;
                    SPRs.code_reg[2]++;
                    SPRs.code_reg[2]++;
                    break;
                case "52":
                    SPRs.code_reg[2]++;

                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                    arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                    S1 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) )) ;

                    arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                    S2 =  (Byte.toUnsignedInt((byte)(Memory.memory1[arr[0]].page[arr[1]] & 0xFF) ));

                    String S_combined_1_1 = Integer.toString(S1);
                    String S_combined_1_2 = ISA.convert(S2);

                    S_combined_1 = Integer.parseInt(Integer.toString(Integer.decode(S_combined_1_1+S_combined_1_2))) ;

                    operations.MOVS(R1,S_combined_1);
                    SPRs.code_reg[2]++;
                    SPRs.code_reg[2]++;
                    SPRs.code_reg[2]++;
                    break;
                case "71":
                    SPRs.code_reg[2]++;
                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    operations.SHL(Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]]));
                    SPRs.code_reg[2] += 1;
                    break;
                case "72":
                    SPRs.code_reg[2]++;
                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    operations.SHR(Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]]));
                    SPRs.code_reg[2] += 1;
                    break;
                case "73":
                    SPRs.code_reg[2]++;
                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    operations.RTL(Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]]));
                    SPRs.code_reg[2] += 1;
                    break;
                case "74":
                    SPRs.code_reg[2]++;
                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    operations.RTR(Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]]));
                    SPRs.code_reg[2] += 1;
                    break;
                case "75":
                    SPRs.code_reg[2]++;
                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    operations.INC(Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]]));
                    SPRs.code_reg[2] += 1;
                    break;
                case "76":
                    SPRs.code_reg[2]++;
                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    operations.DEC(Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]]));
                    SPRs.code_reg[2] += 1;
                    break;
                case "77":
                    SPRs.code_reg[2]++;
                    arr = Memory.tranlation(SPRs.code_reg[2]);
                    operations.PUSH(Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]]));
                    SPRs.code_reg[2] += 1;
                    break;

                case "78":
                    SPRs.code_reg[2]++;
                    arr = Memory.tranlation(SPRs.code_reg[2]);
//                    Memory.memory1[arr[0]].page[arr[1]] = operations.POP();
                    int a = Integer.parseInt(Integer.toString(Integer.decode(Byte.toString(Memory.memory1[arr[0]].page[arr[1]]))));
                    GPRS.gprs[a] = (short)(Integer.parseInt(Integer.toString(Integer.decode("0x"+operations.POP()))));
                    SPRs.code_reg[2] += 1;
                    break;

                case "f1":
                    SPRs.code_reg[2]++;
                    SPRs.code_reg[2] = operations.POP();
//                    System.out.println("hello");
                    break;


                case "f2":
                    operations.NOOP();
                    SPRs.code_reg[2]++;
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



                default:
                    System.out.println("opcode Invalid");
                    System.exit(0);
            }
        }
    }
}