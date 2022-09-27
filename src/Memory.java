public class Memory {
    public static byte[] memory = new byte[65536];

    public static short pc = 0;

    public static int cc;
    public static int cb;
    public static String[] mem_hex;

    public void Push_into_mem(byte x, int curr_address){
        memory[curr_address] = x;
    }

}
