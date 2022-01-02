package jua.interpreter.opcodes;

import jua.interpreter.InterpreterRuntime;
import jua.tools.CodePrinter;

public class Getconst implements Opcode {

    private final String name;

    public Getconst(String name) {
        this.name = name;
    }

    @Override
    public void print(CodePrinter printer) {
        printer.printName("getconst");
        printer.print(name);
    }

    @Override
    public int run(InterpreterRuntime env) {
        env.pushStack(env.getConstantByName(name).value);
        return NEXT;
    }
}