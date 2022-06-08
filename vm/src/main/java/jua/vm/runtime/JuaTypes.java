package jua.vm.runtime;

import jua.util.API;

@API.Unimplementable
public interface JuaTypes {

    int U = 0; // --- undefined ---
    int L = 1; // long
    int D = 2; // double
    int B = 3; // boolean
    int S = 4; // string
    int A = 6; // array
    int T = 5; // map
    int E = 7; // iterator
    int N = 8; // <null>

    int PAIR_SHIFT = 4;

    int LL = (L << PAIR_SHIFT) | L; // long | long
    int LD = (L << PAIR_SHIFT) | D; // long | double
    int DL = (D << PAIR_SHIFT) | L; // double | long
    int DD = (D << PAIR_SHIFT) | D; // double | double
    int BB = (B << PAIR_SHIFT) | B; // boolean | boolean
    int SS = (S << PAIR_SHIFT) | S; // string | string
    int AA = (A << PAIR_SHIFT) | A; // array | array
    int TT = (T << PAIR_SHIFT) | T; // map | map
    int EE = (E << PAIR_SHIFT) | E; // iterator | iterator
    int NN = (N << PAIR_SHIFT) | N; // <null> | <null>
    int LS = (L << PAIR_SHIFT) | S; // long | string
    int DS = (D << PAIR_SHIFT) | S; // double | string
    int BS = (B << PAIR_SHIFT) | S; // boolean | string
    int NS = (N << PAIR_SHIFT) | S; // <null> | string
    int SL = (S << PAIR_SHIFT) | L; // string | long
    int SD = (S << PAIR_SHIFT) | D; // string | double
    int SB = (S << PAIR_SHIFT) | B; // string | boolean
    int SN = (S << PAIR_SHIFT) | N; // string | <null>

    static boolean isScalar(int type) {
        return type > U && type <= S;
    }

    static int pair(int type1, int type2) {
        return (type1 << PAIR_SHIFT) | type2;
    }

    static String name(int type) {
        switch (type) {
            case U: return "undefined";
            case L: return "int";
            case D: return "float";
            case B: return "boolean";
            case S: return "string";
            case T: return "map";
            case A: return "list";
            case E: return "iterator";
            case N: return "<null>";
        }
        throw new IllegalArgumentException("type: " + type);
    }
}
