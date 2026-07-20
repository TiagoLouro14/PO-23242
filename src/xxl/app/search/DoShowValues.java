package xxl.app.search;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import xxl.core.Calculator;
import xxl.core.Cell;
import xxl.core.Spreadsheet;
import xxl.core.User;
import xxl.core.Content;

// FIXME import classes

/**
 * Command for searching content values.
 */
class DoShowValues extends Command<Spreadsheet> {
  private Form _form;
  private User _user = User.getInstance();
  private Calculator _cal = new Calculator();

  DoShowValues(Spreadsheet receiver) {
    super(Label.SEARCH_VALUES, receiver);
    _form = new Form();
    addIntegerField("value", Message.searchValue());

  }
  
  @Override
  protected final void execute() {
    int _row = _cal.getSpreadsheet().getCutBuffer().getRows();
    int _col = _cal.getSpreadsheet().getCutBuffer().getColumns();
    int _value = integerField("value");

    for (int i = 0; i <= _row; i++) {
      for (int j = 0; j <= _col; j++) {
        Cell cell = _cal.getSpreadsheet().getCutBuffer().getCell(i, j);
        if(cell !=null) {
          if (cell.getExpression().matches("=\\d+;\\d+") && _value == cell.getContent().asInt()){
            _display.addLine(i+";"+j+"|"+cell);
          }
          else if(_value == cell.getContent().asInt()) {
            _display.addLine(i+";"+j+"|"+cell);
          }
        }
      }

    }
  }
}
