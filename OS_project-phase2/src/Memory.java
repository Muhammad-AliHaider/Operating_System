public class Memory {
    public static byte[] memory = new byte[65536]; // 64KB of Memory
    public static Page[] memory1 = new Page[512];
    public static short pc = 0;

    public Memory(){
        for(int i = 0 ; i < 512 ; i++){
            memory1[i] = new Page();
        }
    }

    public static void store(int offset, int page_number){

    }

    public static void load(int offset,int page_number){

    }

     public static int[] tranlation(int x){
        int frame_number = (int)(x/128);
        int offset = (int)(x%128);
        int[] set = {frame_number, offset};
        return set;
     }

}
