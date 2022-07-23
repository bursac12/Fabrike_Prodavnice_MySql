

import entiteti.Artikal;
import entiteti.Prodavnica;
import entiteti.Promet;
import entiteti.Rezervacija;
import entiteti.Sadrzi;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import utils.Utils;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.TextMessage;
import javax.jms.Message;
/**
 *
 * @author Tic
 */
public class Main {

    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static javax.jms.ConnectionFactory connectionFactory;
    
    @Resource(lookup = "mojRed")
    private static javax.jms.Queue queue;
    
    @Resource(lookup = "mojRed1")
    private static javax.jms.Queue queue1;
    
    
    
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue1);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekatJPAPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
              
        t.begin();
        
        Artikal a = new Artikal();
        Utils ut = new Utils();
        Sadrzi s = new Sadrzi();
        Promet p = new Promet();
        Promet p1 = new Promet();
        Collection<Artikal> artikli = new ArrayList<Artikal>();
        Collection<Sadrzi> sadrzi = new ArrayList<Sadrzi>();
        
        Scanner sc= new Scanner(System.in);
        int izbor;
        String tip;
        String naziv;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int sifraProd = 1;
        Prodavnica prod = new Prodavnica();
        prod = ut.vratiProdavnicu(sifraProd,em);
        while (true)
        {
            System.out.println("/*************************************************/");
            System.out.println("1. Pretraga po tipu artikla ");
            System.out.println("2. Pretraga po nazivu artikla ");
            System.out.println("3. Proveri stanje artikla ");
            System.out.println("4. Prodaj artikal ");
            System.out.println("5. Proveri druge prodavnice");
            System.out.println("6. Rezervisi artikal");
            System.out.println("7. Prodaj rezervisan artikal");
            System.out.println("Unesite vas izbor (0 za kraj): ");
            System.out.println("/*************************************************/");
            
            izbor=Integer.parseInt(sc.nextLine());
            
            if (izbor==0) break;
           
            switch (izbor) {
                case    1:  System.out.println("Unesite tip artikla ");
                           
                            tip=sc.nextLine();
                                    // Vraca za nasu prodavnicu
                            EntityManager em1 = emf.createEntityManager();
                            EntityTransaction t1 = em1.getTransaction();
              
                            t1.begin();
                            sadrzi = ut.nadjiArtikalPoTipu(tip,em1);
                            if (sadrzi.isEmpty()){
                                System.out.println("Nema trazenog tipa artikla u prodavnici");
                            }
                            else{
                            for (Sadrzi sa : sadrzi) {
                              System.out.println("    Naziv: " + sa.getSifAr().getNaziv().toString() + " Tip: " + sa.getSifAr().getTip() + " Cena: " + sa.getSifAr().getCena()+ " Kolicina: " + sa.getKolicina());
                                                    }
                            }
                            em1.close();
                            break;
                    
                case    2:  System.out.println("Unesite naziv artikla ");
                            
                            naziv=sc.nextLine();
                            EntityManager em2 = emf.createEntityManager();
                            EntityTransaction t2 = em2.getTransaction();
                            t2.begin();
                            //Vraca po nazivu
                            sadrzi = ut.nadjiArtikalPoNazivu(naziv,em2);
                            if (sadrzi.isEmpty()){
                                System.out.println("Nema trazenog artikla u prodavnici");
                            }
                            else {
                                for (Sadrzi sa : sadrzi) {
                                  System.out.println("    Naziv: " + sa.getSifAr().getNaziv().toString() + " Tip: " + sa.getSifAr().getTip() + " Cena: " + sa.getSifAr().getCena()+ " Kolicina: " + sa.getKolicina());
                                                        }
                            }
                            em2.close();
                            break;    
                            
                case    3:  System.out.println("Unesite naziv artikla za proveru ");
                            naziv=sc.nextLine();
                            
                            s = ut.vratiSadrziPoTacnomNazivu(naziv, em);
                            if (s != null){
                                try {

                                    TextMessage textMessage = context.createTextMessage();
                                    textMessage.setText("1 "+naziv);
                                    producer.send(queue, textMessage);

                                }   catch (JMSException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                } 

                                Message message = consumer.receive();
                                if(message instanceof TextMessage){
                                    try {
                                        TextMessage textMessage = (TextMessage) message;
                                        System.out.println("/*************************************************/");
                                        System.out.println("Poruka od menadzera: "+textMessage.getText());
                                        System.out.println("/*************************************************/");
                                    } catch (JMSException ex) {
                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                
                            }
                            else System.out.println("Artikal ne postoji, pokusajte ponovo");
                            break;    
                            
                case    4:  System.out.println("Unesite naziv artikla za prodaju ");
                            EntityManager em3 = emf.createEntityManager();
                            EntityTransaction t3 = em3.getTransaction();
                            t3.begin();
                            naziv=sc.nextLine();
                            System.out.println("Unesite kolicinu");
                            int kol = Integer.parseInt(sc.nextLine());
                            s = ut.vratiSadrziPoTacnomNazivu(naziv, em3);
                            if (s == null)
                                System.out.println("Nema trazenog proizvoda");
                            else{
                                if (s.getKolicina() < kol)
                                    System.out.println("Nema dovoljne kolicine proizvoda");
                                else{
                                    //Smanji kolicinu
                                    s.setKolicina(s.getKolicina() - kol);
                                    Date date = new Date();
                                    //Dodaj u promet
                                    try {
                                        p = ut.vratiPrometZaDatum(sdf.parse(sdf.format(date)), em3);
                                    } catch (ParseException ex) {
                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    if (p !=null){
                                      p.setIznos(p.getIznos() + kol*s.getSifAr().getCena());
                                      int trenutnaKol = p.getKolicina();
                                      int ukupnaKol = trenutnaKol + kol;
                                      p.setKolicina( ukupnaKol);
                                      p.setSifP(prod);
                                      em3.persist(p);
                                    }
                                    else{
                                        p1.setDatum(sdf.parse(sdf.format(date)));
                                        p1.setIznos(kol*s.getSifAr().getCena());
                                        p1.setKolicina(kol);
                                        p1.setSifP(prod);
                                        em3.persist(p1);
                                    }
                                    em3.persist(s);
                                    t3.commit();  
                                    System.out.println("Prodato");
                                    
                                }
                            }
                                em3.close();
                                break;    
                case    5: System.out.println("Unesite naziv artikla");
                            naziv=sc.nextLine();
                           System.out.println("Unesite kolicinu");
                            int kolicina = Integer.parseInt(sc.nextLine());
                            sadrzi = ut.nadjiArtikalPoNazivuSveProd(naziv,kolicina,em);
                            if (sadrzi.isEmpty())
                                System.out.println("Ni u jednoj prodavnici nema trazenog artikla u dovoljnoj kolicini");
                            else{
                                for (Sadrzi sa : sadrzi) {
                                   // System.out.println("trt");;
                                  System.out.println("    Prodavnica: " + sa.getSifP().getNaziv().toString() + "    Naziv: " + sa.getSifAr().getNaziv().toString() + " Tip: " + sa.getSifAr().getTip() + " Cena: " + sa.getSifAr().getCena()+ " Kolicina: " + sa.getKolicina());
                                }
                            }
                            break;
                            //Vraca kad nema u nasoj 
                case    6:  System.out.println("Unesite naziv artikla");
                            naziv=sc.nextLine();
                            System.out.println("Unesite kolicinu");
                            int kolicina1 = Integer.parseInt(sc.nextLine());
                            System.out.println("Unesite prodavnicu u kojoj se preuzima proizvod");
                            String prod1 = sc.nextLine();
                            System.out.println("Ime kupca");
                            String ime = sc.nextLine();
                            
                            System.out.println("Tel kupca");
                            String tel  = sc.nextLine();
                            
                            TextMessage textMessage = context.createTextMessage();
                            {
                                try {
                                    textMessage.setText("2 "+naziv+" "+kolicina1 + " " + prod1 + " " + ime + " " + tel);
                                } catch (JMSException ex) {
                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            producer.send(queue, textMessage);
                            System.out.println("Rezervacija je prosledjena menadzeru na obradu");
                            break;
                case    7: System.out.println("Unesite ime kupca");
                           String kupac = sc.nextLine();
                           System.out.println("Unesite telefon kupca");
                           String telefon = sc.nextLine();
                           EntityManager em4 = emf.createEntityManager();
                           EntityTransaction t4 = em4.getTransaction();
                           t4.begin();
                           Rezervacija r = ut.nadjiRezervaciju(kupac, telefon, em4);
                           
                           if (r != null){
                                System.out.println("Rezervisan proizvod " + r.getSifAr().getNaziv() + ", kolicina " + r.getKolicina());

                                s = ut.vratiSadrziPoTacnomNazivu(r.getSifAr().getNaziv(), em4);
                                //Smanji kolicinu
                                         s.setKolicina(s.getKolicina() - r.getKolicina());
                                         Date date = new Date();
                                         //Dodaj u promet
                                         try {
                                             p = ut.vratiPrometZaDatum(sdf.parse(sdf.format(date)), em4);
                                         } catch (ParseException ex) {
                                             Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                         }
                                         if (p !=null){
                                           p.setIznos(p.getIznos() + r.getKolicina()*s.getSifAr().getCena());
                                           int trenutnaKol = p.getKolicina();
                                           int ukupnaKol = trenutnaKol + r.getKolicina();
                                           p.setKolicina( ukupnaKol);
                                           p.setSifP(prod);
                                           em4.persist(p);
                                         }
                                         else{
                                             p1.setDatum(sdf.parse(sdf.format(date)));
                                             p1.setIznos(r.getKolicina()*s.getSifAr().getCena());
                                             p1.setKolicina(r.getKolicina());
                                             p1.setSifP(prod);
                                             em4.persist(p1);
                                         }
                                         em4.persist(s);
                                         ut.obrisiRezervaciju(kupac, telefon, em4);
                                         t4.commit();  
                                         System.out.println("Prodato");


                                em4.close();
                           }
                           else
                               System.out.println("Rezervacija ne postoji");
                           break;
                    
                default :   System.out.print("Greska pri unosu!");
                            break;
            }
                           
        }       
        
      /*  ut.prodajProizvod("yama", 2, em);
        t.commit();  */
        
        
        em.close();
        emf.close();
    }
    
}
