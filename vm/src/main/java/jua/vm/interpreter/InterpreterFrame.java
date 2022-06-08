package jua.vm.interpreter;

import jua.vm.runtime.JuaFunction;

public final class InterpreterFrame {

    private final JuaFunction owner;
    private final InterpreterFrame sender;

    private short cp = 0;

    InterpreterFrame(JuaFunction owner, InterpreterFrame sender) {
        this.owner = owner;
        this.sender = sender;
    }

    public JuaFunction owner() {
        return owner;
    }

    public InterpreterFrame sender() {
        return sender;
    }

    public int codePoint() {
        return (cp & 0xffff);
    }

    public void codePoint(int codePoint) {
        this.cp = (short) codePoint;
    }

    public boolean isInterpretable() {
        return !owner.isNative();
    }
}
