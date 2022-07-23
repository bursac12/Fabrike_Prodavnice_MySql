package entiteti;

import entiteti.Artikal;
import entiteti.Prodavnica;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-27T07:17:43")
@StaticMetamodel(Rezervacija.class)
public class Rezervacija_ { 

    public static volatile SingularAttribute<Rezervacija, Date> datum;
    public static volatile SingularAttribute<Rezervacija, String> ime;
    public static volatile SingularAttribute<Rezervacija, Artikal> SifAr;
    public static volatile SingularAttribute<Rezervacija, Prodavnica> SifP;
    public static volatile SingularAttribute<Rezervacija, Integer> kolicina;
    public static volatile SingularAttribute<Rezervacija, String> tel;
    public static volatile SingularAttribute<Rezervacija, Integer> ID;

}