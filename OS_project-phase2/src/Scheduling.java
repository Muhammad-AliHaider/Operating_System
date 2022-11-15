import java.util.ArrayList;

public class Scheduling {
public static queue queue1;
public static queue queue2;

public void Sort_for_priority(){
    queue1.sort();
}

public static void add_to_queue(PCB pcb){
    if(pcb.process_pri <= 15){
        queue1.insert(pcb);
    }
    else if(pcb.process_pri > 15){
        queue2.insert(pcb);
    }
}

public PCB priority(PCB pcb)
{
     return queue1.dequeue();
}

public void Round_robin(){

}

}
