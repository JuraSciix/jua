package jua.interpreter.instructions;

import jua.interpreter.InterpreterRuntime;
import jua.interpreter.InterpreterError;
import jua.interpreter.runtime.Operand;
import jua.compiler.CodePrinter;

public enum Shl implements Instruction {

    INSTANCE;

    @Override
    public void print(CodePrinter printer) {
        printer.printName("shl");
    }

    @Override
    public int run(InterpreterRuntime env) {
        Operand rhs = env.popStack();
        Operand lhs = env.popStack();

        if (lhs.isInt() && rhs.isInt()) {
            env.pushStack(lhs.intValue() << rhs.intValue());
        } else {
            throw InterpreterError.binaryApplication("<<", lhs.type(), rhs.type());
        }
        return NEXT;
    }
}