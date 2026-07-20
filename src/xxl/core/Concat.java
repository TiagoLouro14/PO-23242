package xxl.core;

import java.io.Serial;
import java.io.Serializable;

public class Concat extends IntervalFunction implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;
    public Concat(Range range) {
        super(range);
    }



    @Override
    protected Literal compute() {
        return new LiteralString(":/");
    }

    @Override
    protected String getFunctionName() {
        return "CONCAT";
    }

}
