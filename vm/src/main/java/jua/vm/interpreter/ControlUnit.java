package jua.vm.interpreter;

import jua.spec.interpreter.Instructions;
import jua.vm.runtime.code.ConstantPool;

public class ControlUnit {

    public static void perform(InterpreterThread thread, InterpreterFrame frame) {
        int cp = frame.codePoint();
        long[] code = frame.owner().codeSegment().code();
        Address[] memory = thread.getMemory();
        int offset = thread.getOffset();
        ConstantPool constantPool = frame.owner().codeSegment().constantPool();

        try {
            while (true) {
                long instr = code[cp];

                switch (Instructions.fetchOPCode(instr)) {
                    case Instructions.OPCodes.nop: {
                        cp++;
                        continue;
                    }

                    case Instructions.OPCodes.const_null: {
                        int ax = Instructions.fetchHAX(instr);
                        memory[offset + ax].setNull();
                        cp++;
                        continue;
                    }

                    case Instructions.OPCodes.const_true: {
                        int ax = Instructions.fetchHAX(instr);
                        memory[offset + ax].setBoolean(true);
                        cp++;
                        continue;
                    }

                    case Instructions.OPCodes.const_false: {
                        int ax = Instructions.fetchHAX(instr);
                        memory[offset + ax].setBoolean(false);
                        cp++;
                        continue;
                    }

                    case Instructions.OPCodes.lc: {
                        int ax = Instructions.fetchHAX(instr);
                        int bx = Instructions.fetchHBX(instr);
                        constantPool.load(ax, memory[offset + bx]);
                        cp++;
                        continue;
                    }

                    case Instructions.OPCodes.li: {
                        int ax = Instructions.fetchHAX(instr);
                        int bx = Instructions.fetchHBX(instr);
                        memory[offset + bx].setLong(ax);
                        cp++;
                        continue;
                    }

                    case Instructions.OPCodes.mov: {
                        int ax = Instructions.fetchHAX(instr);
                        int bx = Instructions.fetchHBX(instr);
                        memory[offset + bx].set(memory[offset + ax]);
                        cp++;
                        continue;
                    }

                    case Instructions.OPCodes.reset: {
                        int ax = Instructions.fetchHAX(instr);
                        memory[offset + ax].reset();
                        cp++;
                        continue;
                    }

                    // todo

                    case Instructions.OPCodes.ret: {
                        memory[offset].setNull();
                        thread.setMsg(InterpreterThread.Messages.POPPING_FRAME);
                        cp++;
                        return;
                    }

                    case Instructions.OPCodes.return_: {
                        int ax = Instructions.fetchHAX(instr);
                        memory[offset].set(memory[offset + ax]);
                        thread.setMsg(InterpreterThread.Messages.POPPING_FRAME);
                        cp++;
                        return;
                    }

                    case Instructions.OPCodes.halt: {
                        thread.setMsg(InterpreterThread.Messages.INTERRUPTED);
                        cp++;
                        return;
                    }
                }
            }
        } finally {
            frame.codePoint(cp);
        }
    }
}
