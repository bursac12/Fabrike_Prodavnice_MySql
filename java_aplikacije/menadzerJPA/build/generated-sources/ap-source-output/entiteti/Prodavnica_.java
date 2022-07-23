package entiteti;

import entiteti.Rezervacija;
import entiteti.Sadrzi;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-27T06:52:18")
@StaticMetamodel(Prodavnica.class)
public class Prodavnica_ { 

    public static volatile CollectionAttribute<Prodavnica, Rezervacija> rezervacija;
    public static volatile SingularAttribute<Prodavnica, String> Adresa;
    public static volatile CollectionAttribute<Prodavnica, Sadrzi> sadrzi;
    public static volatile SingularAttribute<Prodavnica, String> Tel;
    public static volatile SingularAttribute<Prodavnica, Integer> ID;
    public static volatile SingularAttribute<Prodavnica, String> Naziv;

}