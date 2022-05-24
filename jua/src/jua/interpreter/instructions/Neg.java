package jua.interpreter.instructions;

import jua.interpreter.InterpreterState;
import jua.runtime.heap.Operand;
import jua.compiler.CodePrinter;

public enum Neg implements Instruction {

    INSTANCE;

    @Override
    public void print(CodePrinter printer) {
        printer.printName("neg");
    }

    @Override
    public int run(InterpreterState state) {
        Operand val = state.popStack();
        state.pushStack(val.neg());
        return NEXT;
    }
}