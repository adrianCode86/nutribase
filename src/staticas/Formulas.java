/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staticas;

import modelo.MedidaPliegue;
import modelo.MedidasNormales;
import modelo.Paciente;

/**
 *Clase con metodos estáticos en los que realizo los calculos necesario
 * @author agg
 */
public class Formulas {
    
    
    /**
     * Metodo que recibe el sexo del paciente y su porcentaje de grasa
     * y nos devuelve en que situcion se encuentra el paciente, segun 
     * en que limite de valore se encuentre el porcentaje de grasa y
     * si su sexo
     * @param sexo
     * @param grasa
     * @return categoria grasa paciente
     */
    public static String categoriaGrasa(String sexo,float grasa){
        
        if (sexo.equalsIgnoreCase("Hombre")) {
            if (grasa < 12.0f) {
                return "Bajo";
            } else if (grasa >= 12.0f && grasa < 21.0f) {
                return "Normal";
            } else if (grasa >= 21.0f && grasa < 25.0f) {
                return "Limite";
            } else {
                return "Alto";
            }
        } else {
            if (grasa < 20.0f) {
                return "Bajo";
            } else if (grasa >= 20.0f && grasa < 31.0f) {
                return "Normal";
            } else if (grasa >= 31.0f && grasa < 33.0f) {
                return "Limite";
            } else {
                return "Alto";
            }
        }

    }
    
    /**
     * Metodo que nos devuelve el peso ideal, segun el sexo y la altura 
     * del paciente, y lo calcula segun dos formulas, de las cuales se velve
     * a calcular su media para un peso más exacto.
     * @param sexo
     * @param altura
     * @return 
     */
    public static float obtenerPesoIdeal(String sexo, float altura){
        
        // Variable con un valor segun el sexo del paciente
        float k;
        if(sexo.equalsIgnoreCase("Hombre")){
            k=4.0f;
        }else{
            k=2.0f;
        }
        
        // formula que calcula peso segun lorentz
        float pesoLorentz= (float) (altura*100)-100 -(((altura*100)-150)/k);
        
        // formula que calcula peso segun la formula MLI
        float  mli= (float) (50+0.75*((altura*100)-150));
        
        return (pesoLorentz+mli)/2;
       
    }
    
    
   /**
    * Metodo que nos da el porcentaje de masa muscular para un deportista
    * @param paciente
    * @param pliegue
    * @param normales
    * @return masa muscular
    */
   public static float porcentajeMasaMuscularDeportista(Paciente paciente, MedidaPliegue pliegue, MedidasNormales normales){
     
       int auxSexo;
       if (paciente.getSexo().equalsIgnoreCase("hombre")) {
           auxSexo = 1;
       } else {
           auxSexo = 0;
       }

       //formula que nos da la masa muscular en kg
       float masaKg = (float) ((float) normales.getAltura() * (0.00744 * (Math.pow(normales.getMedidaBrazo() - (3.1416f * pliegue.getTricipital() / 10), 2)) + (0.00088 * (Math.pow(normales.getMedidaMuslo() - (3.1416f * pliegue.getMuslo() / 10), 2)) + 0.00441 * (Math.pow(normales.getMedidaGemelo() - (3.1416f * pliegue.getMedidaPlieguePierna() / 10), 2)))) + (2.4f * auxSexo) - 0.048f * paciente.getEdad() + 7.8f);

       return (100 * masaKg) / normales.getPeso();
   }
   
   /**
    * Metodo que nos calcula el gastoEnergeticoTotal a partir de la edad, sexo y si esta embarazada
    * @param paciente
    * @param normales
    * @return gatosEnergeticoTotal
    */
   public static int  gastoEnergeticoTotal(Paciente paciente, MedidasNormales normales){
       
       String sexo=paciente.getSexo();
       int edad=paciente.getEdad();
       float altura=normales.getAltura();
       float peso=normales.getPeso();
       String actividadFisica=paciente.getDatosClinicos().getActividadFisica();
       
       if(sexo.equalsIgnoreCase("Hombre")){
           if(edad < 18){
               return gastoEnergeticoNinyos(edad, peso, altura, actividadFisica);
           }else if (edad >=18 && edad < 65){
                return gastoEnergeticoAdulto(edad, peso, altura, actividadFisica);
            }else{
               gastoEnergeticoAnciano(edad, peso, altura, actividadFisica);
           }
       
       }else{
           boolean embarazada= paciente.isEmbarazada();
           if(edad < 18 && !embarazada){
               return gastoEnergeticoNinyas(edad, peso, altura, actividadFisica);
           }else if( edad < 18 && embarazada){
               return gastoEnergeticoNinyas(edad, peso, altura, actividadFisica)+350;
           }else if(edad >=18 && edad < 65 && !embarazada){
               return gastoEnergeticoAdulta(edad, peso, altura, actividadFisica);
           }else if(edad >=18 && edad < 65 && embarazada){
               return gastoEnergeticoAdulta(edad, peso, altura, actividadFisica)+350;
           }else {
               return gastoEnergeticoAnciana(edad, peso, altura, actividadFisica);
           }
       }
        return 0;
       
   }
   /* Metodo interno que calcula gastoEnergeticoToltal para un niño de 3-18 años*/
   private static int gastoEnergeticoNinyos(int edad,float peso,float altura,String actividadFisica){
       // variable que es el valor de la actividad fisica
       float af = 0;
       switch(actividadFisica){
           case "Sedentario":
               af=1.0f;
               break;
           case "Ligera":
               af=1.13f;
               break;
           case "Moderada":
               af=1.26f;
               break;
           case "Intensa":
               af=1.42f;
               break;
           case "Muy intensa":
               af=1.50f;
               break;
       }
       return (int) ((int) 88.5f-(61.9f*edad) +af*(26.7f*peso+903 * altura) +20);
       
   }
   
   /* Metodo interno que calcula gastoEnergeticoToltal para un niña de 3-18 años*/
   private static int gastoEnergeticoNinyas(int edad,float peso,float altura,String actividadFisica){
       // variable que es el valor de la actividad fisica
       float af = 0;
       switch(actividadFisica){
           case "Sedentario":
               af=1.0f;
               break;
           case "Ligera":
               af=1.16f;
               break;
           case "Moderada":
               af=1.31f;
               break;
           case "Intensa":
               af=1.56f;
               break;
           case "Muy intensa":
               af=1.60f;
               break;
       }
       return (int) ((int) 135.3f-(30.8f*edad) +af*(10*peso+934 * altura) +20);
   }
   
   /* Metodo interno que calcula gastoEnergeticoToltal para un anciano*/
   private static int gastoEnergeticoAnciano(int edad,float peso,float altura,String actividadFisica){
       // variable que es el valor de la actividad fisica
       float af = 0;
       switch(actividadFisica){
           case "Sedentario":
               af=1.2f;
               break;
           case "Ligera":
               af=1.55f;
               break;
           case "Moderada":
               af=1.72f;
               break;
           case "Intensa":
               af=1.9f;
               break;
           case "Muy intensa":
               af=2.0f;
               break;
       }
       return (int) ((int)  af*(8.8f*peso+1128*altura-1071));
   }
   
    /* Metodo interno que calcula gastoEnergeticoToltal para un anciana*/
   private static int gastoEnergeticoAnciana(int edad,float peso,float altura,String actividadFisica){
       // variable que es el valor de la actividad fisica
       float af = 0;
       switch(actividadFisica){
           case "Sedentario":
               af=1.2f;
               break;
           case "Ligera":
               af=1.55f;
               break;
           case "Moderada":
               af=1.72f;
               break;
           case "Intensa":
               af=1.9f;
               break;
           case "Muy intensa":
               af=2.0f;
               break;
       }
       return (int) ((int)  af*(9.2f*peso+637*altura-302));
   }
   
    /* Metodo interno que calcula gastoEnergeticoToltal para un adulto varon 
        de 18-65 años*/
   private static int gastoEnergeticoAdulto(int edad,float peso,float altura,String actividadFisica){
       // variable que es el valor de la actividad fisica
       float af = 0;
       switch(actividadFisica){
           case "Sedentario":
               af=1.2f;
               break;
           case "Ligera":
               af=1.4f;
               break;
           case "Moderada":
               af=1.6f;
               break;
           case "Intensa":
               af=1.8f;
               break;
           case "Muy intensa":
               af=2.0f;
               break;
       }
       return (int) ((int)  (66.47f+13.75*peso+5*altura*100-6.75f*edad)*af);
   }
   
   
    /* Metodo interno que calcula gastoEnergeticoToltal para una  mujer adulta
        de 18-65 años*/
   private static int gastoEnergeticoAdulta(int edad,float peso,float altura,String actividadFisica){
       // variable que es el valor de la actividad fisica
       float af = 0;
       switch(actividadFisica){
           case "Sedentario":
               af=1.2f;
               break;
           case "Ligera":
               af=1.35f;
               break;
           case "Moderada":
               af=1.5f;
               break;
           case "Intensa":
               af=1.7f;
               break;
           case "Muy intensa":
               af=1.8f;
               break;
       }
       return (int) ((int)  (655+9.56f*peso+1.85f*altura*100-4.7f*edad)*af);
   }
   /**
    * Metodo que devuelve valor calorico total, que es las calorias totales a consumir 
    * por el paciente
    * @param deficit
    * @param gasto
    * @return valor calorico total
    */
   public static int vct(String deficit,int gasto){
       switch(deficit){
           case "Déficit Ligero":
               return gasto-200;
           case "Déficit Moderado":
               return gasto-500;
           case "Mantenimiento":
                return gasto;
           case "Superávit Ligero":
               return gasto+200;
           case "Superávit Moderado":
               return gasto+500;
       }
       return 0;
   }
}
