/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entiteti.Artikal;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author Tic
 */
public class Utils {
   public static Artikal vratiSadrziPoTacnomNazivu(String naziv, EntityManager em){
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

    
}
