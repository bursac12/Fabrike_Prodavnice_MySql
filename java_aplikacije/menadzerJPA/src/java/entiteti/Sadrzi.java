/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import entiteti.Artikal;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Tic
 */
@Entity
public class Sadrzi implements Serializable {
    @Id
    private Integer ID;
    @ManyToOne
    @JoinColumn(name = "Prodavnica_ID", referencedColumnName = "ID")
    private Prodavnica SifP;
    
    @ManyToOne
    @JoinColumn(name = "Artikal_ID", referencedColumnName = "ID")
    private Artikal SifAr;
    
    private int kolicina;

    public Prodavnica getSifP() {
        return SifP;
    }

    public void setSifP(Prodavnica SifP) {
        this.SifP = SifP;
    }

    public Artikal getSifAr() {
        return SifAr;
    }

    public void setSifAr(Artikal SifA) {
        this.SifAr = SifA;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
    
    
    
}
