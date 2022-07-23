/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

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
    private Collection<Sadrzi> sadrzi;
    
    @OneToMany(mappedBy = "SifP", cascade = CascadeType.ALL)
    private Collection<Rezervacija> rezervacija;

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public Collection<Rezervacija> getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Collection<Rezervacija> rezervacija) {
        this.rezervacija = rezervacija;
    }
    
    

    public Collection<Sadrzi> getSadrzi() {
        return sadrzi;
    }

    public void setSadrzi(Collection<Sadrzi> sadrzi) {
        this.sadrzi = sadrzi;
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
