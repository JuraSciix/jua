package jua.compiler;

import jua.util.List;
import jua.util.Source;

public interface Tree {

    enum Tag {
        COMPILATION_UNIT
    }

    interface Visitor {
        void visitCompilationUnit(CompilationUnit tree);
    }

    Tag getTag();

    void accept(Visitor visitor);

    class CompilationUnit implements Tree {

        public final Source source;

        public final List<? extends Tree> trees;

        public CompilationUnit(Source source, List<? extends Tree> trees) {
            this.source = source;
            this.trees = trees;
        }

        @Override
        public Tag getTag() { return Tag.COMPILATION_UNIT; }

        @Override
        public void accept(Visitor visitor) { visitor.visitCompilationUnit(this); }
    }

    abstract class Statement implements Tree {

        public final int pos;

        protected Statement(int pos) {
            this.pos = pos;
        }
    }

    abstract class Expression extends Statement {

        protected Expression(int pos) {
            super(pos);
        }
    }
}
