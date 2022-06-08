package jua.vm.runtime;

import jua.vm.interpreter.Address;
import jua.vm.interpreter.InterpreterFrame;
import jua.vm.interpreter.InterpreterThread;

public interface JuaNativeHandler {

    void execute(InterpreterThread thread, InterpreterFrame frame, Address[] args, Address returnValue);
}
