public class queue {
public Node head;
public Node tail;

public void insert(PCB pcb) {
    if ((head == null) &&( tail == null)){
        head = new Node(pcb);
        tail = head;
    }
    else{
        Node temp = new Node(pcb);
        tail.next = temp;
        tail = tail.next;
//        System.out.println("2st loop");

    }
}

public PCB dequeue(){
//    System.out.println(len());
    PCB x = null;
    if (head == tail){
    x = head.pcb;
    head = head.next;
    tail = tail.next;
    }
    else{
        x = head.pcb;
        head = head.next;
    }
    return x;
}

public int len(){
    Node temp = head;
    int count = 1;
    while (temp.next != null){
        temp = temp.next;
        count++;
    }
    return count;
}



    public void insertInOrder(PCB DATA) //In ASCENDING order
    {
        Node InsertionNode = new Node(DATA);
        if (head == null) //Insert at beginning of list when list is empty list
        {
            head = InsertionNode;
        }
        else if (head.pcb.process_pri > DATA.process_pri ) //Insert at beginning of list when head data is less than given data
        {
            InsertionNode.next = head;
            head = InsertionNode;
        }
        else
        {
            Node TrackerNode = head;
            while (TrackerNode.next != null && TrackerNode.next.pcb.process_pri < DATA.process_pri) //Checking that next node exists and next node's value is less than given value
            {
                System.out.println("1st loop");
                TrackerNode = TrackerNode.next;
            }
            //Loop breaks when either next node is empty or next node's value is greater than given value
            if (TrackerNode.next == null) //Insert at end of list (after last node) i.e. next node is empty
            {
                if (TrackerNode.pcb.process_pri <= DATA.process_pri)
                    TrackerNode.next = InsertionNode;
                else //means if TrackerNode.data.compareTo(DATA) > 0
                {
                    InsertionNode.next = TrackerNode;
                    TrackerNode.next = null;
                }
            }
            else if (TrackerNode.pcb.process_pri < DATA.process_pri  && TrackerNode.next.pcb.process_pri >= DATA.process_pri) //Insert at any desired place i.e. current node's value is greater than given value (so insert before current node and next node)
            {
                InsertionNode.next = TrackerNode.next;
                TrackerNode.next = InsertionNode;

            }
        }
    }

    public void Update_Waiting_Time_PCB(){
    Node temp_node = head;
    while(temp_node != null){
        temp_node.pcb.Update_waiting_time();
        temp_node = temp_node.next;
    }
    }


}
