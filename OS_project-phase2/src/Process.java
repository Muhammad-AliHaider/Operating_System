public class Process {
    public int datasize;
    public int codesize;
    public int process_size;
    public int priority;
    PCB pcb;
    PageTable pt;

    public Process(Memory mem, String file_name)
    {
        //file read kra kr pora process load hojayega. Priority, id, data/code size sab ajayega.





        int pid =0;
        Short[] sprs = new Short[0], gprs = new Short[0];
        int pages =0;
        // yahan se process load hone k bad ka kaam hai
        pcb = new PCB(pid, priority, process_size, datasize, codesize, file_name,sprs, gprs,pages);
    }
}
