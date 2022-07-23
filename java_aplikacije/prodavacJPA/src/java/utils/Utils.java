/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entiteti.Artikal;
import entiteti.Prodavnica;
import entiteti.Promet;
import entiteti.Rezervacija;
import entiteti.Sadrzi;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;


/**
 *
 * @author Tic
 */


public class Utils {
    
    public static List nadjiArtikalPoNazivu(String naziv, EntityManager em){
     try {
        return em.createQuery("SELECT p FROM Sadrzi p JOIN p.SifAr s WHERE s.Naziv LIKE CONCAT('%',:naziv,'%') AND p.SifP.ID = 1").setParameter("naziv", naziv).getResultList();
     }
     catch (NoResultException e) {
       return null;
     } 
     catch (NonUniqueResultException e) {
       return null;
     }
    }    
  
    public static List nadjiArtikalPoTipu(String tip, EntityManager em){
     try {
        return em.createQuery("SELECT p FROM Sadrzi p JOIN p.SifAr s WHERE p.SifP.ID = 1 AND s.Tip LIKE CONCAT('%',:tip,'%')").setParameter("tip", tip).getResultList();
     }
     catch (NoResultException e) {
       return null;
     } 
     catch (NonUniqueResultException e) {
       return null;
     }
    }  
    
    public static List nadjiArtikalPoNazivuSveProd(String naziv, int kolicina, EntityManager em){
     try {
        return em.createQuery("SELECT p FROM Sadrzi p JOIN p.SifAr s WHERE p.SifP.ID != 1 AND s.Naziv LIKE CONCAT('%',:naziv,'%') AND p.kolicina >= :kolicina").setParameter("naziv", naziv).setParameter("kolicina", kolicina).getResultList();
     }
     catch (NoResultException e) {
       return null;
     } 
     catch (NonUniqueResultException e) {
       return null;
     }
    }  
    
    public static void prodajProizvod(String naziv, int kolicina, EntityManager em){
     try {
         em.createQuery("UPDATE Sadrzi p SET p.kolicina = p.kolicina - :kolicina WHERE p.SifP.ID = 1 AND p.SifAr.Naziv LIKE CONCAT('%',:naziv,'%')").setParameter("kolicina", kolicina).setParameter("naziv", naziv).executeUpdate();
     }
     catch (NoResultException e) {
     } 
     catch (NonUniqueResultException e) {
     }
    } 

    public static Sadrzi vratiSadrziPoTacnomNazivu(String naziv, EntityManager em){
    try {
        return (Sadrzi) em.createQuery("SELECT p FROM Sadrzi p JOIN p.SifAr s WHERE p.SifP.ID = 1 AND p.SifAr.Naziv = :naziv").setParameter("naziv", naziv).getSingleResult();
     }
     catch (NoResultException e) {
       return null;
     } 
     catch (NonUniqueResultException e) {
       return null;
     }
    }    
    
    public static Promet vratiPrometZaDatum(Date datum, EntityManager em){
    try {
        return (Promet) em.createQuery("SELECT p FROM Promet p WHERE p.SifP.ID = 1 AND p.datum = :datum").setParameter("datum", datum).getSingleResult();
     }
     catch (NoResultException e) {
       return null;
     } 
     catch (NonUniqueResultException e) {
       return null;
     }
    }  
    
    public static Prodavnica vratiProdavnicu(int ID, EntityManager em){
    try {
        return (Prodavnica) em.createQuery("SELECT p FROM Prodavnica p WHERE p.ID = :ID").setParameter("ID", ID).getSingleResult();
     }
     catch (NoResultException e) {
       return null;
     } 
     catch (NonUniqueResultException e) {
       return null;
     }
    }    
    
    public static Rezervacija nadjiRezervaciju(String ime, String tel, EntityManager em){
    try {
        return (Rezervacija) em.createQuery("SELECT p FROM Rezervacija p WHERE p.ime = :ime and p.tel = :tel").setParameter("ime", ime).setParameter("tel", tel).getSingleResult();
     }
     catch (NoResultException e) {
       return null;
     } 
     catch (NonUniqueResultException e) {
       return null;
     }
    }  
    
    public static void obrisiRezervaciju(String ime, String tel, EntityManager em){
    try {
        em.createQuery("DELETE FROM Rezervacija p WHERE p.ime = :ime and p.tel = :tel").setParameter("ime", ime).setParameter("tel", tel).executeUpdate();
     }
     catch (NoResultException e) {

     } 
     catch (NonUniqueResultException e) {
      
     }
    }      
}
