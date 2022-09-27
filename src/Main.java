import javax.print.attribute.SetOfIntegerSyntax;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        Memory mem = new Memory();
        ISA operations = new ISA();
        String opcode = "";
        //setting code_reg and data_reg values
        SPRs.code_reg[0] = Memory.pc;
        SPRs.code_reg[1] = 25000;
        SPRs.data_reg[0] = 25001;
        SPRs.data_reg[1] = 30000;
        //read the file and saved it's contents in the memory
        Scanner in = new Scanner(new File("src\\p1.txt"));
        while (in.hasNext()) {
            byte a = (byte) in.nextInt();
            //coverting integer into hexa decimal
            Memory.memory[SPRs.code_reg[2]] = a;
            //hex values --> 30 01 00 01 30 02 7f ff 19 01 02 f3
            SPRs.code_reg[2]++;
        }

        // switch cases showing, in which every instruction is passed as a hex string , 0xFF ensures that the value staying with in byte limit
        opcode = "";
        Memory.pc = SPRs.code_reg[0];
        while (!(Memory.pc >= SPRs.code_reg[2])/*(Memory.pc >= Memory.cc)*/ || !(Memory.memory[Memory.pc] == (byte)Byte.parseByte("f3"))){
            opcode = Integer.toHexString(Memory.memory[Memory.pc] & 0xFF);
            switch (opcode) {
                case "16":
                    Memory.pc++;
                    operations.Move(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), Integer.toHexString(Memory.memory[Memory.pc + 1] & 0xFF));
                    Memory.pc += 2;
                    break;
                case "17":
                    Memory.pc++;
                    operations.Add(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), Integer.toHexString(Memory.memory[Memory.pc + 1] & 0xFF));
                    Memory.pc += 2;
                    break;
                case "18":
                    Memory.pc++;
                    operations.Sub(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), Integer.toHexString(Memory.memory[Memory.pc + 1] & 0xFF));
                    Memory.pc += 2;
                    break;
                case "19":
                    Memory.pc++;
                    operations.Mul(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), Integer.toHexString(Memory.memory[Memory.pc + 1] & 0xFF));
                    Memory.pc += 2;
                    break;
                case "1A":
                    Memory.pc++;
                    operations.Div(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), Integer.toHexString(Memory.memory[Memory.pc + 1] & 0xFF));
                    Memory.pc += 2;
                    break;
                case "1B":
                    Memory.pc++;
                    operations.And(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), Integer.toHexString(Memory.memory[Memory.pc + 1] & 0xFF));
                    Memory.pc += 2;
                    break;
                case "1C":
                    Memory.pc++;
                    operations.Or(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), Integer.toHexString(Memory.memory[Memory.pc + 1] & 0xFF));
                    Memory.pc += 2;
                    break;
                case "30":
                    Memory.pc++;
                    operations.Movi(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), (short) (Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 1] & 0xFF) )+ Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 2] & 0xFF))));
                    Memory.pc += 3;
                    break;
                case "31":
                    Memory.pc++;
                    operations.Addi(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), (short) (Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 1]& 0xFF)) + Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 2]& 0xFF))));
                    Memory.pc += 3;
                    break;
                case "32":
                    Memory.pc++;
                    operations.Subi(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), (short) (Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 1]& 0xFF)) + Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 2]& 0xFF))));
                    Memory.pc += 3;
                    break;
                case "33":
                    Memory.pc++;
                    operations.Muli(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), (short) (Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 1]& 0xFF)) + Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 2]& 0xFF))));
                    Memory.pc += 3;
                    break;
                case "34":
                    Memory.pc++;
                    operations.Divi(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), (short) (Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 1]& 0xFF)) + Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 2]& 0xFF))));
                    Memory.pc += 3;
                    break;
                case "35":
                    Memory.pc++;
                    operations.Andi(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), (short) (Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 1]& 0xFF)) + Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 2]& 0xFF))));
                    Memory.pc += 3;
                    break;
                case "36":
                    Memory.pc++;
                    operations.Ori(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF), (short) (Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 1]& 0xFF)) + Byte.toUnsignedInt((byte)(Memory.memory[Memory.pc + 2]& 0xFF))));
                    Memory.pc += 3;
                    break;
                case "37":
                    Memory.pc++;
                    operations.BZ((short)(Byte.toUnsignedInt(Memory.memory[Memory.pc])));
                case "38":
                    Memory.pc++;
                    operations.BNZ((short)(Byte.toUnsignedInt(Memory.memory[Memory.pc])));
                case "39":
                    Memory.pc++;
                    operations.BC((short)(Byte.toUnsignedInt(Memory.memory[Memory.pc])));
                case "3A":
                    Memory.pc++;
                    operations.BS((short)(Byte.toUnsignedInt(Memory.memory[Memory.pc])));
                case "3B":
                    Memory.pc++;
                    operations.JMP((short)(Byte.toUnsignedInt(Memory.memory[Memory.pc])));
                    Memory.pc++;
//                case "3D":
//                    Memory.pc++;
//                    operations.Act((short) (Integer.parseInt(mem.mem_hex[Memory.pc])));
//                    Memory.pc++;
                case "51":
                    Memory.pc++;
                    operations.MOVL(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF),(Memory.memory[Memory.pc] & 0xFF));
                    Memory.pc++;
                    Memory.pc++;
                case "52":
                    Memory.pc++;
                    operations.MOVS(Integer.toHexString(Memory.memory[Memory.pc] & 0xFF),(Memory.memory[Memory.pc] & 0xFF));
                    Memory.pc++;
                    Memory.pc++;
                case "71":
                    Memory.pc++;
                    operations.SHL(Integer.toHexString(Memory.memory[Memory.pc]));
                    Memory.pc += 1;
                    break;
                case "72":
                    Memory.pc++;
                    operations.SHR(Integer.toHexString(Memory.memory[Memory.pc]));
                    Memory.pc += 1;
                    break;
                case "73":
                    Memory.pc++;
                    operations.RTL(Integer.toHexString(Memory.memory[Memory.pc]));
                    Memory.pc += 1;
                    break;
                case "74":
                    Memory.pc++;
                    operations.RTR(Integer.toHexString(Memory.memory[Memory.pc]));
                    Memory.pc += 1;
                    break;
                case "75":
                    Memory.pc++;
                    operations.INC(Integer.toHexString(Memory.memory[Memory.pc]));
                    Memory.pc += 1;
                    break;
                case "76":
                    Memory.pc++;
                    operations.DEC(Integer.toHexString(Memory.memory[Memory.pc]));
                    Memory.pc += 1;
                    break;
                case "f2":
                    operations.NOOP();
                    Memory.pc++;
                    break;
                case "f3":
                    System.out.println("----------------------------------------------------------------------------------------------------");
                    System.out.println("GPRS");
                    GPRS.show_in_decimal();
                    System.out.println("----------------------------------------------------------------------------------------------------");
                    System.out.println("SPRS");
                    SPRs.display_sprs();
                    System.exit(0);
                    break;
            }
        }
    }
}