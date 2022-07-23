/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import entiteti.Promet;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Tic
 */
@Entity
public class Prodavnica implements Serializable {
    
    @Id
    private Integer ID;
    private String Naziv;
    private String Adresa;
    private String Tel;

    @OneToMany(mappedBy = "SifP", cascade = CascadeType.ALL)
    private Collection<Promet> promet;

    @OneToMany(mappedBy = "SifP", cascade = CascadeType.ALL)
    private Collection<Sadrzi> sadrzi;

    public Collection<Sadrzi> getSadrzi() {
        return sadrzi;
    }

    public void setSadrzi(Collection<Sadrzi> sadrzi) {
        this.sadrzi = sadrzi;
    }
    
    
    public Collection<Promet> getPromet() {
        return promet;
    }

    public void setPromet(Collection<Promet> promet) {
        this.promet = promet;
    }
    
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String Naziv) {
        this.Naziv = Naziv;
    }

    public String getAdresa() {
        return Adresa;
    }

    public void setAdresa(String Adresa) {
        this.Adresa = Adresa;
    }

    public String getTelefon() {
        return Tel;
    }

    public void setTelefon(String Telefon) {
        this.Tel = Telefon;
    }
    
    
        
}
