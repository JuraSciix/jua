package jua.vm.interpreter;

import jua.util.Preconditions;
import jua.vm.runtime.code.ConstantPool;

@Deprecated
public final class InterpreterState {

    // Immutable fields:

    private final long[] code;
    private final Address[] memory;
    private final ConstantPool constantPool;
    private final InterpreterThread thread;

    // Mutable fields:

    private short cp = 0;
    private short advancingCP = 0;

    InterpreterState(long[] code, Address[] memory, ConstantPool constantPool, InterpreterThread thread) {
        // Check parameters.
        Preconditions.ensureNotNull(code, "code");
        Preconditions.ensureNotNull(memory, "memory");
        Preconditions.ensureNotNull(constantPool, "constant pool");
        Preconditions.ensureNotNull(thread, "thread");

        // Store parameters.
        this.code = code;
        this.memory = memory;
        this.constantPool = constantPool;
        this.thread = thread;
    }

    // Getters:

    public long[] code() {
        return code;
    }

    public Address[] memory() {
        return memory;
    }

    public ConstantPool constantPool() {
        return constantPool;
    }

    public InterpreterThread thread() {
        return thread;
    }

    public int codePoint() {
        return (cp & 0xffff);
    }

    public int advancingCP() {
        return (advancingCP & 0xffff);
    }

    // Setters:

    public void codePoint(int cp) {
        this.cp = (short) cp;
    }

    public void advancingCP(int advancingCP) {
        this.advancingCP = (short) advancingCP;
    }

    // Other:

    public void advanceCP() {
        int advancedCP = codePoint() + advancingCP();
        codePoint(advancedCP);
        advancingCP(0);
    }
}
