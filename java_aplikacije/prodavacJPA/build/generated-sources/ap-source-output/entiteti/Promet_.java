package entiteti;

import entiteti.Prodavnica;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-27T00:43:39")
@StaticMetamodel(Promet.class)
public class Promet_ { 

    public static volatile SingularAttribute<Promet, Date> datum;
    public static volatile SingularAttribute<Promet, Double> iznos;
    public static volatile SingularAttribute<Promet, Prodavnica> SifP;
    public static volatile SingularAttribute<Promet, Integer> kolicina;
    public static volatile SingularAttribute<Promet, Integer> ID;

}