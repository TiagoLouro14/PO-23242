package xxl.core;

import java.io.Serial;
import java.io.Serializable;

public class Mul extends BinaryFunction implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;
    public Mul() {
    }

    public Mul(Content arg0, Content arg1) {
        super(arg1,arg0);

    }

    @Override
    protected String getFunctionName() {
        return "MUL";
    }


    /**
     * @return
     */
    @Override
    protected Literal compute() {
        // Obtém os valores dos argumentos usando _arg1 e _arg2 (definidos na classe pai)
        try{
            int value1 = _arg1.value().asInt();
            int value2 = _arg0.value().asInt();


            return new LiteralInteger(value1 * value2);
        }catch (NumberFormatException e) {
            return new LiteralString("#VALUE");
        }
    }
}
