public class Decoder {

    public static ISA operations = new ISA();



    public static void decoder(String opcode){
        switch (opcode) {
            case "16":
                SPRs.code_reg[2]++;
                int[] arr = Memory.tranlation(SPRs.code_reg[2]);
                String R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                String R2 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                operations.Move(R1, R2);
                SPRs.code_reg[2] += 2;
                break;
            case "17":
                SPRs.code_reg[2]++;

                arr = Memory.tranlation(SPRs.code_reg[2]);
                R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                R2 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                operations.Add(R1, R2);
                SPRs.code_reg[2] += 2;
                break;
            case "18":
                SPRs.code_reg[2]++;
                arr = Memory.tranlation(SPRs.code_reg[2]);
                R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                R2 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                operations.Sub(R1, R2);
                SPRs.code_reg[2] += 2;
                break;
            case "19":
                SPRs.code_reg[2]++;

                arr = Memory.tranlation(SPRs.code_reg[2]);
                R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                R2 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                operations.Mul(R1, R2);
                SPRs.code_reg[2] += 2;
                break;
            case "1a":
                SPRs.code_reg[2]++;

                arr = Memory.tranlation(SPRs.code_reg[2]);
                R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                R2 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                operations.Div(R1, R2);
                SPRs.code_reg[2] += 2;
                break;
            case "1b":
                SPRs.code_reg[2]++;

                arr = Memory.tranlation(SPRs.code_reg[2]);
                R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                R2 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                operations.And(R1, R2);
                SPRs.code_reg[2] += 2;
                break;
            case "1c":
                SPRs.code_reg[2]++;

                arr = Memory.tranlation(SPRs.code_reg[2]);
                R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                R2 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                operations.Or(R1, R2);
                SPRs.code_reg[2] += 2;
                break;
            case "30":
                SPRs.code_reg[2]++;

                arr = Memory.tranlation(SPRs.code_reg[2]);
                R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                int S1 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                int S2 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                short S_combined = (short) (S1 + S2);

                operations.Movi(R1, S_combined);
                SPRs.code_reg[2] += 3;
                break;
            case "31":
                SPRs.code_reg[2]++;

                arr = Memory.tranlation(SPRs.code_reg[2]);
                R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                S1 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                S2 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                S_combined = (short) (S1 + S2);

                operations.Addi(R1, S_combined);
                SPRs.code_reg[2] += 3;
                break;
            case "32":
                SPRs.code_reg[2]++;

                arr = Memory.tranlation(SPRs.code_reg[2]);
                R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                S1 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                S2 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                S_combined = (short) (S1 + S2);

                operations.Subi(R1, S_combined);
                SPRs.code_reg[2] += 3;
                break;
            case "33":
                SPRs.code_reg[2]++;

                arr = Memory.tranlation(SPRs.code_reg[2]);
                R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                S1 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                S2 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                S_combined = (short) (S1 + S2);

                operations.Muli(R1, S_combined);
                SPRs.code_reg[2] += 3;
                break;
            case "34":
                SPRs.code_reg[2]++;

                arr = Memory.tranlation(SPRs.code_reg[2]);
                R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                S1 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                S2 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                S_combined = (short) (S1 + S2);

                operations.Divi(R1, S_combined);
                SPRs.code_reg[2] += 3;
                break;
            case "35":
                SPRs.code_reg[2]++;

                arr = Memory.tranlation(SPRs.code_reg[2]);
                R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                S1 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                S2 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                S_combined = (short) (S1 + S2);

                operations.Andi(R1, S_combined);
                SPRs.code_reg[2] += 3;
                break;
            case "36":
                SPRs.code_reg[2]++;

                arr = Memory.tranlation(SPRs.code_reg[2]);
                R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                S1 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                S2 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                S_combined = (short) (S1 + S2);

                operations.Ori(R1, S_combined);
                SPRs.code_reg[2] += 3;
                break;
            case "37":
                SPRs.code_reg[2]++;
//                    arr = Memory.tranlation(SPRs.code_reg[2]);
                SPRs.code_reg[2]++;
                arr = Memory.tranlation(SPRs.code_reg[2]);
                S1 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                S2 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                S_combined = (short) (S1 + S2);
                operations.BZ(S_combined);
//                    SPRs.code_reg[2] += 2;

                break;
            case "38":
                SPRs.code_reg[2]++;
                SPRs.code_reg[2]++;
                arr = Memory.tranlation(SPRs.code_reg[2]);
                S1 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                S2 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                S_combined = (short) (S1 + S2);

                operations.BNZ(S_combined);
                break;
            case "39":
                SPRs.code_reg[2]++;
                SPRs.code_reg[2]++;
                arr = Memory.tranlation(SPRs.code_reg[2]);
                S1 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                S2 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                S_combined = (short) (S1 + S2);

                operations.BC(S_combined);
                break;
            case "3a":
                SPRs.code_reg[2]++;
                SPRs.code_reg[2]++;
                arr = Memory.tranlation(SPRs.code_reg[2]);
                S1 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                S2 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                S_combined = (short) (S1 + S2);
                operations.BS(S_combined);
                break;
            case "3b":
                SPRs.code_reg[2]++;
                SPRs.code_reg[2]++; // since Register is not used
                arr = Memory.tranlation(SPRs.code_reg[2]);
                S1 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                S2 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                S_combined = (short) (S1 + S2);
                operations.JMP(S_combined);
//                    SPRs.code_reg[2]++;
                break;
            case "3c":
                SPRs.code_reg[2]++;
                SPRs.code_reg[2]++; // since Register is not used
                arr = Memory.tranlation(SPRs.code_reg[2]);
                S1 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                S2 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                S_combined = (short) (S1 + S2);

                operations.CALL(S_combined);
                break;
//                case "3d":
//                    SPRs.code_reg[2]++;
//                    operations.Act((short) (Integer.parseInt(mem.mem_hex[SPRs.code_reg[2]])));
//                    SPRs.code_reg[2]++;
            case "51":
                SPRs.code_reg[2]++;

                arr = Memory.tranlation(SPRs.code_reg[2]);
                R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                S1 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                S2 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                int S_combined_1 = (S1 + S2);

                operations.MOVL(R1, S_combined_1);
                SPRs.code_reg[2]++;
                SPRs.code_reg[2]++;
                SPRs.code_reg[2]++;
                break;
            case "52":
                SPRs.code_reg[2]++;

                arr = Memory.tranlation(SPRs.code_reg[2]);
                R1 = Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]] & 0xFF);

                arr = Memory.tranlation(SPRs.code_reg[2] + 1);
                S1 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                arr = Memory.tranlation(SPRs.code_reg[2] + 2);
//                    + Byte.toUnsignedInt((byte)(Memory.memory[SPRs.code_reg[2] + 2] & 0xFF))
                S2 = (Byte.toUnsignedInt((byte) (Memory.memory1[arr[0]].page[arr[1]] & 0xFF)));

                String S_combined_1_1 = Integer.toString(S1);
                String S_combined_1_2 = ISA.convert(S2);

                S_combined_1 = Integer.parseInt(Integer.toString(Integer.decode(S_combined_1_1 + S_combined_1_2)));

                operations.MOVS(R1, S_combined_1);
                SPRs.code_reg[2]++;
                SPRs.code_reg[2]++;
                SPRs.code_reg[2]++;
                break;
            case "71":
                SPRs.code_reg[2]++;
                arr = Memory.tranlation(SPRs.code_reg[2]);
                operations.SHL(Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]]));
                SPRs.code_reg[2] += 1;
                break;
            case "72":
                SPRs.code_reg[2]++;
                arr = Memory.tranlation(SPRs.code_reg[2]);
                operations.SHR(Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]]));
                SPRs.code_reg[2] += 1;
                break;
            case "73":
                SPRs.code_reg[2]++;
                arr = Memory.tranlation(SPRs.code_reg[2]);
                operations.RTL(Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]]));
                SPRs.code_reg[2] += 1;
                break;
            case "74":
                SPRs.code_reg[2]++;
                arr = Memory.tranlation(SPRs.code_reg[2]);
                operations.RTR(Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]]));
                SPRs.code_reg[2] += 1;
                break;
            case "75":
                SPRs.code_reg[2]++;
                arr = Memory.tranlation(SPRs.code_reg[2]);
                operations.INC(Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]]));
                SPRs.code_reg[2] += 1;
                break;
            case "76":
                SPRs.code_reg[2]++;
                arr = Memory.tranlation(SPRs.code_reg[2]);
                operations.DEC(Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]]));
                SPRs.code_reg[2] += 1;
                break;
            case "77":
                SPRs.code_reg[2]++;
                arr = Memory.tranlation(SPRs.code_reg[2]);
                operations.PUSH(Integer.toHexString(Memory.memory1[arr[0]].page[arr[1]]));
                SPRs.code_reg[2] += 1;
//                System.out.println( SPRs.code_reg[2]);
                break;

            case "78":
                SPRs.code_reg[2]++;
                arr = Memory.tranlation(SPRs.code_reg[2]);
//                    Memory.memory1[arr[0]].page[arr[1]] = operations.POP();
                int a = Integer.parseInt(Integer.toString(Integer.decode(Byte.toString(Memory.memory1[arr[0]].page[arr[1]]))));
                GPRS.gprs[a] = (short) (Integer.parseInt(Integer.toString(Integer.decode("0x" + operations.POP()))));
                SPRs.code_reg[2] += 1;
                break;

            case "f1":
                SPRs.code_reg[2]++;
                SPRs.code_reg[2] = operations.POP();
//                    System.out.println("hello");
                break;


            case "f2":
                operations.NOOP();
                SPRs.code_reg[2]++;
                break;
            case "f3":

//                        System.exit(0);
                break;


            default:
                System.out.println("opcode Invalid");
                System.exit(0);
        }

    }
}
