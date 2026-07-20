package xxl.app.search;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Calculator;
import xxl.core.Cell;
import xxl.core.Spreadsheet;
import xxl.core.User;
// FIXME import classes

/**
 * Command for searching function names.
 */
class DoShowFunctions extends Command<Spreadsheet> {
  private User _user = User.getInstance();
  private Calculator _cal = new Calculator();

  DoShowFunctions(Spreadsheet receiver) {
    super(Label.SEARCH_FUNCTIONS, receiver);
    addStringField("string", Message.searchFunction());

  }

  @Override
  protected final void execute() {

    String _string = stringField("string");
    int _row = _cal.getSpreadsheet().getCutBuffer().getRows();
    int _col = _cal.getSpreadsheet().getCutBuffer().getColumns();
    for (int i = 0; i <= _row; i++) {
      for (int j = 0; j <= _col; j++) {
        Cell cell = _cal.getSpreadsheet().getCutBuffer().getCell(i, j);
        if (cell != null) {
          if (_string.matches(cell.getContent().toString()) && cell.getContent().toString().matches(".*(ADD|MUL|DIV|SUB|PRODUCT|COALESCE|CONCAT|AVERAGE).*")) {
            _display.addLine(i + ";" + j+ "|" + cell + cell.getExpression());
          }
        }
      }
    }

  }
}