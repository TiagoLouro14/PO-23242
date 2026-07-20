package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product extends IntervalFunction implements Serializable {
    private int sum = 1;
    @Serial
    private static final long serialVersionUID = 202308312359L;

    public Product(Range range) {
        super(range);
    }

    public Product() {
    }

    @Override
    protected Literal compute() {
        try {
            int size = _range.getCellsContent().size();
            for (int c : _range.getCellsContent()) {
                sum *= c;

            }
            return new LiteralInteger(sum);
        } catch (NumberFormatException e) {
            return new LiteralString("#VALUE");
        }
    }

    @Override
    protected String getFunctionName() {
        return "PRODUCT";
    }
}
