package xxl.core;

import java.io.Serial;
import java.io.Serializable;

public class LiteralString extends Literal implements Serializable {
    @Serial
    private static final long serialVersionUID = 202308312359L;
    private String value;

    public LiteralString(String value) {
        this.value = value;
    }

    public LiteralString() {
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitLiteralString(this);
    }

    @Override
    public String getType() {
        return "String";
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public Literal value() {
        return this;
    }


    public boolean containsReference() {
        return value.matches("^x=\\d+.*");
    }
}


    

