package jua.spec.interpreter;

public interface Instructions {

    /*
     * +--------------------------------------------------------------------------------------------------+
     * |                       I  N  S  T  R  U  C  T  I  O  N     F  O  R  M  A  T                       |
     * +-------------+----------------+----------------+----------------+----------------+----------------+
     * |    Name     | OPCODE         | HAX            | HBX            | HCX            | EXTRA          |
     * +-------------+----------------+----------------+----------------+----------------+----------------+
     * | Description | Operation code | Operand A      | Operand B      | Operand C      | Extra bits     |
     * +-------------+----------------+----------------+----------------+----------------+----------------+
     * |    Width    | 8              | 16             | 16             | 16             | 8              |
     * +-------------+----------------+----------------+----------------+----------------+----------------+
     * |    Shift    | 0              | 8              | 24             | 40             | 56             |
     * +-------------+----------------+----------------+----------------+----------------+----------------+
     */

    static long storeOPCode(long instruction, int opcode) {
        return (instruction & ~0x00000000000000ffL) | (((long) opcode) & 0x00000000000000ffL);
    }

    static long storeHAX(long instruction, int ax) {
        return (instruction & ~0x0000000000ffff00L) | (((long) ax << 8) & 0x0000000000ffff00L);
    }

    static long storeHBX(long instruction, int bx) {
        return (instruction & ~0x000000ffff000000L) | (((long) bx << 24) & 0x000000ffff000000L);
    }

    static long storeHCX(long instruction, int cx) {
        return (instruction & ~0x00ffff0000000000L) | (((long) cx << 40) & 0x00ffff0000000000L);
    }

    static long storeExtra(long instruction, int extra) {
        return (instruction & ~0xff00000000000000L) | (((long) extra << 56) & 0xff00000000000000L);
    }

    static int fetchOPCode(long instruction) {
        return (int) (instruction & 0x00000000000000ffL);
    }

    static int fetchHAX(long instruction) {
        return (int) ((instruction & 0x0000000000ffff00L) >> 8);
    }

    static int fetchHBX(long instruction) {
        return (int) ((instruction & 0x000000ffff000000L) >> 24);
    }

    static int fetchHCX(long instruction) {
        return (int) ((instruction & 0x00ffff0000000000L) >> 40);
    }

    static int fetchExtra(long instruction) {
        return (int) ((instruction & 0xff00000000000000L) >>> 56);
    }

    interface OPCodes {
        byte nop = 0;
        byte const_null = 1;
        byte const_true = 2;
        byte const_false = 3;
        byte lc = 4; // load constant
        byte li = 5; // load integer
        byte mov = 6;
        byte reset = 7;
        byte concat = 0;
        byte add = 0;
        byte sub = 0;
        byte mul = 0;
        byte div = 0;
        byte rem = 0;
        byte shl = 0;
        byte shr = 0;
        byte and = 0;
        byte or = 0;
        byte xor = 0;
        byte neg = 0;
        byte pos = 0;
        byte not = 0;
        byte inc = 0;
        byte dec = 0;
        byte aload = 0;
        byte astore = 0;
        byte ainc = 0;
        byte adec = 0;
        byte unset = 0;
        byte len = 0;
        byte newmap = 0;
        byte newlist = 0;
        byte mkitr = 0;
        byte ifetch = 0;
        byte inext = 0;
        byte getconst = 0;
        byte push = 0;
        byte pc = 0; // push const
        byte call = 0;
        byte qcall_0 = 0;
        byte qcall_1 = 0;
        byte qcall_2 = 0;
        byte isz = 0; // is zero
        byte isnz = 0; // is non-zero
        byte iseq = 0;
        byte isne = 0;
        byte isgt = 0;
        byte isle = 0;
        byte isge = 0;
        byte islt = 0;
        byte isnull = 0;
        byte isnonnull = 0;
        byte isset = 0;
        byte isnotset = 0;
        byte ifz = 0;
        byte ifnz = 0;
        byte ifeq = 0;
        byte ifne = 0;
        byte ifgt = 0;
        byte ifle = 0;
        byte ifge = 0;
        byte iflt = 0;
        byte ifnull = 0;
        byte ifnonnull = 0;
        byte ifset = 0;
        byte ifnotset = 0;
        byte goto_ = 0;
        byte ret = 99;
        byte return_ = 100;

        @Deprecated
        byte halt = 101;
    }
}
