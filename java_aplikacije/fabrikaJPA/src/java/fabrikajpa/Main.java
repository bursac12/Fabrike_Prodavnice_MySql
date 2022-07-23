/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrikajpa;

import entiteti.Artikal;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import utils.Utils;


/**
 *
 * @author Tic
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "mojRed")
    private static Queue queue;    
    
    @Resource(lookup = "mojTopik")
    private static Topic topic;
    
    public static void main(String[] args) throws JMSException {
        // TODO code application logic here
        
        Scanner sc= new Scanner(System.in);
        int izbor, kolicina, sifra;
        double cena;
        String naziv;
        Artikal a = new Artikal();
        Utils ut = new Utils();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fabrikaJPAPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
              
        t.begin();
        
        while (true)
        {
            System.out.println("/*************************************************/");
            System.out.println("1. Proizvedi artikal ");
            System.out.println("2. Promeni cenu ");
            
            izbor=Integer.parseInt(sc.nextLine());
            
            if (izbor==0) break;
           
            switch (izbor) {
                case    1:  System.out.println("Unesite naziv artikla ");
                           
                            naziv = sc.nextLine();
                            a = ut.vratiSadrziPoTacnomNazivu(naziv, em);
                            if (a != null){
                                System.out.println("Unesite kolicinu ");
                                kolicina = Integer.parseInt(sc.nextLine());
                                System.out.println("Unesite sifru prodavnice kojoj saljete proizvode ");
                                sifra = Integer.parseInt(sc.nextLine());
                                
                                JMSContext context = connectionFactory.createContext();
                                JMSProducer producer1 = context.createProducer();
                                
                                TextMessage textMessage1 = context.createTextMessage();
                                textMessage1.setText("3 " + naziv + " " + kolicina);
                                if (sifra == 1){
                                   producer1.send(queue, textMessage1);
                                    System.out.println("Poslato u prodavnicu");
                                }
                                
                            }
                            else
                               System.out.println("Uneti artikal ne postoji");
                            break;
                    
                case    2:  System.out.println("Unesite naziv artikla ");
                            
                            naziv = sc.nextLine();
                            a = ut.vratiSadrziPoTacnomNazivu(naziv, em);
                            if (a!=null){
                                System.out.println("Unesite novu cenu");

                                cena = Double.parseDouble(sc.nextLine());
                                a.setCena(cena);
                                em.persist(a);
                                t.commit();
                                t.begin();
                                System.out.println("Cena je uspesno promenjena");
                            
                            //Vraca po nazivu
                             try {
                                
                                Destination destination = topic;
                                JMSContext context = connectionFactory.createContext();
                                JMSProducer producer1 = context.createProducer();
                                JMSProducer producer = context.createProducer();
                                
                                //Saljemo praznu
                                TextMessage textMessage1 = context.createTextMessage();
                                textMessage1.setText(" ");
                                producer1.send(queue, textMessage1);
                                
                                
                                String sadrzaj = "Naziv: " + naziv + " Cena " + cena;
                                TextMessage textMessage = context.createTextMessage();
                                textMessage.setText(sadrzaj);
                                producer.send(destination, textMessage);
                                System.out.println("Poslata je poruka sa sadrzajem: " + sadrzaj);

                            } catch (JMSException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                             }
                            else
                                System.out.println("Artikal ne postoji u bazi");
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
