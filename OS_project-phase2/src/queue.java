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
    }
}

public PCB dequeue(){
    head = head.next;
    return head.pcb;
}

public void sort()
{
    Node current = this.head;
    Node index = null;

    if(this.head == null) {
        return;
    }
    else {
        while (current != null) {
            //Node index will point to node next to current
            index = current.next;
        }

        while (index != null) {
                    //If current node 's data is greater than index' s node data, swap the data between them
            if (current.pcb.process_pri > index.pcb.process_pri) {
                Node temp = current;
                current = index;
                index = temp;
                index = index.next;
                current = current.next;
            }
        }
    }
}


}
