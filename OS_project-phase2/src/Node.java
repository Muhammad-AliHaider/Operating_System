public class Node {
    public PCB pcb;
    public Node next;

    public Node(PCB pcb){
        this.pcb = pcb;
        this.next = null;
    }
    public Node(PCB pcb , Node next){
        this.pcb = pcb;
        this.next = next;
    }

}
