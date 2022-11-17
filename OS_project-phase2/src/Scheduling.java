import java.util.ArrayList;

public class Scheduling {
public static queue queue1 = new queue();
public static queue queue2 = new queue();

public void Sort_for_priority(){
    queue1.sortList();
}

public static void add_to_queue(PCB pcb){
    if(pcb.process_pri <= 15){
//        System.out.println(pcb.File_name);
//        System.out.println("xxxx");
//        System.out.println("1st loop");

        queue1.insertInOrder(pcb);
    }
    else if(pcb.process_pri > 15){
//        System.out.println(pcb.process_pri);
//        System.out.println("2st loop");
        queue2.insert(pcb);
        return;
    }
}

public static PCB priority()
{

    return queue1.dequeue();
}

public void Round_robin(){

}

}
