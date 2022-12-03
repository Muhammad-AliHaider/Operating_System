import java.util.ArrayList;

public class Scheduling {
public static queue queue1 = new queue();
public static queue queue2 = new queue();

public static queue RunningQueue = new queue();

public static void add_to_queue(PCB pcb){
    if(pcb.process_pri <= 15){


        queue1.insertInOrder(pcb);
    }
    else if(pcb.process_pri > 15){

        queue2.insert(pcb);
    }
}

public static void update_waiting_time_Ready_Queue(){
    queue1.Update_Waiting_Time_PCB();
    queue2.Update_Waiting_Time_PCB();
}

public static PCB fetch_RR(){
    PCB Runningpcb = Scheduling.Round_robin();
//    System.out.println(Runningpcb.File_name);

    SPRs.toSPRS(Runningpcb.SPRs);
    for (int index = 0; index < 16; index++) {
        GPRS.gprs[index] = Runningpcb.GPRS[index];
    }
    SPRs.code_reg[0] = Runningpcb.SPRs[1];
    SPRs.code_reg[1] = Runningpcb.SPRs[2];
    if(SPRs.code_reg[2] <= SPRs.code_reg[0])
        SPRs.code_reg[2] = SPRs.code_reg[0]; // <---pc

    else{
        SPRs.code_reg[2] = Runningpcb.SPRs[3];
    }

    return Runningpcb;
}

    public static PCB fetch_PRI(){
        PCB Runningpcb = Scheduling.priority();
//        System.out.println(Runningpcb.File_name);

        SPRs.toSPRS(Runningpcb.SPRs);
        for (int index = 0; index < 16; index++) {
            GPRS.gprs[index] = Runningpcb.GPRS[index];
        }
        SPRs.code_reg[0] = Runningpcb.SPRs[1];
        SPRs.code_reg[1] = Runningpcb.SPRs[2];
        if(SPRs.code_reg[2] <= SPRs.code_reg[0])
            SPRs.code_reg[2] = SPRs.code_reg[0]; // <---pc

        else{
            SPRs.code_reg[2] = Runningpcb.SPRs[3];
        }

        return Runningpcb;
    }

public static void Store(PCB Runningpcb){
    short[] sprs_to_formed = new short[16];
    short[] gprs_to_formed = new short[16];
    short[] SPRS_formed = SPRs.formSPRS();

    for (int index = 0; index < 16; index++) {
        Runningpcb.GPRS[index] = GPRS.gprs[index];
        Runningpcb.SPRs[index] = SPRS_formed[index];
    }

//    PCB Storing_pcb = new PCB(Runningpcb.processID, Runningpcb.process_pri, Runningpcb.process_size, Runningpcb.data_size, Runningpcb.File_name, sprs_to_formed, gprs_to_formed, Runningpcb.page_table);

    Scheduling.add_to_queue(Runningpcb);
}

public static PCB priority()
{
    return queue1.dequeue();
}

public static PCB Round_robin(){
    return queue2.dequeue();
}

}
