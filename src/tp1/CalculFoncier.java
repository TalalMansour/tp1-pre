/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.util.ArrayList;

/**
 *  Cette classe sert a calculer les "valeur_fonciere_totale", "taxe_scolaire",
 *  "taxe_ municipale",et "lotissements" .  Et comme le type de terrin est fixe pour tous 
 *   les lots et cheque fichier jason a un seul terrain donc c'est mieux de 
 *   instencier un object de type CalculeFoncier pour cheque fichier json 
 *   en donnat le type de terrain et le prix max et min pour le constructeur
 *   tout apres. il ya de methode a appeler pour cheque caclule 
 *  Les methode inclu:-
 *  1- valeur_par_lot;
 *  2- valeur_fonciere_totale; 
 *  3-  taxe_scolaire;
    4-  taxe_municipale.
   
 * @author Talal Mansour
 */
public class CalculFoncier {
    final private double  VALEUR_BASE = 733.77; 
    private int type_terrain;    
    private double prix_m2_min;
    private double prix_m2_max; 
    private double prix_m2_moyen;
    private double somme_lots;
    private double valeur_fonciere_totale;
    private double taxe_scolaire;
    private double taxe_municipale;
    private ArrayList <Double> valeur_lot ; 
    
   /**
    * 
    * 
    * @param type_terrain
    * @param prix_m2_min
    * @param prix_m2_max 
    */
   public CalculFoncier ( int type_terrain, double prix_m2_min, double prix_m2_max ) {
       
       this.type_terrain = type_terrain;
       this.prix_m2_min = prix_m2_min;
       this.prix_m2_max = prix_m2_max;
       prix_m2_moyen = (prix_m2_min + prix_m2_max)/2;
       valeur_lot = new ArrayList<> ();
   }   
   
   private void calculeFoncierEtTax(){ 
   
       valeur_fonciere_totale = VALEUR_BASE + somme_lots;
       taxe_scolaire          = valeur_fonciere_totale * 1.2/100;
       taxe_municipale        = valeur_fonciere_totale * 2.5/100;
   }
     /**
      * 
      * 
      * 
      * 
      * @param type_terrain
      * @param nombre_droits_passage
      * @param nombre_services
      * @param superficie 
      */       
    public void valeur_par_lot ( int superficie,  int nombre_droits_passage ,int nombre_service){
        double valeurDuLot = calculMontantSuperficie( superficie);
        double valeurDroitPassage =  calculDroitsPassage (nombre_droits_passage ,valeurDuLot);
        double valeurService = calculService(superficie, nombre_service);
        double totalParLot = valeurDuLot + valeurDroitPassage +valeurService;
        somme_lots += totalParLot;
        valeur_lot.add(totalParLot);
        
     }
   
    /**
     * 
     * 
     * 
     * @param superficie
     * @return 
     */
    private double calculMontantSuperficie ( int superficie){
    double resultat =0;
    switch (type_terrain) {
        case 0:
        resultat = superficie * prix_m2_min;
        break;
        case 1:    
        resultat = superficie * prix_m2_moyen;
        break;
        case 2:
        resultat = superficie * prix_m2_max;     
     }    
     return resultat;       
    }
    
    /**
     * 
     * 
     * 
     * @param nombre_droits_passage
     * @return 
     */
    private double calculDroitsPassage  ( int nombre_droits_passage , double valeurLot){
        double resultat = 0;
        final int montatBase = 500;
        switch (type_terrain) {
            case 0:
            resultat = montatBase -( nombre_droits_passage*(5/100*valeurLot));
            break;
            case 1:    
            resultat = montatBase -( nombre_droits_passage*(10/100*valeurLot));
            break;
            case 2:
           resultat = montatBase -( nombre_droits_passage*(5/100*valeurLot));
         }    
         return resultat;       
    }
      
    private double calculService ( int superficie , int nombre_service ){
        double service = 0.0;
        switch (type_terrain) {
            case 1:  
                if (superficie > 500 && superficie <= 10000){
                    service = 500 * nombre_service;
                }else if (superficie > 10000) {
                     service = 1000 *nombre_service;
                }
            break;
      
            case 2:
            if (superficie < 500 ){
                service = 500* nombre_service;
            }else{
                service = 1500*nombre_service;
            }  
         }  
        service = ( service >5000)? 5000:service;
        return service;       
    }
    
     //********les getters***********
     /**
     * 
     * @return 
     */
     public String getValeur_fonciere_totale(){
        return ""+ valeur_fonciere_totale + " $" ;
    }
     /**
      * 
      * @return 
      */
    public String getTaxe_scolaire( ){
        return ""+  taxe_scolaire + " $";
    }
     
    /**
     * 
     * @return 
     */  
    public String getTaxe_municipale( ){
        return ""+ taxe_municipale + " $";
    
    } 
    
    /**
     * 
     * @return 
     */  
    public String getValeur_lot(int lot_nbr ){
        return ""+ valeur_lot.get (lot_nbr - 1) + " $";
    
    } 
}
