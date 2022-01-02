package jua.interpreter.opcodes;

import jua.interpreter.InterpreterRuntime;
import jua.interpreter.runtime.Operand;
import jua.tools.CodePrinter;

public final class Vload implements Opcode {

    private final int id;

    public Vload(int id) {
        this.id = id;
    }

    @Override
    public void print(CodePrinter printer) {
        printer.printName("vload");
        printer.printLocal(id);
    }

    @Override
    public int run(InterpreterRuntime env) {
        Operand operand = env.getLocal(id);

        if (operand == null) {
            env.getFrame().reportUndefinedVariable(id);
        } else {
            env.pushStack(operand);
        }
        return NEXT;
    }
}