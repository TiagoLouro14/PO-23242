package xxl.core;

abstract class BinaryFunction extends Function {
    protected Content _arg1;
    protected Content _arg0;


    public BinaryFunction(Content arg1, Content arg0) {
        _arg1 = arg1;
        _arg0 = arg0;
    }

    BinaryFunction() {
    }

    protected abstract String getFunctionName();

    protected abstract Literal compute();

    public String toString() {
        return getFunctionName();
    }

}

