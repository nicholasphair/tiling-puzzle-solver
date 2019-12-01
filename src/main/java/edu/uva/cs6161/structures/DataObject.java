package edu.uva.cs6161.structures;

/**
 * Data Objects as defined by Knuth's paper.
 */
public class DataObject {
    private DataObject L;
    private DataObject R;
    private DataObject U;
    private DataObject D;
    private DataObject C;

    public DataObject() {
        this.L = this;
        this.R = this;
        this.U = this;
        this.D = this;
    }

    public DataObject(DataObject L, DataObject R, DataObject U, DataObject D, DataObject C) {
        this.L = L;
        this.R = R;
        this.U = U;
        this.D = D;
        this.C = C;
    }

    public DataObject getL() {
        return L;
    }

    public void setL(DataObject l) {
        L = l;
    }

    public DataObject getR() {
        return R;
    }

    public void setR(DataObject r) {
        R = r;
    }

    public DataObject getU() {
        return U;
    }

    public void setU(DataObject u) {
        U = u;
    }

    public DataObject getD() {
        return D;
    }

    public void setD(DataObject d) {
        D = d;
    }

    public DataObject getC() {
        return C;
    }

    public void setC(DataObject c) {
        C = c;
    }

}
