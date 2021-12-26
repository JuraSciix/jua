package jua.interpreter.states;

import jua.interpreter.Environment;
import jua.tools.CodePrinter;

public class Ifcmpne extends JumpState {

    @Override
    public void print(CodePrinter printer) {
        printer.printName("Ifcmpne");
        super.print(printer);
    }

    @Override
    public int run(Environment env) {
        if (!env.popStack().equals(env.popStack())) {
            return destination;
        } else {
            return NEXT;
        }
    }
}