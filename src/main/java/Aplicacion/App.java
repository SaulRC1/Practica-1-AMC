/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import Controlador.VistaPrincipalController;
import Modelo.Punto;
import Modelo.Trio;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.UIManager;

/**
 *
 * @author SaulRC1
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Trio.Test_Trio_Exhaustivo();
        
       /*ArrayList<Punto> Puntos = new ArrayList<Punto>();
        
       Punto.LeeFichero_TSP("H:/3º Ingenieria Informatica/AMC/Practica 1/dataset_amc_1920/d657.tsp/d657.tsp", Puntos);
        
        for (int i = 0; i < Puntos.size(); i++) {
            System.out.println(Puntos.get(i));
        }
        Punto.QuickSort_Punto(Puntos, 0, Puntos.size() - 1);
        Punto[] ArrayDePuntos = new Punto[Puntos.size()];
        
        for (int i = 0; i < Puntos.size(); i++) {
            
            ArrayDePuntos[i] = Puntos.get(i);
            
        }
        
        Trio sol = Trio.exhaustivo_ArrayList(Puntos);
        
        long inicio = System.currentTimeMillis();
        sol = Trio.exhaustivo(ArrayDePuntos, 0, Puntos.size() - 1);
        long fin = System.currentTimeMillis();
        
        
        System.out.println("\nSegun el Algoritmo EXHAUSTIVO");
        System.out.println("-----------------------------");
        System.out.println("El trio de puntos es: " + sol);
        System.out.println("Ha tardado: " + (fin - inicio) + " ms");
        
        //Trio.Test_Trio_ArrayList_Exhaustivo();
       /*System.out.println("\nPuntos Ordenados con Quicksort");
       // System.out.println("--------------------------------");
        
        
        for (int i = 0; i < Puntos.size(); i++) {
            
            System.out.println(Puntos.get(i));
            
        }*/
        
        //Trio.divideArray(Puntos.toArray(Punto[]::new));
        /*inicio = System.currentTimeMillis();
        Trio sol2 = Trio.Divide_y_Venceras(ArrayDePuntos);
        fin = System.currentTimeMillis();
        System.out.println("\nSegun el Algoritmo DyV");
        System.out.println("-----------------------------");
        System.out.println("El trio de puntos es: " + sol2);
        System.out.println("Ha tardado: " + (fin - inicio) + " ms");*/
        
        try {
            for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
                if("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }          
            }
        } catch (Exception e) {
        }
        
        VistaPrincipalController Controlador = new VistaPrincipalController();
        
        /*Punto P1 = new Punto(1,1); Punto P2 = new Punto(2,3);
        Punto P3 = new Punto(3,4); Punto P4 = new Punto(4,1);
        
        Trio T1 = new Trio(P4,P2,P3);
        System.out.println(T1);
        
        Trio.Test_Trio_ArrayList_Exhaustivo();
        Trio.Test_Trio_Exhaustivo();*/
        
        
    }

}
