public class PCB {
    public int processID;
    public int process_pri;
    public int process_size;

    public int data_size;
    public String File_name;
    public short[] SPRs;
    public short[] GPRS;

    public int[] page_table;

    int executionTime = 0;
    int waitingTime = 0;


    public PCB(int P_id,int P_pri,int P_size, int data_size, String Name, short[] SPRs, short[] GPRs){
        this.processID = P_id;
        this.process_pri = P_pri;
        this.process_size = P_size;
        this.data_size = data_size;
        this.File_name = Name;
        this.SPRs = SPRs;
        this.GPRS = GPRs;
    }
    public PCB(int P_id,int P_pri,int P_size, int data_size, String Name, short[] SPRs, short[] GPRs, int[] frame){
        this.processID = P_id;
        this.process_pri = P_pri;
        this.process_size = P_size;
        this.data_size = data_size;
        this.File_name = Name;
        this.SPRs = SPRs;
        this.GPRS = GPRs;
        this.page_table = frame;

    }

    public void Update_waiting_time(){
        this.waitingTime+=2;
    }

    public void Update_execution_time(){
        this.executionTime+=2;
    }


}
