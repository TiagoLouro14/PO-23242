package xxl.core;

import java.io.Serializable;


public class TwoDimensionalArrayStorage implements CellStorage,Serializable {

    private static final long serialVersionUID = 202308312359L;
    private Cell[][] _cells;
    private int _rows;
    private int _columns;

    public TwoDimensionalArrayStorage() {
    }

    public TwoDimensionalArrayStorage(int rows, int cols) {
        _rows = rows;
        _columns = cols;
        _cells = new Cell[rows+1][cols+1];
    }

    public Cell getCell(int row, int col) {
        return _cells[row][col];
    }

    @Override
    public void setCell(int row, int col, Cell cell) {
        _cells[row][col] = cell;
    }
    public Integer getSize(){
        return _cells.length;
    }
    public int getRows(){
        return _rows;
    }
    public int getColumns(){
        return _columns;
    }

    public Cell[][] getAllCell(){
        return _cells;
    }

}