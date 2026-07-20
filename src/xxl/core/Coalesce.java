package xxl.core;

import java.io.Serializable;

public class Coalesce extends IntervalFunction implements Serializable {
    public Coalesce(Range range) {
        super(range);
    }



    @Override
    protected Literal compute() {
        return new LiteralString(":/");
    }

    @Override
    protected String getFunctionName() {
        return "COALESCE";
    }
}
