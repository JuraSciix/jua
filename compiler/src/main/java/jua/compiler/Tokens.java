package jua.compiler;

import jua.spec.source.Name;

public final class Tokens {

    public enum TokenType {
        AMP("&"),
        AMPAMP("&&"),
        AMPEQ("&="),
        AT("@"),
        BANG("!"),
        BANGEQ("!="),
        BAR("|"),
        BARBAR("||"),
        BAREQ("|="),
        BOOL("bool", Kind.NAMED),
        BREAK("break"),
        CARET("^"),
        CARETEQ("^="),
        CASE("case"),
        COL(":"),
        COLCOL("::"),
        COMMA(","),
        CONST("const"),
        CONTINUE("continue"),
        CUSTOM,
        DEFAULT("default"),
        DO("do"),
        DOT("."),
        ELLIPSIS("..."),
        ELSE("else"),
        EOF,
        EQ("="),
        EQEQ("=="),
        FALLTHROUGH("fallthrough"),
        FALSE("false"),
        FLOAT("float", Kind.NAMED),
        FLOATLITERAL(Kind.NUMERIC),
        FN("fn"),
        FOR("for"),
        GOTO("goto"),
        GT(">"),
        GTEQ(">="),
        GTGT(">>"),
        GTGTEQ(">>="),
        IDENTIFIER(Kind.NAMED),
        IF("if"),
        INT("int", Kind.NAMED),
        INTLITERAL(Kind.NUMERIC),
        LBRACE("{"),
        LBRACKET("["),
        LPAREN("("),
        LT("<"),
        LTEQ("<="),
        LTLT("<<"),
        LTLTEQ("<<="),
        MINUS("-"),
        MINUSEQ("-="),
        MINUSMINUS("--"),
        NULL("null"),
        OF("of"),
        PERCENT("%"),
        PERCENTEQ("%="),
        PLUS("+"),
        PLUSEQ("+="),
        PLUSPLUS("++"),
        QUES("?"),
        QUESDOT("?."),
        QUESLBRACKET("?["),
        QUESQUES("??"),
        QUESQUESEQ("??="),
        RBRACE("}"),
        RBRACKET("]"),
        RETURN("return"),
        RPAREN(")"),
        SEMI(";"),
        SLASH("/"),
        SLASHEQ("/="),
        STAR("*"),
        STAREQ("*="),
        STR("str", Kind.NAMED),
        STRINGLITERAL(Kind.STRING),
        SWITCH("switch"),
        TILDE("~"),
        TRUE("true"),
        TYPE("type"),
        WHILE("while"),
        YIELD("yield");

        public enum Kind {
            DEFAULT,
            NAMED,
            NUMERIC,
            STRING
        }

        public final String value;

        public final Kind kind;

        TokenType() {
            this(null, Kind.DEFAULT);
        }

        TokenType(String value) {
            this(value, Kind.DEFAULT);
        }

        TokenType(Kind kind) {
            this(null, kind);
        }

        TokenType(String value, Kind kind) {
            this.value = value;
            this.kind = kind;
        }
    }

    public static class Token {

        public final int pos;

        public final TokenType type;

        public Token(int pos, TokenType type) {
            this.pos = pos;
            this.type = type;
        }

        TokenType.Kind getKind() { return TokenType.Kind.DEFAULT; }

        public Name name() { throw new UnsupportedOperationException(); }

        public String str() { throw new UnsupportedOperationException(); }

        public int radix() { throw new UnsupportedOperationException(); }
    }

    public static class NamedToken extends Token {

        public final Name name;

        public NamedToken(int pos, TokenType type, Name name) {
            super(pos, type);
            this.name = name;
        }

        @Override
        TokenType.Kind getKind() { return TokenType.Kind.NAMED; }

        @Override
        public Name name() { return name; }
    }

    public static class StringToken extends Token {

        public final String str;

        public StringToken(int pos, TokenType type, String str) {
            super(pos, type);
            this.str = str;
        }

        @Override
        TokenType.Kind getKind() { return TokenType.Kind.STRING; }

        @Override
        public String str() { return str; }
    }

    public static class NumericToken extends StringToken {

        public final int radix;

        public NumericToken(int pos, TokenType type, String str, int radix) {
            super(pos, type, str);
            this.radix = radix;
        }

        @Override
        TokenType.Kind getKind() { return TokenType.Kind.NUMERIC; }

        @Override
        public int radix() { return radix; }
    }
}
