import javax.print.attribute.SetOfIntegerSyntax;
import java.awt.image.MemoryImageSource;
import java.io.*;
import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {



    public static void ouput(PCB Runningpcb)
    {
        System.out.println(Runningpcb.File_name + " : \n");
        System.out.println("Waiting_time = " + Runningpcb.waitingTime);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Execution_time = " + Runningpcb.executionTime);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Turn_around_time = " + (Runningpcb.executionTime + Runningpcb.waitingTime));
        Memory.print_memory_dump();
    }

    public static void main(String[] args) throws IOException {
        ISA operations = new ISA();
        String opcode = "";
        //setting code_reg and data_reg values
//        SPRs.code_reg[0] = Memory.pc;
//        SPRs.code_reg[1] = 25000;
//        SPRs.data_reg[0] = 25001;
//        SPRs.data_reg[1] = 30000;

        int clock_cycles_time_quanta = 8;


        File file1 = new File("E:\\projects\\OS_project\\OS_project-phase2\\src\\flags");
        File file2 = new File("E:\\projects\\OS_project\\OS_project-phase2\\src\\noop");
        File file3 = new File("E:\\projects\\OS_project\\OS_project-phase2\\src\\large0");
        File file4 = new File("E:\\projects\\OS_project\\OS_project-phase2\\src\\p5");
        File file5 = new File("E:\\projects\\OS_project\\OS_project-phase2\\src\\power");
        File file6 = new File("E:\\projects\\OS_project\\OS_project-phase2\\src\\sfull");

        File[] file = {file1, file2, file3, file4, file5, file6};
        int frame_number = 0;
        int Ccount = 0;
        Memory.Init_Memory();
        for (int o = 0; o < 6; o++) {
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
                }

                process_pri = array.get(0);

                processID = Integer.parseInt(Integer.toString(array.get(1)) + Integer.toString(array.get(2)));

                process_size = array.size();

                String a = Page.ZeroAppender(Integer.toHexString(array.get(3) & 0xff));
                String b = Page.ZeroAppender(Integer.toHexString(array.get(4) & 0xff));
                data_size = Integer.parseInt((a + b), 16);

                code_size = process_size - data_size - 8;

                int total_size = process_size - 8 + 50;
                int page_numbers = (int) (total_size / 128);


                count = 0;
                int i = 0;
                int k = 8;
                int offset = 0;
                SPRs.data_reg[0] = (short) Ccount;
                while (i < data_size) {

                    if (offset >= 128) {
                        offset = 0;
                        frame_number++;
                    }
                    Memory.memory1[frame_number].page[offset] = (byte) ((int) array.get(k));

                    k++;
                    i++;
                    offset++;
                    count++;
                    Ccount++;

                }
                SPRs.data_reg[1] = (short) (Ccount);

                i = 0;
                SPRs.code_reg[0] = (short) Ccount;
                while (i < code_size) {

                    if (offset >= 128) {
                        offset = 0;
                        frame_number++;
                    }
                    Memory.memory1[frame_number].page[offset] = (byte) ((int) array.get(k));
                    k++;
                    i++;
                    offset++;
                    Ccount++;
                    count++;

                }
                SPRs.code_reg[1] = (short) Ccount;
                int[] frames = new int[page_numbers + 1];
                int x = 0;

                SPRs.stack_reg[0] = (short) Ccount;
                SPRs.stack_reg[1] = (short) (Ccount + 50);
                SPRs.stack_reg[2] = SPRs.stack_reg[0];

                for (int l = frame_number - page_numbers; l <= frame_number; l++) {
                    frames[x] = l;
                    x++;
                }

                short[] gprs_to_form = new short[16];
                short[] sprs_to_form = new short[16];
                short[] SPRS_formed = SPRs.formSPRS();

                for (int index = 0; index < 16; index++) {
                    gprs_to_form[index] = GPRS.gprs[index];
                    sprs_to_form[index] = SPRS_formed[index];
                }

                pcb = new PCB(processID, process_pri, process_size, data_size, file[o].getName(), sprs_to_form, gprs_to_form, frames);

                Scheduling.add_to_queue(pcb);

                frame_number++;
                Ccount = frame_number * 128;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
//
//      Priiority Queue
        while (Scheduling.queue1.head != null) {
            PCB Runningpcb = Scheduling.fetch_PRI();
            Scheduling.RunningQueue.insert(Runningpcb);


            int[] arr = Memory.tranlation(SPRs.code_reg[2]);
            opcode = Integer.toHexString(Byte.toUnsignedInt(Memory.memory1[arr[0]].page[arr[1]]) & 0xFF);

            while ((SPRs.code_reg[2] <= SPRs.code_reg[1]) && !(opcode.equals("f3")) ) {
                 arr = Memory.tranlation(SPRs.code_reg[2]);
                opcode = Integer.toHexString(Byte.toUnsignedInt(Memory.memory1[arr[0]].page[arr[1]]) & 0xFF);
                Decoder.decoder(opcode);

                Scheduling.update_waiting_time_Ready_Queue();
                Runningpcb.Update_execution_time();

            }

            ouput(Runningpcb);
            Scheduling.RunningQueue.dequeue();

        }

//      Round_Robin
        while(Scheduling.queue2.head != null ){

            PCB Runningpcb = Scheduling.fetch_RR();
            Scheduling.RunningQueue.insert(Runningpcb);

            int[] arr = Memory.tranlation(SPRs.code_reg[2]);
            opcode = Integer.toHexString(Byte.toUnsignedInt(Memory.memory1[arr[0]].page[arr[1]]) & 0xFF);

            boolean process_ended = false;
            for(int i = 0 ; i < clock_cycles_time_quanta/2 ; i++ ) {
                arr = Memory.tranlation(SPRs.code_reg[2]);
                opcode = Integer.toHexString(Byte.toUnsignedInt(Memory.memory1[arr[0]].page[arr[1]]) & 0xFF);

                if (opcode.equals("f3") || (SPRs.code_reg[2] >= SPRs.code_reg[1]) ){
                    process_ended = true;
                }
                if (operations.is_stack_full()){
                    System.out.println("Stack Overflow error");
                    process_ended = true;
                }


                Decoder.decoder(opcode);

                Scheduling.update_waiting_time_Ready_Queue();
                Runningpcb.Update_execution_time();


            }
//            System.out.println("Waiting_time = " + Runningpcb.waitingTime);
//            System.out.println("Execution_time = " + Runningpcb.executionTime);
//            Memory.print_memory_dump();

            if(process_ended == true){
                process_ended = false;
                ouput(Runningpcb);
                Scheduling.RunningQueue.dequeue();
            }
            else{

                Scheduling.Store(Runningpcb);
                Scheduling.RunningQueue.dequeue();
            }


        }
    }
}