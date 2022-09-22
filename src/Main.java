import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        Memory mem = new Memory();
        ISA operations = new ISA();
        //read the file and saved it's contents in the memory
        //Scanner in = new Scanner(new File("p1.txt"));
//        while (in.hasNext()) {
//            byte a = (byte) in.nextInt();
//            //coverting integer into hexa decimal
//            Memory.memory[Memory.pc + 3] = Integer.toHexString(a & 0xFF);
//            //hex values --> 30 01 00 01 30 02 7f ff 19 01 02 f3
//            mem.cc++;
//        }
        String opcode = "";
        //setting program counter to the code base
        mem.pc = mem.cb;
        //read the file and saved it's contents in the memory
        Scanner in = new Scanner(new File("p1.txt"));
        while (in.hasNext()) {
            byte a = (byte) in.nextInt();
            //coverting integer into hexa decimal
            mem.mem_hex[mem.cc] = Integer.toHexString(a & 0xFF);
            //hex values --> 30 01 00 01 30 02 7f ff 19 01 02 f3
            mem.cc++;
        }

        opcode = "";
        //setting program counter to the code base
        mem.pc = mem.cb;
        //while program counter is less thabn the code couter or it does not encounter terminate operation
        //memory reading continues
        while (!(mem.pc >= mem.cc) || !(mem.mem_hex[mem.pc].equals("f3"))) {
            opcode = mem.mem_hex[mem.pc];
            switch (opcode) {
                //whichever instruction is found in the memory, its switch case is run and program counter is
                //incremmented accordingly
                case "16":
                    mem.pc++;
                    operations.Move(mem.mem_hex[mem.pc], mem.mem_hex[mem.pc + 1]);
                    //pc is incrememnted by two to jump on the next instruction
                    mem.pc += 2;
                    break;
                case "17":
                    mem.pc++;
                    operations.Add(mem.mem_hex[mem.pc], mem.mem_hex[mem.pc + 1]);
                    mem.pc += 2;
                    break;
                case "18":
                    mem.pc++;
                    operations.Sub(mem.mem_hex[mem.pc], mem.mem_hex[mem.pc + 1]);
                    mem.pc += 2;
                    break;
                case "19":
                    mem.pc++;
                    operations.Mul(mem.mem_hex[mem.pc], mem.mem_hex[mem.pc + 1]);
                    mem.pc += 2;
                    break;
                case "1A":
                    mem.pc++;
                    operations.Div(mem.mem_hex[mem.pc], mem.mem_hex[mem.pc + 1]);
                    mem.pc += 2;
                    break;
                case "1B":
                    mem.pc++;
                    operations.And(mem.mem_hex[mem.pc], mem.mem_hex[mem.pc + 1]);
                    mem.pc += 2;
                    break;
                case "1C":
                    mem.pc++;
                    operations.Or(mem.mem_hex[mem.pc], mem.mem_hex[mem.pc + 1]);
                    mem.pc += 2;
                    break;
//                case "30":
//                    mem.pc++;
//                    operations.MOVI(mem.mem_hex[mem.pc], (short) Integer.parseInt(mem.mem_hex[mem.pc + 1] + mem.mem_hex[mem.pc + 2], 16));
//                    mem.pc += 3;
//                    break;
                case "31":
                    mem.pc++;
                    operations.Addi(mem.mem_hex[mem.pc], (short) Integer.parseInt(mem.mem_hex[mem.pc + 1] + mem.mem_hex[mem.pc + 2]));
                    mem.pc += 3;
                    break;
                case "32":
                    mem.pc++;
                    operations.Subi(mem.mem_hex[mem.pc], (short) Integer.parseInt(mem.mem_hex[mem.pc + 1] + mem.mem_hex[mem.pc + 2]));
                    mem.pc += 3;
                    break;
                case "33":
                    mem.pc++;
                    operations.Muli(mem.mem_hex[mem.pc], (short) Integer.parseInt(mem.mem_hex[mem.pc + 1] + mem.mem_hex[mem.pc + 2], 16));
                    mem.pc += 3;
                    break;
                case "34":
                    mem.pc++;
                    operations.Divi(mem.mem_hex[mem.pc], (short) Integer.parseInt(mem.mem_hex[mem.pc + 1] + mem.mem_hex[mem.pc + 2], 16));
                    mem.pc += 3;
                    break;
                case "35":
                    mem.pc++;
                    operations.Andi(mem.mem_hex[mem.pc], (short) Integer.parseInt(mem.mem_hex[mem.pc + 1] + mem.mem_hex[mem.pc + 2], 16));
                    mem.pc += 3;
                    break;
                case "36":
                    mem.pc++;
                    operations.Ori(mem.mem_hex[mem.pc], (short) Integer.parseInt(mem.mem_hex[mem.pc + 1] + mem.mem_hex[mem.pc + 2]));
                    mem.pc += 3;
                    break;
//                case "37":
//                    mem.pc++;
//                    operations.BZ((short) Integer.parseInt(mem.mem_hex[mem.pc], 16));
//                case "38":
//                    mem.pc++;
//                    operations.BNZ((short) Integer.parseInt(mem.mem_hex[mem.pc], 16));
//                case "39":
//                    mem.pc++;
//                    operations.BC((short) Integer.parseInt(mem.mem_hex[mem.pc], 16));
//                case "3A":
//                    mem.pc++;
//                    operations.BS((short) Integer.parseInt(mem.mem_hex[mem.pc], 16));
//                case "3B":
//                    mem.pc++;
//                    operations.JMP((short) Integer.parseInt(Memory.mem_hex[Memory.pc], 16));
//                    mem.pc++;
//                case "3D":
//                    mem.pc++;
//                    operations.Act((short) (Integer.parseInt(mem.mem_hex[mem.pc])));
//                    mem.pc++;
//                case "51":
//                    mem.pc++;
//                    operations.MOVL(mem.mem_hex[mem.pc]);
//                    mem.pc++;
//                case "52":
//                    mem.pc++;
//                    operations.MOVS(mem.mem_hex[mem.pc]);
//                    mem.pc++;
//                case "71":
//                    mem.pc++;
//                    operations.SHL(mem.mem_hex[mem.pc]);
//                    mem.pc += 1;
//                    break;
//                case "72":
//                    mem.pc++;
//                    operations.SHR(mem.mem_hex[mem.pc]);
//                    mem.pc += 1;
//                    break;
//                case "73":
//                    mem.pc++;
//                    operations.RTL(mem.mem_hex[mem.pc]);
//                    mem.pc += 1;
//                    break;
//                case "74":
//                    mem.pc++;
//                    operations.RTR(mem.mem_hex[mem.pc]);
//                    mem.pc += 1;
//                    break;
//                case "75":
//                    mem.pc++;
//                    operations.INC(mem.mem_hex[mem.pc]);
//                    mem.pc += 1;
//                    break;
//                case "76":
//                    mem.pc++;
//                    operations.DEC(mem.mem_hex[mem.pc]);
//                    mem.pc += 1;
//                    break;
                case "f2":
                    operations.NOOP();
                    mem.pc++;
//                case "f3":
//                    GPR gpr = new GPR();
//                    System.out.println("General Purpose Registers\nDecimal Registers");
//                    gpr.show_decimal_registers();
//                    System.out.println("Hex Registers");
//                    gpr.show_hex_registers();
//                    System.out.println("\nSpecial Purpose Registers");
//                    SPR sprs = new SPR();
//                    sprs.spr[0] = (short) MEMORY.cb;
//                    sprs.spr[2] = (short) MEMORY.cc;
//                    sprs.spr[9] = (short) MEMORY.pc;
//                    sprs.spr[10] = (short) Integer.parseInt(opcode, 16);
//                    for (int i = 0; i < sprs.spr.length; i++) {
//                        System.out.println(sprs.spr[i]);
//
//                    }
//                    operations.END();


            }
        }
    }
}