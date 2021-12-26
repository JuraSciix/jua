package jua.interpreter.states;

import jua.interpreter.Environment;
import jua.tools.CodePrinter;

public enum Dup_x2 implements State {

    INSTANCE;


    @Override
    public void print(CodePrinter printer) {
        printer.printName("dup_x2");
    }

    @Override
    public int run(Environment env) {
        env.getFrame().dup1_x2();
        return NEXT;
    }
}