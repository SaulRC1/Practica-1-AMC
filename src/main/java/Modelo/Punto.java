/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Punto {

    private double x;
    private double y;

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Punto(Punto P) {
        this.x = P.x;
        this.y = P.y;
    }

    public static double Distancia(Punto P1, Punto P2) {

        double distancia = Math.sqrt(Math.pow(P1.x - P2.x, 2)
                + Math.pow(P1.y - P2.y, 2));

        return distancia;
    }

    /**
     * Metodo utilizado para testear el funcionamiento de la distancia entre
     * varios puntos.
     */
    public static void Test_Distancia() {
        Punto P[] = new Punto[5];

        P[0] = new Punto(1, 1);
        P[1] = new Punto(2, 3);
        P[2] = new Punto(3, 4);
        P[3] = new Punto(4, 1);
        P[4] = new Punto(3, 2);

        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {

                double Distancia = Punto.Distancia(P[i], P[j]);

                System.out.println("---------------------------");
                System.out.println("Distancia entre [P" + (i + 1) + "] y [P"
                        + (j + 1) + "]: " + Distancia);
                System.out.println("---------------------------");

            }

        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Punto other = (Punto) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }
    
    

    public static void LeeFichero_TSP(String ruta, ArrayList<Punto> Puntos) {

        try {
            File archivo = new File(ruta);
            if (!archivo.canRead()) {
                archivo.setReadable(true);
            }
            FileReader entrada = new FileReader(archivo);

            BufferedReader mibuffer = new BufferedReader(entrada);
            String linea = "";
            String split_linea[] = null;

            //int N_Puntos = 0;
            for (int i = 0; i < 6; i++) {
                linea = mibuffer.readLine();
            }
            while (linea != null) {

                linea = mibuffer.readLine();

                if (linea != null) {

                    split_linea = linea.split(" ");
                    /*System.out.println("Longitud Linea: " + split_linea.length);
                    
                    for(int i = 0; i<split_linea.length; i++){
                        System.out.println(split_linea[i]);
                        
                    }*/
                    //System.out.println(linea);
                    if (split_linea.length == 3) {
                        double x = Double.parseDouble(split_linea[1]);
                        double y = Double.parseDouble(split_linea[2]);
                        Puntos.add(new Punto(x, y));
                    }

                }

            }

            entrada.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Punto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Punto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Punto{" + "x=" + x + ", y=" + y + '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static boolean menor(Punto P1, Punto P2) {
        boolean resultado = false;

        if (P1.x < P2.x) {
            resultado = true;
        }

        return resultado;

    }

    public static boolean mayor(Punto P1, Punto P2) {
        boolean resultado = false;

        if (P1.x > P2.x) {
            resultado = true;
        }

        return resultado;

    }

    public static boolean mayor_o_igual(Punto P1, Punto P2) {
        boolean resultado = false;

        if (P1.x >= P2.x) {
            resultado = true;
        }

        return resultado;

    }

    public static boolean menor_o_igual(Punto P1, Punto P2) {
        boolean resultado = false;

        if (P1.x <= P2.x) {
            resultado = true;
        }

        return resultado;

    }
    
    
    public static int particion(ArrayList<Punto> Puntos,int izq, int der){
        Punto pivote = Puntos.get(izq);
        
        while(true)
        {
            while(Punto.menor(Puntos.get(izq), pivote)){
                izq++;
            }
            while(Punto.mayor(Puntos.get(der), pivote)){
                der--;
            }
            
            if(izq >= der){
                return der;
            }else{
                Punto temporal = Puntos.get(izq);
                Puntos.set(izq, Puntos.get(der));
                Puntos.set(der, temporal);
                izq++;
                der--;
            }
        }
    }

    public static void QuickSort_Punto(ArrayList<Punto> Puntos, int izq, int der) {

        if(izq < der){
            int IndiceParticion = particion(Puntos, izq, der);
            QuickSort_Punto(Puntos, izq, IndiceParticion);
            QuickSort_Punto(Puntos, IndiceParticion + 1, der);
        }

    }

}
