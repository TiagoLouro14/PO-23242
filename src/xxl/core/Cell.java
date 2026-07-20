package xxl.core;

import java.io.Serializable;

public class Cell extends Content implements Serializable {
    private Content _content;
    private String _expression;


    public Cell() {
    }

    public Cell(Content content) {
        _content = (content != null) ? content : new LiteralString("");
    }

    public String toString() {
        return _content.asString();
    }

    @Override
    Literal value() {
        return _content.value();
    }

    public Content getContent(){
        return _content;
    }
    public String getContentasString(){
        return _content.toString();
    }

    public void setExpression(String expression){
        _expression = expression;
    }


    public String getExpression(){
        return (_expression != null) ? _expression : "";
    }
}
