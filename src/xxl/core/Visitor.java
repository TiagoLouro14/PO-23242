package xxl.core;

public interface Visitor {
    void visitLiteralInteger(LiteralInteger literal);
    void visitLiteralString(LiteralString literal);
}


