package jua.interpreter.instructions;

import jua.interpreter.InterpreterRuntime;
import jua.compiler.CodePrinter;

public final class Iflt extends ChainInstruction {

    private final int value;

    public Iflt(int value) {
        this.value = value;
    }

    @Override
    public void print(CodePrinter printer) {
        printer.printName("iflt");
        printer.print(value);
        super.print(printer);
    }

    @Override
    public int run(InterpreterRuntime env) {
        if (env.popInt() < value) {
            return destination;
        } else {
            return NEXT;
        }
    }
}