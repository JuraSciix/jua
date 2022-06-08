package jua.spec.source;

public class OptionalParameter extends Parameter {

    public final Tree.Expression expr;

    public OptionalParameter(Name name, Tree.Expression expr) {
        super(name);
        this.expr = expr;
    }
}
