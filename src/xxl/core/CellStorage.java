package xxl.core;

public interface CellStorage {
    Object getCell(int row,int col);
    void setCell(int row,int col,Cell cell);
}
