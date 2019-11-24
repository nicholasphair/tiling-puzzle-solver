package edu.uva.cs6161.objects;

public abstract class DanceObject {
    private DanceObject L;
    private DanceObject R;
    private DanceObject U;
    private DanceObject D;
    private DanceObject C;

    public DanceObject() {

    }

    public DanceObject(DanceObject L, DanceObject R, DanceObject U, DanceObject D, DanceObject C) {
        this.L = L;
        this.R = R;
        this.U = U;
        this.D = D;
        this.C = C;
    }

    public DanceObject getL() {
        return L;
    }

    public void setL(DanceObject l) {
        L = l;
    }

    public DanceObject getR() {
        return R;
    }

    public void setR(DanceObject r) {
        R = r;
    }

    public DanceObject getU() {
        return U;
    }

    public void setU(DanceObject u) {
        U = u;
    }

    public DanceObject getD() {
        return D;
    }

    public void setD(DanceObject d) {
        D = d;
    }

    public DanceObject getC() {
        return C;
    }

    public void setC(DanceObject c) {
        C = c;
    }
}
