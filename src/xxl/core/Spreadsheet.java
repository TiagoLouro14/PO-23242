package xxl.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import xxl.core.exception.UnrecognizedEntryException;

public class Spreadsheet extends Calculator implements Serializable {

  private static final long serialVersionUID = 202308312359L;
  private Cell _c;
  private TwoDimensionalArrayStorage _cells;
  private Range _range;



  public Spreadsheet() {
  }

  public Spreadsheet(TwoDimensionalArrayStorage cells) throws UnrecognizedEntryException {
    _cells = cells;
  }


  public TwoDimensionalArrayStorage getCutBuffer(){
    return _cells;
  }


  public void copy(String range) {
    // Implement copy logic here
  }



  public void clear(String range) {
    // Implement clear logic here
  }


  public void addUser(String u) {
    // Implement user addition logic here
  }

  public void insertContent(int row, int column, Content contentSpecification) throws UnrecognizedEntryException {
    Cell cell = new Cell(contentSpecification);
    _cells.setCell(row, column, cell);
  }





  public Range createRange(String range) {
    String[] rangeCoordinates = range.split("[:;]");
    int firstRow = Integer.parseInt(rangeCoordinates[0]);
    int firstColumn = Integer.parseInt(rangeCoordinates[1]);
    int lastRow = Integer.parseInt(rangeCoordinates[2]);
    int lastColumn = Integer.parseInt(rangeCoordinates[3]);

    // Check if coordinates are valid
    // If valid, create and return a new Range object
    if (isValidRange(firstRow, firstColumn, lastRow, lastColumn)) {
      try {
        return new Range(firstRow,firstColumn,lastRow,lastColumn);
      } catch (UnrecognizedEntryException e) {
        throw new RuntimeException(e);
      }
    } else {
      // Handle invalid range
      return null;
    }
  }


  private boolean isValidRange(int firstRow, int firstColumn, int lastRow, int lastColumn) {
    // Verificar se as coordenadas da faixa são válidas
    // Retorna true se a faixa for válida, false caso contrário

    // Verifica se as coordenadas são não negativas
    if (firstRow < 0 || firstColumn < 0 || lastRow < 0 || lastColumn < 0) {
      return false;
    }

    // Verifica se a primeira linha é menor ou igual à última linha
    // e se a primeira coluna é menor ou igual à última coluna
    if (firstRow <= lastRow && firstColumn <= lastColumn) {
      return true;
    } else {
      return false;
    }
  }


}