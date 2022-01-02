package jua.interpreter.opcodes;

import jua.interpreter.InterpreterRuntime;
import jua.interpreter.InterpreterError;
import jua.interpreter.runtime.Operand;
import jua.tools.CodePrinter;

public final class Ifcmple extends ChainOpcode {

    @Override
    public void print(CodePrinter printer) {
        printer.printName("ifcmple");
        super.print(printer);
    }

    @Override
    public int run(InterpreterRuntime env) {
        Operand rhs = env.popStack();
        Operand lhs = env.popStack();

        if (!lhs.isNumber() || !rhs.isNumber()) {
            // op inverted due to VM mechanics
            throw InterpreterError.binaryApplication(">", lhs.type(), rhs.type());
        }
        if ((lhs.isFloat() || rhs.isFloat())
                ? lhs.floatValue() > rhs.floatValue()
                : lhs.intValue() > rhs.intValue()) {
           return NEXT;
        } else {
            return destination;
        }
    }
}