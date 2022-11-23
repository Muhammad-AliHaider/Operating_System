import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class test {





    public static void main(String[] args) throws IOException {
        File file = new File("E:\\projects\\OS_project\\OS_project-phase2\\src\\sfull");

        FileInputStream input= new FileInputStream(file);
        PCB pcb= null;
        int character;
        ArrayList<Integer> array = new ArrayList<Integer>();
        // read() function return int between 0 and 255.
        int process_pri = 0;
        int processID = 0;
        int process_size = 0;
        int data_size = 0;
        int code_size = 0;
        int count = 0;
        while((character = input.read()) != -1) {
//            System.out.print(count + " ) ");
            array.add(character);
            System.out.println(Integer.toHexString(character));
            count++;
        }
//        System.out.println(Integer.parseInt(((Integer.toHexString(array.get(3) & 0xff )) +(Integer.toHexString(array.get(4) & 0xff))),16));
        process_pri= array.get(0);
        processID = Integer.parseInt(Integer.toString(array.get(1)) + Integer.toString(array.get(2))) ;
        process_size = array.size() - 1;
        String a = Page.ZeroAppender(Integer.toHexString(array.get(3)& 0xff));
        String b = Page.ZeroAppender(Integer.toHexString(array.get(4)& 0xff)) ;

        data_size = Integer.parseInt((a +b),16) ;
        code_size = process_size -  data_size - 8;
        System.out.println(code_size);
        System.out.println("data");
        count = 0;
        int i = 0;
        int k = 8;
        while(i <data_size)
        {
            System.out.print(count + " ) ");
            System.out.print(Integer.toHexString(array.get(k)));
            System.out.println(" ");
            k++;
            i++;
            count++;
        }
        System.out.println();
        System.out.println("code");
        i = 0;
        while(i <= code_size)
        {
            System.out.print(Integer.toHexString(array.get(k)));
            System.out.print(" ");
            k++;
            i++;
        }
    }
}
