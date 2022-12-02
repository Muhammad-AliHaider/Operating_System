import java.util.ArrayList;

public class Scheduling {
public static queue queue1 = new queue();
public static queue queue2 = new queue();

public static void add_to_queue(PCB pcb){
    if(pcb.process_pri <= 15){


        queue1.insertInOrder(pcb);
    }
    else if(pcb.process_pri > 15){

        queue2.insert(pcb);
    }
}

public static PCB priority()
{
    return queue1.dequeue();
}

public static PCB Round_robin(){
    return queue2.dequeue();
}

}
