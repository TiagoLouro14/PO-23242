package xxl.core;

import java.io.Serializable;

public class LiteralInteger extends Literal implements Serializable {

    private int value;

    public LiteralInteger() {
    }

    public LiteralInteger(int value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return "Integer";
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Literal value() {
        return this;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitLiteralInteger(this);
    }


    public boolean containsReference() {
        return false; // Literais inteiros nunca contêm referências
    }
}

