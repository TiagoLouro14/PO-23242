package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Reference extends Content implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;
    private int _row;
    private int _column;
    private Calculator _calculator;

    public Reference() {
    }

    public Reference(int row, int column, Calculator calculator) {
        _row = row;
        _column = column;
        _calculator = calculator;
    }

    @Override
    public String toString() {
        Cell cell = _calculator.getSpreadsheet().getCutBuffer().getCell(_row, _column);
        if (cell == null) {
            return "";
        }
        return String.format("%s=%d;%d",cell.getContent().asString(), _row, _column);
    }

    @Override
    Literal value() {
        Cell cell = _calculator.getSpreadsheet().getCutBuffer().getCell(_row, _column);
        if (cell == null) {
            return new LiteralString(""); // Ou qualquer valor padrão desejado para referências vazias
        }
        return cell.value();
    }

    public boolean isEmpty() {
        Cell referencedCell = _calculator.getSpreadsheet().getCutBuffer().getCell(_row, _column);
        return referencedCell == null;
    }
}



