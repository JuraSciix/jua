package jua.vm.runtime;

public enum Operator {

    CONCAT(".", Arity.BINARY),
    ADD("+", Arity.BINARY),
    SUB("-", Arity.BINARY),
    MUL("*", Arity.BINARY),
    DIV("/", Arity.BINARY),
    REM("%", Arity.BINARY),
    SHL("<<", Arity.BINARY),
    SHR(">>", Arity.BINARY),
    AND("&", Arity.BINARY),
    OR("|", Arity.BINARY),
    XOR("^", Arity.BINARY),
    NEG("-", Arity.UNARY),
    POS("+", Arity.UNARY),
    NOT("~", Arity.UNARY),
    INC("++", Arity.UNARY),
    DEC("--", Arity.UNARY),
    POW("**", Arity.BINARY);

    public enum Arity {

        BINARY("binary"),
        UNARY("unary");

        public final String name;

        Arity(String name) {
            this.name = name;
        }
    }

    public final String sign;

    public final Arity arity;

    Operator(String sign, Arity arity) {
        this.sign = sign;
        this.arity = arity;
    }

    @Override
    public String toString() {
        // unary '++'
        // binary '&'
        return arity.name + " '" + sign + "'";
    }
}
