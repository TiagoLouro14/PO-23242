package xxl.core;

import java.io.Serial;
import java.io.Serializable;

public class Div extends BinaryFunction implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;

    public Div(Content arg0, Content arg1) {
        super(arg0,arg1);
    }

    @Override
    protected String getFunctionName() {
        return "DIV";
    }


    @Override
    protected Literal compute() {
        // Obtém os valores dos argumentos usando _arg1 e _arg2 (definidos na classe pai)
        try{
            int value1 = _arg1.value().asInt();
            int value2 = _arg0.value().asInt();
            if(value2 == 0){
                System.out.println("Cant divide by 0");
            }

            return new LiteralInteger(value1 / value2);
        }catch (NumberFormatException e) {
            return new LiteralString("#VALUE");
        }
    }
}
