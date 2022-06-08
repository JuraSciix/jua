package jua.vm.runtime.exception;

public class IllegalTypeConversionException extends JuaRuntimeErrorException {

    public final int typeFrom;

    public final int typeTo;

    public IllegalTypeConversionException(int typeFrom, int typeTo) {
        this.typeFrom = typeFrom;
        this.typeTo = typeTo;
    }
}
