package jua.runtime;

import jua.interpreter.*;

public class ScriptRuntimeFunction implements RuntimeFunction {

    public final int[] locals;

    public final int[] optionals;

    public final Program program;

    public ScriptRuntimeFunction(int[] locals, int[] optionals, Program program) {
        this.locals = locals;
        this.optionals = optionals;
        this.program = program;
    }

    @Override
    public void call(InterpreterRuntime env, String name, int argc) {
        int tot = locals.length;
        int req = (tot - optionals.length);

        if (argc > tot) {
            error(name, "arguments too many. (total " + tot + ", got " + argc + ')');
        } else if (argc < req) {
            error(name, "arguments too few. (required " + req + ", got " + argc + ')');
        }
        ProgramFrame frame = program.makeFrame();

        for (int i = tot - 1; i >= 0; i--) {
            frame.store(locals[i], (i >= argc) ? program.constantPool[i - req] : env.popStack());
        }
        env.enterCall(frame);
        Trap.bti();
    }

    private void error(String name, String message) {
        throw new InterpreterError(String.format("%s: %s", name, message));
    }
}