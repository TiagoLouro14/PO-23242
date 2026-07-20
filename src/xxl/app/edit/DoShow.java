package xxl.app.edit;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Spreadsheet;
import xxl.core.Calculator;
import xxl.core.Cell;


import java.util.ArrayList;
import java.util.List;

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {
  private Form _form;
  private Calculator _cal = new Calculator();

  DoShow(Spreadsheet receiver) {
    super(Label.SHOW, receiver);
    _form = new Form();
    addStringField("coords", Message.address());
  }

  @Override
  protected final void execute() throws CommandException {

      String _string = stringField("coords");
      String[] _partes = _string.split(":");
      List<Integer> _cordInt = new ArrayList<>();


      for (String parte : _partes) {
          String[] numeros = parte.split(";");
          for (String numero : numeros) {
              int valor = Integer.parseInt(numero);
              _cordInt.add(valor);
          }
      }
    /*
    _cordInt.get(0) -> line of cell
    _cordInt.get(1) -> column of cell
     */
      int _rowsinthespread = _cal.getSpreadsheet().getCutBuffer().getRows();
      int _colsinthespread = _cal.getSpreadsheet().getCutBuffer().getColumns();
      if (_cordInt.size() == 2) { // 1 cell
          int _row = _cordInt.get(0);
          int _col = _cordInt.get(1);
          Cell cell = _cal.getSpreadsheet().getCutBuffer().getCell(_row, _col);
          if ((_row > _rowsinthespread) || (_col > _colsinthespread)){
              _display.add("Visualizar: Operação inválida: A gama " + "'" + _string + "'" + " é inválida.");
          }
          if (cell != null) {

              // if cell is a reference to a function
              if(cell.getExpression().matches("=(\\d+;\\d+|\\d+:\\d+)")
                      && cell.toString().equals("")){
                  _display.addLine(_row + ";" + _col + "|" + "#VALUE" + cell.getExpression());
              }else if(cell.getExpression().matches(".*=(AVERAGE|COALESCE|PRODUCT|CONCAT)\\(\\d+;\\d+(:\\d+;\\d+)?\\).*")){
                  _display.addLine(_row + ";" + _col + "|" + cell + cell.getExpression());
              }
              else if(cell.getExpression().matches(".*=(ADD|SUB|MUL|DIV|CONCAT|PRODUCT|COALESCE|AVERAGE)\\((\\d+([,;]\\d+|:[,;]\\d+)?)?(,[,;]?\\d+([,;]\\d+|:[,;]\\d+)?)*\\).*")
                ){
                  _display.addLine(_row + ";" + _col + "|" + cell + cell.getExpression());
              }
              else if(!cell.getContent().toString().isEmpty()){
              _display.addLine(_row + ";" + _col + "|" + cell );
              }
          }
          else {
              _display.addLine(_row + ";" + _col + "|");
          }


      } else if (_cordInt.size() == 4) { // gamma of cell
          int _startRow = _cordInt.get(0);
          int _startCol = _cordInt.get(1);
          int _endRow = _cordInt.get(2);
          int _endCol = _cordInt.get(3);

          // if its only one cell but search within a gama
          if (_startRow == _startCol && _startRow == _endRow && _startCol == _endCol) {
              _display.addLine(_endCol + ";" + _endCol + "|" + _cal.getSpreadsheet().getCutBuffer().getCell(_endRow, _endCol));
          }else {

              if ((_endCol > _colsinthespread) || (_endRow > _rowsinthespread) ||
                      !(((_startRow == _endRow) && (_startCol != _endCol)) // if not a horizontal search
                              || ((_startRow != _endRow) && (_startCol == _endCol))))  // if not a vertical search
                  _display.add("Visualizar: Operação inválida: A gama " + "'" + _string + "'" + " é inválida.");
              else {
                  for (int i = _startRow; i <= _endRow; i++) {
                      for (int j = _startCol; j <= _endCol; j++) {
                          Cell cell = _cal.getSpreadsheet().getCutBuffer().getCell(i, j);
                          if (cell != null) {

                              // if cell is a reference to a function
                              if(cell.getExpression().matches(".*=(ADD|SUB|MUL|DIV|CONCAT|PRODUCT|COALESCE|AVERAGE)\\((\\d+([,;]\\d+|:[,;]\\d+)?)?(,[,;]?\\d+([,;]\\d+|:[,;]\\d+)?)*\\).*")){
                                  _display.addLine(i + ";" + j + "|" + cell + cell.getExpression());

                                  // if a cell has only a literal as value
                              }else if(!cell.getContent().toString().isEmpty()) {
                                  _display.addLine(i + ";" + j + "|" + cell);
                              }

                          } else {
                              _display.addLine(""+i+";"+j);
                          }
                      }
                  }
              }
          }
      }
  }

}
