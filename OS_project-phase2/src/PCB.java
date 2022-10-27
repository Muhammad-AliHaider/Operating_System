public class PCB {
    public int processID;
    public int process_pri;
    public int process_size;
    public String File_name;
    public Short[] SPRs;
    public Short[] GPRS;

    public PCB(int P_id,int P_pri,int P_size, String Name, Short[] SPRs, Short[] GPRs){
        this.processID = P_id;
        this.process_pri = P_pri;
        this.process_size = P_size;
        this.File_name = Name;
        this.SPRs = SPRs;
        this.GPRS = GPRs;
    }


}
