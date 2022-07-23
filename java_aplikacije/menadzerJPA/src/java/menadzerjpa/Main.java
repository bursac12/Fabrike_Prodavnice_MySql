/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menadzerjpa;

import entiteti.Artikal;
import entiteti.Prodavnica;
import entiteti.Rezervacija;
import entiteti.Sadrzi;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
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

     @Resource(lookup = "jms/__defaultConnectionFactory")
    private static javax.jms.ConnectionFactory connectionFactory;
    
    @Resource(lookup = "mojRed")
    private static javax.jms.Queue queue;
    
    @Resource(lookup = "mojRed1")
    private static javax.jms.Queue queue1;
    
    @Resource(lookup = "mojTopik")
    private static Topic topic;
    
    public static void main(String[] args) throws ParseException {
       
       Artikal a = new Artikal();
       Utils ut =new Utils();
       Sadrzi s =  new Sadrzi();
       JMSContext context = connectionFactory.createContext();
       JMSConsumer consumer = context.createConsumer(queue);
       JMSProducer producer = context.createProducer();
       JMSConsumer consumerT = context.createConsumer(topic);
       String obr,poruka=""; 
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       
             
       while(true){
        Message message = consumer.receive();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("menadzerJPAPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();              
        t.begin();
         
        if(message instanceof TextMessage){
            try {               
                TextMessage textMessage = (TextMessage) message;
                poruka=textMessage.getText();
                System.out.println(textMessage.getText());
                               
            } catch (JMSException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            int tip = Character.getNumericValue(poruka.charAt(0));
            
            switch(tip) {
                case 1:
                    try {
                        TextMessage textMessage = context.createTextMessage();
                        double ran = Math.random();
                        String m;
                        if (ran > 0.7) { m="Otpakovano"; }
                        else  { m="Zapakovano"; } 
                        textMessage.setText(m);
            
                        producer.send(queue1, textMessage);
                    } catch (JMSException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 2:
                     String [] parts1  = poruka.split(" ");
                     String naziv1 = parts1[1];
                     int kolicina1 = Integer.parseInt(parts1[2]);
                     int prodavnica = Integer.parseInt(parts1[3]);
                     String ime = parts1[4];
                     String tel = parts1[5];
                     if (prodavnica == 1) {
                        Prodavnica p = ut.vratiProdavnicu(prodavnica, em);
                        Rezervacija r = new Rezervacija();
                        Artikal ar = ut.vratiArtikalPoTacnomNazivu(naziv1,em);
                        Date d = new Date();
                        Date d1 = sdf.parse(sdf.format(d));
                        
                        r.setSifAr(ar);
                        r.setSifP(p);
                        r.setKolicina(kolicina1);
                        r.setIme(ime);
                        r.setTel(tel);
                        r.setDatum(d1);
                        
                        em.persist(r);
                        t.commit();
                        t.begin();
                     }
                case 3:
                      String [] parts  = poruka.split(" ");
                      String naziv = parts[1];
                      int kolicina = Integer.parseInt(parts[2]);
                      System.out.println("Naziv " + parts[1] + " Kolicina " + parts[2]);
                      s = ut.vratiSadrziPoTacnomNazivu(naziv,em);
                      if ( s != null){
                          s.setKolicina(s.getKolicina() + kolicina);
                          em.persist(s);
                          t.commit();
                          t.begin();
                      }
                default: 
                    break;
            }
            
            
            }
         
         
         try {  
                
                Message message1 = consumerT.receive();
                if(message1 instanceof TextMessage){
                    String msg = ((TextMessage)message1).getText();
                    String[] parts = msg.split(" ");
                    String naziv = parts[1];
                    double cena = Double.parseDouble(parts[3]);
                    
                    a = ut.vratiArtikalPoTacnomNazivu(naziv, em);
                    if (a != null){
                        a.setCena(cena);
                        em.persist(a);
                        t.commit();
                        t.begin();
                    }
                    
                    System.out.println("Artikal: " + parts[1] + " Cena: " + parts[3]);
                }
        } catch (JMSException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.close();
        emf.close();
        }
        
  
    }
    
}
