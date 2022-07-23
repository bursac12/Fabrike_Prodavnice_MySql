package entiteti;

import entiteti.Rezervacija;
import entiteti.Sadrzi;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-27T06:52:18")
@StaticMetamodel(Artikal.class)
public class Artikal_ { 

    public static volatile CollectionAttribute<Artikal, Rezervacija> rezervacija;
    public static volatile CollectionAttribute<Artikal, Sadrzi> sadrzi;
    public static volatile SingularAttribute<Artikal, String> Tip;
    public static volatile SingularAttribute<Artikal, Integer> ID;
    public static volatile SingularAttribute<Artikal, Double> cena;
    public static volatile SingularAttribute<Artikal, String> Naziv;

}