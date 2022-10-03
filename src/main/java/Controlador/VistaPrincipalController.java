/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Punto;
import Modelo.Trio;
import Vista.PanelPuntos;
import Vista.VistaPrincipal;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;

/**
 *
 * @author SaulRC1
 */
public class VistaPrincipalController {

    private VistaPrincipal vPrincipal;
    private PanelPuntos vPuntos;
    private ArrayList<Punto> Puntos = new ArrayList<Punto>();
    private Trio T = null;

    public VistaPrincipalController() {
        vPrincipal = new VistaPrincipal();
        vPuntos = new PanelPuntos();
        JScrollPane scroll = new JScrollPane(vPuntos);
        scroll.setBounds(10, 100, 500, 500);
        vPrincipal.add(scroll);
        //vPuntos.add(scroll);
        //vPrincipal.add(scroll);
        
        vPrincipal.btnCargarFichero.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                CargarFichero(evt);
            }
        });

        vPrincipal.btnAleatorio.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                CargarDatosAleatorios(evt);
            }
        });
        vPrincipal.btnCalcular.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt){
                CalcularTrio(evt);
            }
        });
        //vPrincipal.PanelPoints.paintComponents(g);

        vPrincipal.setLocationRelativeTo(null);
        vPrincipal.setLayout(new BorderLayout());
        //vPuntos.setVisible(true);
        //vPrincipal.add(vPuntos);
        //vPuntos.setLocation(0, 50);
        vPrincipal.setTitle("Practica 1 AMC - Punto Mas Cercano a Otros Dos (Saul Rodriguez Naranjo)");
        vPrincipal.setResizable(false);
        vPrincipal.setVisible(true);

    }

    private void CargarFichero(MouseEvent evt) {
        //System.out.println("Clicked");
        JFileChooser fc = new JFileChooser();
        int seleccion = fc.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero_seleccionado = fc.getSelectedFile();

            vPrincipal.txtFichero.setText(fichero_seleccionado.getName());
            this.Puntos = new ArrayList<Punto>();
            Punto.LeeFichero_TSP(fichero_seleccionado.getAbsolutePath(), Puntos);
            Punto.QuickSort_Punto(Puntos, 0, Puntos.size() - 1);
            String Mostrar = "";
            vPrincipal.txtDataSet.setText("");
            for (int i = 0; i < Puntos.size(); i++) {
                Mostrar = Mostrar + "\n" + Puntos.get(i);
                vPrincipal.txtDataSet.setText(Mostrar);
            }
            vPuntos.cargarPuntos(Puntos);

        }
    }

    private void CargarDatosAleatorios(MouseEvent evt) {

        Random numAleatorio = new Random();

        int tam_dataset = numAleatorio.nextInt(700 - 70 + 1) + 70;
        Puntos = new ArrayList<Punto>();
        vPrincipal.txtFichero.setText("SE HAN GENERADO NUMEROS DE FORMA ALEATORIA DE TAMAÑO: " + tam_dataset);
        vPrincipal.txtDataSet.setText("");
        String Mostrar = "";
        numAleatorio.setSeed(2554);
        double max = 5000;
        double min = 1;
        double x = min + (max - min) * numAleatorio.nextDouble();
        double y = min + (max - min) * numAleatorio.nextDouble();
        
        
        for (int i = 0; i < tam_dataset; i++) {

            x = min + (max - min) * numAleatorio.nextDouble();
            y = min + (max - min) * numAleatorio.nextDouble();
            Puntos.add(new Punto(x, y));
            
        }
        
        Punto.QuickSort_Punto(Puntos, 0, Puntos.size() - 1);
        
        for (int i = 0; i < tam_dataset; i++){
            Mostrar = Mostrar + "\n" + Puntos.get(i);
        }
        
        vPrincipal.txtDataSet.setText(Mostrar);
        vPuntos.cargarPuntos(Puntos);

    }
    
    private void CalcularTrio(MouseEvent evt){
        
        Punto[] ArrayPuntos = new Punto[Puntos.size()];
        for (int i = 0; i < Puntos.size(); i++) {
            ArrayPuntos[i] = Puntos.get(i);
        }
        
        //Punto.QuickSort_Punto(Puntos, 0, 0);
        long ini = System.currentTimeMillis();
        //T = Trio.exhaustivo(ArrayPuntos, 0, ArrayPuntos.length - 1);
        T = Trio.Divide_y_Venceras(ArrayPuntos);
        long fin = System.currentTimeMillis();
        
        vPrincipal.txtTiempo.setText("");
        vPrincipal.txtTiempo.setText((fin - ini) + " ms");
        
        vPrincipal.txtTrio.setText("");
        vPrincipal.txtTrio.setText(T.toString());
        
        vPuntos.setTrio(T);
    }

}
