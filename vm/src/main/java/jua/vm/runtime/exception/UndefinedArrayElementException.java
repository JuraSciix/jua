package jua.vm.runtime.exception;

import jua.util.Preconditions;
import jua.vm.interpreter.Address;

public class UndefinedArrayElementException extends JuaRuntimeErrorException {

    public final Address element;

    public UndefinedArrayElementException(Address element) {
        Preconditions.ensureNotNull(element, "array element");
        this.element = element;
    }
}
