public class Memory {
    public static byte[] memory = new byte[65536];

    public static int pc;
    public static int jc;

    public void Push_into_mem(byte x, int curr_address){
        memory[curr_address] = x;
    }

}
