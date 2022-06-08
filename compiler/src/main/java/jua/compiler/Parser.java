package jua.compiler;

public interface Parser {

    void setLexer(Lexer lexer);

    Lexer getLexer();

    Tree.CompilationUnit parse();

    Tree.Statement parseStatement();

    Tree.Expression parseExpression();
}
