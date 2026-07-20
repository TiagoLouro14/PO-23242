package xxl.core;


import java.io.Serializable;

public abstract class IntervalFunction extends Function implements Serializable {
    private static final long serialVersionUID = 202308312359L;
    protected Range _range;

    public IntervalFunction(Range range) {
        _range = range;
    }

    IntervalFunction() {
    }

    @Override
    protected abstract Literal compute();

    public String toString() {
        return getFunctionName();
    }

    protected abstract String getFunctionName();
}

