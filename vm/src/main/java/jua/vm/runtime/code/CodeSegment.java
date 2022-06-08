package jua.vm.runtime.code;

import jua.util.Preconditions;

public final class CodeSegment {

    private final long[] code;

    private final int memorySize;

    private final ConstantPool constantPool;

    private final LineNumberTable lineNumberTable;

    public CodeSegment(long[] code, int memorySize, ConstantPool constantPool, LineNumberTable lineNumberTable) {
        Preconditions.ensureNotNull(code, "code");
        Preconditions.ensureTrue(memorySize >= 0, "negative memory size");
        Preconditions.ensureNotNull(constantPool, "constant pool");
        Preconditions.ensureNotNull(lineNumberTable, "line number table");
        this.code = code;
        this.memorySize = memorySize;
        this.constantPool = constantPool;
        this.lineNumberTable = lineNumberTable;
    }

    public long[] code() {
        return code;
    }

    public int memorySize() {
        return memorySize;
    }

    public ConstantPool constantPool() {
        return constantPool;
    }

    public LineNumberTable lineNumberTable() {
        return lineNumberTable;
    }
}
