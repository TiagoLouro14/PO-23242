package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Average extends IntervalFunction implements Serializable {
    private int sum = 0;

    @Serial
    private static final long serialVersionUID = 202308312359L;

    public Average(Range range) {
        super(range);
    }

    public Average() {
    }


    @Override
    protected Literal compute() {
        try {
            int size = _range.getCellsContent().size();
            for (int c : _range.getCellsContent()) {
                sum += c;

            }
            return new LiteralInteger(sum / size);
        } catch (NumberFormatException e) {
            return new LiteralString("#VALUE");
        }
    }


    @Override
    protected String getFunctionName() {
        return "AVERAGE";
    }
}
