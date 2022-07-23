/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entiteti.Artikal;
import entiteti.Prodavnica;
import entiteti.Sadrzi;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author Tic
 */
public class Utils {
    public static Artikal vratiArtikalPoTacnomNazivu(String naziv, EntityManager em){
    try {
        return (Artikal) em.createQuery("SELECT p FROM Artikal p WHERE p.Naziv = :naziv").setParameter("naziv", naziv).getSingleResult();
     }
     catch (NoResultException e) {
       return null;
     } 
     catch (NonUniqueResultException e) {
       return null;
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
}
