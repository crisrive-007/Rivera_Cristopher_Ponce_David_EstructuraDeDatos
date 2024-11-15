/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rivera_cristopher_estructuradedatos;

import java.util.Scanner;

/**
 *
 * @author river
 */
public class Rivera_Cristopher_EstructuraDeDatos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner eleccion = new Scanner(System.in);
        int opcion;
        System.out.println("===== MENU DE EJERCICIOS =====");//Se muestra el menu de opciones.
        System.out.println("1. Jugar Ahorcados");
        System.out.println("2. Cambiar palabras");
        System.out.print("Seleccione una opcion: ");
        opcion = eleccion.nextInt();
        
        switch (opcion) {//Determina la opcion que se selecciono.
            
            case 1:
                Jugar jugar = new Jugar();
                jugar.setVisible(true);
                break;
            
            case 2:
                Cambiar_Palabras cambiar = new Cambiar_Palabras();
                cambiar.setVisible(true);
                break;
                
            
    }
    
}
}