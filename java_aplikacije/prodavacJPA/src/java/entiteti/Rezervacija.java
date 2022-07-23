/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Tic
 */
@Entity
public class Rezervacija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @ManyToOne
    @JoinColumn(name = "Prodavnica_ID", referencedColumnName = "ID")
    private Prodavnica SifP;
    
    @ManyToOne
    @JoinColumn(name = "Artikal_ID", referencedColumnName = "ID")
    private Artikal SifAr;
    
    private int kolicina;
    @Temporal(TemporalType.DATE)
    private Date datum;
    private String ime;
    private String tel;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Prodavnica getSifP() {
        return SifP;
    }

    public void setSifP(Prodavnica SifP) {
        this.SifP = SifP;
    }

    public Artikal getSifAr() {
        return SifAr;
    }

    public void setSifAr(Artikal SifAr) {
        this.SifAr = SifAr;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
    
}
