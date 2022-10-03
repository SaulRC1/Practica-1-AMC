/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Trio {

    private Punto Trio_De_Puntos[] = new Punto[3];
    private double distancia;

    public Trio(Punto P1, Punto P2, Punto P3) {

        double distancia1, distancia2, distancia3;
        this.Trio_De_Puntos[0] = new Punto(P1);
        this.Trio_De_Puntos[1] = new Punto(P2);
        this.Trio_De_Puntos[2] = new Punto(P3);
        Punto vertice = new Punto(Trio_De_Puntos[0]);

        distancia1 = Punto.Distancia(Trio_De_Puntos[0], Trio_De_Puntos[1])
                + Punto.Distancia(Trio_De_Puntos[0], Trio_De_Puntos[2]);
        distancia2 = Punto.Distancia(Trio_De_Puntos[1], Trio_De_Puntos[0])
                + Punto.Distancia(Trio_De_Puntos[1], Trio_De_Puntos[2]);
        distancia3 = Punto.Distancia(Trio_De_Puntos[2], Trio_De_Puntos[0])
                + Punto.Distancia(Trio_De_Puntos[2], Trio_De_Puntos[1]);

        if (distancia1 < distancia2) {
            distancia = distancia1;
        } else {
            distancia = distancia2;
            vertice = new Punto(Trio_De_Puntos[1]);
        }

        //distancia = Double.min(distancia, distancia3);
        if (distancia3 < distancia) {
            distancia = distancia3;
            vertice = new Punto(Trio_De_Puntos[2]);
        }

        /*for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 3; j++) {
                for (int k = j + 1; k < 3; k++) {
                    distancia_minima = Punto.Distancia(Trio_De_Puntos[i], Trio_De_Puntos[k])
                            + Punto.Distancia(Trio_De_Puntos[i], Trio_De_Puntos[k]);
                    if (distancia_minima < distancia) {
                        vertice = new Punto(Trio_De_Puntos[i]);
                        distancia = distancia_minima;
                    }
                }

            }

        }*/
        if (Trio_De_Puntos[1].equals(vertice)) {
            Trio_De_Puntos[1] = Trio_De_Puntos[0];
            Trio_De_Puntos[0] = vertice;
        } else if (Trio_De_Puntos[2].equals(vertice)) {

            Trio_De_Puntos[2] = Trio_De_Puntos[0];
            Trio_De_Puntos[0] = vertice;

        }

    }

    /**
     * Metodo que calcula mediante un algoritmo exhaustivo cual es el set de
     * puntos que cumple la condicion de un punto p mas cercano a otros dos
     *
     * @param T Array de Puntos
     * @param i
     * @param d Numero maximo de Puntos incluidos en el Array
     * @return
     */
    public static Trio exhaustivo(Punto T[], int i, int d) {

        Trio solucion = new Trio(T[0], T[1], T[2]);
        Trio aux;

        for (int a1 = i; a1 <= d; a1++) {
            for (int a2 = a1 + 1; a2 <= d; a2++) {
                for (int a3 = a2 + 1; a3 <= d; a3++) {

                    aux = new Trio(T[a1], T[a2], T[a3]);

                    //System.out.println("vuelta con puntos:" + (a1+1) + " " + (a2+1) + " " + (a3+1));
                    if (aux.distancia < solucion.distancia) {
                        solucion = aux;
                    }

                }
            }
        }

        return solucion;

    }

    public static Trio exhaustivo_ArrayList(ArrayList<Punto> Puntos) {

        Trio solucion = new Trio(Puntos.get(0), Puntos.get(1), Puntos.get(2));
        Trio aux;

        for (int a1 = 0; a1 < Puntos.size(); a1++) {
            for (int a2 = a1 + 1; a2 < Puntos.size(); a2++) {
                for (int a3 = a2 + 1; a3 < Puntos.size(); a3++) {

                    aux = new Trio(Puntos.get(a1), Puntos.get(a2), Puntos.get(a3));

                    //System.out.println("vuelta con puntos:" + (a1+1) + " " + (a2+1) + " " + (a3+1));
                    if (aux.distancia < solucion.distancia) {
                        solucion = aux;
                    }

                }
            }
        }

        return solucion;

    }
    
    public Punto[] getPuntos(){
        Punto[] Devolver = new Punto[3];
        Devolver[0] = new Punto(this.Trio_De_Puntos[0]);
        Devolver[1] = new Punto(this.Trio_De_Puntos[1]);
        Devolver[2] = new Punto(this.Trio_De_Puntos[2]);
        
        return Devolver;
    }

    /*public static Trio Divide_y_Venceras(ArrayList<Punto> Puntos, int izq, int der) {
        Trio solucion = null;

        if (der == 2) {
            solucion = new Trio(Puntos.get(izq), Puntos.get(izq + 1), Puntos.get(der));
        } else {
            Trio.Divide_y_Venceras(Puntos, izq, der - 1);
        }

        return solucion;
    }*/
    public static Trio Divide_y_Venceras(Punto[] Puntos) {
        Trio solucion = null;

        //Caso base, el mas senclllo
        if (Puntos.length <= 6) {
            return Trio.exhaustivo(Puntos, 0, Puntos.length - 1);
        } else {
            //Division del problema
            int mitad = Puntos.length / 2;
            Punto[] mitad_izq = new Punto[mitad];
            Punto[] mitad_der = new Punto[Puntos.length - mitad];
            //System.out.println("Longitud Total: " + Puntos.length);
            //System.out.println("Longitud de la mitad der: " + (Puntos.length - mitad));

            for (int i = 0; i < mitad; i++) {
                mitad_izq[i] = new Punto(Puntos[i]);
            }

            for (int i = 0; i < Puntos.length - mitad; i++) {
                mitad_der[i] = new Punto(Puntos[i + mitad]);
                //System.out.println("La i vale: " + i);
            }

            Trio menor_izq = Trio.Divide_y_Venceras(mitad_izq);
            Trio menor_der = Trio.Divide_y_Venceras(mitad_der);
            //Trio restante = Trio.arrayMedio(Puntos);

            //Combinacion y resolucion
            if (menor_izq.distancia < menor_der.distancia) {
                solucion = menor_izq;
            } else {
                solucion = menor_der;
            }

            int a;
            double dmin = solucion.distancia;

            for (a = mitad; a >= 0; a--) {
                if (Puntos[mitad + 1].getX() - Puntos[a].getX() > dmin) {
                    break;
                }
            }

            int b;

            for (b = mitad + 1; b <= Puntos.length - 1; b++) {
                if (Puntos[b].getX() - Puntos[mitad].getX() > dmin) {
                    break;
                }
            }

            a = a + 1;
            b = b - 1;

            for (int c = a; c <= mitad; c++) {
                for (int d = mitad + 1; d <= b; d++) {
                    for (int e = d + 1; e <= b; e++) {

                        Trio aux = new Trio(Puntos[c], Puntos[d], Puntos[e]);

                        if (aux.distancia < solucion.distancia) {
                            solucion = aux;
                        }

                    }

                }
            }

            for (int c = b; c >= mitad + 1; c--) {
                for (int d = a; d <= mitad; d++) {
                    for (int e = d + 1; e <= mitad; e++) {
                        Trio aux = new Trio(Puntos[c], Puntos[d], Puntos[e]);

                        if (aux.distancia < solucion.distancia) {
                            solucion = aux;
                        }
                    }

                }

            }

            /*if (restante.distancia < solucion.distancia) {
                solucion = restante;
            }*/
            return solucion;

        }

    }

    public static Trio Divide_y_Venceras2(Punto[] Puntos, int inicio, int fin) {

        if (inicio == fin) {
            return Trio.exhaustivo(Puntos, inicio, fin - 1);
        } else {
            int mitad = (inicio + fin) / 2;
            Trio menor_izq = Trio.Divide_y_Venceras2(Puntos, inicio, mitad);
            Trio menor_der = Trio.Divide_y_Venceras2(Puntos, mitad + 1, fin);

            if (menor_izq.distancia < menor_der.distancia) {
                return menor_izq;
            } else {
                return menor_der;
            }
        }

    }

    public static Trio DyV3(Punto[] Puntos) {
        Trio solucion = null;

        //Caso base, el mas senclllo
        if (Puntos.length <= 6) {
            return Trio.exhaustivo(Puntos, 0, Puntos.length - 1);
        } else {
            //Division del problema
            int mitad = Puntos.length / 2;
            Punto[] mitad_izq = new Punto[mitad];
            Punto[] mitad_der = new Punto[Puntos.length - mitad];
            //System.out.println("Longitud Total: " + Puntos.length);
            //System.out.println("Longitud de la mitad der: " + (Puntos.length - mitad));

            for (int i = 0; i < mitad; i++) {
                mitad_izq[i] = new Punto(Puntos[i]);
            }

            for (int i = 0; i < Puntos.length - mitad; i++) {
                mitad_der[i] = new Punto(Puntos[i + mitad]);
                //System.out.println("La i vale: " + i);
            }

            Trio menor_izq = Trio.Divide_y_Venceras(mitad_izq);
            Trio menor_der = Trio.Divide_y_Venceras(mitad_der);
            Trio restante = Trio.arrayMedio(Puntos);

            //Combinacion y resolucion
            if (menor_izq.distancia < menor_der.distancia) {
                solucion = menor_izq;
            } else {
                solucion = menor_der;
            }

            if (restante.distancia < solucion.distancia) {
                solucion = restante;
            }

            return solucion;

        }
    }

    public static Trio arrayMedio(Punto[] Puntos/*, int pos_izq, int pos_der*/) {
        int mitad = Puntos.length / 2;

        Punto[] izq = new Punto[Puntos.length - mitad + 2];
        Punto[] der = new Punto[mitad + 2];

        for (int i = 0; i < Puntos.length - mitad + 2; i++) {
            izq[i] = new Punto(Puntos[i + mitad - 2]);
        }

        for (int i = 0; i < (mitad + 2); i++) {
            der[i] = new Punto(Puntos[i]);
        }

        Trio menor_izq = Trio.Divide_y_Venceras(izq);
        Trio menor_der = Trio.Divide_y_Venceras(der);

        if (menor_izq.distancia < menor_der.distancia) {
            return menor_izq;
        } else {
            return menor_der;
        }
    }

    //public static Trio
    public static Trio DyV(Punto[] Puntos) {
        Trio solucion = null;

        //Caso base, el mas senclllo
        if (Puntos.length <= 6) {
            return Trio.exhaustivo(Puntos, 0, Puntos.length - 1);
        } else {
            //Division del problema
            int mitad = Puntos.length / 2;
            Punto[] mitad_izq = new Punto[mitad];
            Punto[] mitad_der = new Punto[Puntos.length - mitad];
            //System.out.println("Longitud Total: " + Puntos.length);
            //System.out.println("Longitud de la mitad der: " + (Puntos.length - mitad));

            for (int i = 0; i < mitad; i++) {
                mitad_izq[i] = new Punto(Puntos[i]);
            }

            for (int i = 0; i < Puntos.length - mitad; i++) {
                mitad_der[i] = new Punto(Puntos[i + mitad]);
                //System.out.println("La i vale: " + i);
            }

            Trio menor_izq = Trio.Divide_y_Venceras(mitad_izq);
            Trio menor_der = Trio.Divide_y_Venceras(mitad_der);

            int pos_izq = Trio.buscarPunto(Puntos, menor_izq.Trio_De_Puntos[0]);
            int pos_der = Trio.buscarPunto(Puntos, menor_der.Trio_De_Puntos[0]);

            if (pos_der - pos_izq - 1 >= 3) {
                Punto[] restante = new Punto[pos_der - pos_izq - 1];

                for (int i = 0; i < pos_der - pos_izq - 1; i++) {
                    restante[i] = new Punto(Puntos[i + pos_izq + 1]);
                }

                Trio menor_restante = Trio.Divide_y_Venceras(restante);

                //Combinacion y resolucion
                if (menor_izq.distancia < menor_der.distancia) {
                    solucion = menor_izq;
                } else {
                    solucion = menor_der;
                }

                if (menor_restante.distancia < solucion.distancia) {
                    solucion = menor_restante;
                }

                return solucion;
            } else {
                if (menor_izq.distancia < menor_der.distancia) {
                    solucion = menor_izq;
                } else {
                    solucion = menor_der;
                }

                return solucion;
            }

        }
    }

    public static int buscarPunto(Punto[] Puntos, Punto buscar) {
        int pos = 0;
        boolean encontrado = false;

        while (!encontrado) {
            if (buscar.equals(Puntos[pos])) {
                encontrado = true;
            } else {
                pos++;
            }
        }

        if (!encontrado) {
            pos = -1;
        }

        return pos;
    }

    public static void divideArray(Punto[] Puntos) {
        int mitad = Puntos.length / 2;
        Punto[] mitad_izq = new Punto[mitad];
        Punto[] mitad_der = new Punto[Puntos.length - mitad];

        for (int i = 0; i < mitad; i++) {
            mitad_izq[i] = new Punto(Puntos[i]);
        }

        for (int i = 0; i < Puntos.length - mitad; i++) {
            mitad_der[i] = new Punto(Puntos[i + mitad]);
        }

        System.out.println("Mitad Izquierda");
        System.out.println("---------------------------");
        for (int i = 0; i < mitad; i++) {
            System.out.println("[[" + i + "]] --> " + mitad_izq[i]);

        }

        System.out.println("Mitad Derecha");
        System.out.println("---------------------------");
        for (int i = 0; i < Puntos.length - mitad; i++) {
            System.out.println("[[" + (i + mitad) + "]] --> " + mitad_der[i]);

        }

        int pos_der = 78;
        int pos_izq = 56;

        Punto[] restante = new Punto[pos_der - pos_izq - 1];

        for (int i = 0; i < pos_der - pos_izq - 1; i++) {
            restante[i] = new Punto(Puntos[i + pos_izq + 1]);
        }

        System.out.println("Array Restante");
        System.out.println("---------------------------");
        for (int i = 0; i < pos_der - pos_izq - 1; i++) {
            System.out.println("[[" + (i + pos_izq + 1) + "]] --> " + restante[i]);

        }
    }

    /**
     * Este metodo compara la distancia entre los puntos de T1 con T2, se
     * determina si T1 es menor o mayor a T2.
     *
     * Dentro de los conjuntos de puntos, se mide la distancia entre T1[0] con
     * T1[1] y T1[2] y se comprueba si ambas distancias son menores que las de
     * T2[0] con T2[1] y T2[2]
     *
     * @param T1 Un trio de puntos, el que queramos comparar con otro.
     * @param T2 Un trio de puntos, el que queramos comparar con otro.
     * @return Devuelve true en caso de que T1 sea menor que T2. En caso
     * contrario devuelve false.
     */
    public static boolean menor(Trio T1, Trio T2) {
        boolean resultado = false;

        double DistanciaT1 = Punto.Distancia(T1.Trio_De_Puntos[0], T1.Trio_De_Puntos[1])
                + Punto.Distancia(T1.Trio_De_Puntos[0], T1.Trio_De_Puntos[2]);

        double DistanciaT2 = Punto.Distancia(T2.Trio_De_Puntos[0], T2.Trio_De_Puntos[1])
                + Punto.Distancia(T2.Trio_De_Puntos[0], T2.Trio_De_Puntos[2]);

        //System.out.println("DistanciaT1: " + DistanciaT1);
        //System.out.println("DistanciaT2: " + DistanciaT2);
        if (DistanciaT1 < DistanciaT2) {
            resultado = true;
        }
        //System.out.println("Resultado: " + resultado);
        return resultado;
    }

    public double Distancia_Trio() {
        double distancia = Punto.Distancia(Trio_De_Puntos[0], Trio_De_Puntos[1])
                + Punto.Distancia(Trio_De_Puntos[0], Trio_De_Puntos[2]);

        return distancia;
    }

    @Override
    public String toString() {
        return "\nP1 = " + this.Trio_De_Puntos[0]
                + "\nP2 = " + this.Trio_De_Puntos[1]
                + "\nP3 = " + this.Trio_De_Puntos[2] + "\nDistancia: " + distancia;
    }

    /**
     * Metodo para testear funcionamiento del algoritmo exhaustivo
     */
    public static void Test_Trio_Exhaustivo() {
        Punto P[] = new Punto[5];

        P[0] = new Punto(1, 1);
        P[1] = new Punto(2, 3);
        P[2] = new Punto(3, 4);
        P[3] = new Punto(4, 1);
        P[4] = new Punto(3, 2);

        Trio sol = Trio.exhaustivo(P, 0, 4);

        System.out.println("El trio de Puntos es: " + sol);
    }

    /**
     * Comprueba el funcionamiento del algoritmo exhaustivo con un ArrayList
     */
    public static void Test_Trio_ArrayList_Exhaustivo() {
        ArrayList<Punto> Test = new ArrayList<Punto>();

        Test.add(new Punto(1, 1));
        Test.add(new Punto(2, 3));
        Test.add(new Punto(3, 4));
        Test.add(new Punto(4, 1));
        Test.add(new Punto(3, 2));

        System.out.println("TEST EXHAUSTIVO ARRAYLIST");
        System.out.println("-------------------------");

        Trio sol = Trio.exhaustivo_ArrayList(Test);
        System.out.println("El trio de Puntos es: " + sol);

    }

}
