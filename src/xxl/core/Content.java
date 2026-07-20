package xxl.core;

import java.io.Serial;
import java.io.Serializable;

public abstract class Content implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;


    public abstract String toString();
    abstract Literal value();
    public String asString(){
        return toString();
    }
    public int asInt(){
        return Integer.parseInt(value().toString());
    }

}
