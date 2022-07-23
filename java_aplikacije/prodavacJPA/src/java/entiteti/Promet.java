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
public class Promet implements Serializable {
    @Id 
    private Integer ID;
    @ManyToOne
    @JoinColumn(name = "Prodavnica_ID", referencedColumnName = "ID")
    private Prodavnica SifP;

    @Temporal(TemporalType.DATE)
    private Date datum;
    
    private int kolicina;
    private double iznos;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Prodavnica getSifP() {
        return SifP;
    }

    public void setSifP(Prodavnica SifP) {
        this.SifP = SifP;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }
    
    
}
