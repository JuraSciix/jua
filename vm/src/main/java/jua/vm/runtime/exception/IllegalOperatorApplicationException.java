package jua.vm.runtime.exception;

import jua.util.Preconditions;
import jua.vm.interpreter.Address;
import jua.vm.runtime.Operator;

public class IllegalOperatorApplicationException extends JuaRuntimeErrorException {

    public final Operator operator;

    public final Address lhs;

    public final Address rhs;

    public IllegalOperatorApplicationException(Operator operator, Address lhs, Address rhs) {
        Preconditions.ensureNotNull(operator, "operator");
        Preconditions.ensureNotNull(lhs, "lhs");
        Preconditions.ensureNotNull(rhs, "rhs");
        this.operator = operator;
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
