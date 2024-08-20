
package com.mycompany.archivos_secuenciales;

/**
 *
 * @author brand
 */

public class reparaciones {

    private int id_ve;
    private int id_pi;
    private int id_re;
    private int contrl;
    private String falla;
    private String fecha_e;
    private String fecha_s;

    public int getId_ve() {
        return id_ve;
    }

    public void setId_ve(int v) {
        this.id_ve = v;
    }

    public int getId_pi() {
        return id_pi;
    }

    public void setId_pi(int p) {
        this.id_pi = p;
    }

    public int getId_re() {
        return id_re;
    }

    public void setId_re(int r) {
        this.id_re = r;
    }

    public int getId_contrl() {
        return contrl;
    }

    public void setId_contrl(int c) {
        this.contrl = c;
    }

    public String getFalla() {
        return falla;
    }

    public void setFalla(String fa) {
        this.falla = fa;
    }

    public String getFecha_e() {
        return fecha_e;
    }

    public void setFecha_e(String fe) {
        this.fecha_e = fe;
    }

    public String getFecha_s() {
        return fecha_s;
    }

    public void setFecha_s(String fs) {
        this.fecha_s = fs;
    }
}
