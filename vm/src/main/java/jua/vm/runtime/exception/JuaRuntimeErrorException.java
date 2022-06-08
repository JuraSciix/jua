package jua.vm.runtime.exception;

public class JuaRuntimeErrorException extends RuntimeException {

    public JuaRuntimeErrorException() {
        super();
    }

    public JuaRuntimeErrorException(String message) {
        super(message);
    }
}
