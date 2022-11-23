public class Page {
    public static byte[] page = new byte[128];

    public Page(){
        for(int i = 0 ; i < 128 ; i++){
            page[i] = 0;
        }
    }

    public static String ZeroAppender(String x){
        if(x.length() == 1){
            x = "0"+x;
        }
        return x;
    }

}
