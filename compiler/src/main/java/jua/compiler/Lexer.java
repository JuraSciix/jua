package jua.compiler;

import jua.util.List;
import jua.util.Source;

import java.util.Iterator;

public interface Lexer {

    void setSource(Source source);

    Source getSource();

    List<Tokens.Token> lex();

    Iterator<Tokens.Token> asIterator();
}
