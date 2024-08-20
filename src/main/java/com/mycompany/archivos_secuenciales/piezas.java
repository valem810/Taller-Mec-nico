package com.mycompany.archivos_secuenciales;

/**
 *
 * @author brand
 */

public class piezas {

    private int piz;
    private String descrp;
    private int stock;

    public int getPiz() {
        return piz;
    }

    public String getDescrp() {
        return descrp;
    }

    public int getStock() {
        return stock;
    }

    public void SetPiz(int p) {
        this.piz = p;
    }

    public void SetDescrp(String d) {
        this.descrp = d;
    }

    public void SetStock(int s) {
        this.stock = s;
    }
}
