package jua.spec.source;

public class NamedArgument extends Argument {

    public final Name name;

    public NamedArgument(Tree.Expression expr, Name name) {
        super(expr);
        this.name = name;
    }
}
