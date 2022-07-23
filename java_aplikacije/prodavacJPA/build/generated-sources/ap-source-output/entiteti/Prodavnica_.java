package entiteti;

import entiteti.Promet;
import entiteti.Sadrzi;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-27T05:09:41")
@StaticMetamodel(Prodavnica.class)
public class Prodavnica_ { 

    public static volatile SingularAttribute<Prodavnica, String> Adresa;
    public static volatile CollectionAttribute<Prodavnica, Promet> promet;
    public static volatile CollectionAttribute<Prodavnica, Sadrzi> sadrzi;
    public static volatile SingularAttribute<Prodavnica, String> Tel;
    public static volatile SingularAttribute<Prodavnica, Integer> ID;
    public static volatile SingularAttribute<Prodavnica, String> Naziv;

}