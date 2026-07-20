package xxl.core;

abstract class Function extends Content {
    private String _name;

    protected abstract Literal compute();

    public String asString(){
        return compute().toString();
    }
    public int asInt(){
        return compute().asInt();
    }

    public Literal value(){
        return compute();
    }

}
