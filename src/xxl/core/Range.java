package xxl.core;

import xxl.core.exception.UnrecognizedEntryException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Range extends Spreadsheet{
    private int _beginrow;
    private int _begincolumn;
    private int _endrow;
    private int _endcolumn;
    private Calculator _c = new Calculator();
    private Set<Integer> _cellsnumbers = new HashSet<>();


    public Range(int beginrow,int begincolumn,int endrow,int endcolumn) throws UnrecognizedEntryException {
        _beginrow = beginrow;
        _begincolumn = begincolumn;
        _endrow = endrow;
        _endcolumn = endcolumn;
    }

    Set<Integer> getCellsContent() {
        for (int i = _beginrow; i <= _endrow; i++) {
            for (int j = _begincolumn; j <= _endcolumn; j++) {
                Cell cell = _c.getSpreadsheet().getCutBuffer().getCell(i, j);
                if (cell != null) {
                    // Se a gama é horizontal ou vertical, adiciona o conteúdo da célula
                    if ((_beginrow == _endrow && _begincolumn != _endcolumn) ||
                            (_beginrow != _endrow && _begincolumn == _endcolumn)) {
                        _cellsnumbers.add(cell.getContent().asInt());
                    } else {
                        // Se não, adiciona 1 para cada célula na gama
                        _cellsnumbers.add(1);
                    }
                }
            }
        }
        return _cellsnumbers;
    }


    List<Cell> getCells(){
        return null;
    }
}