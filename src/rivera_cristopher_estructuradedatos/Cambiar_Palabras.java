/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rivera_cristopher_estructuradedatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Cambiar_Palabras extends JFrame {
    private String[] palabras;
    private int indicePalabras;
    private String palabraSecreta;
    private StringBuilder palabraAdivinada;
    private int oportunidadesRestantes = 5;

    private JLabel lblPalabra;
    private JLabel lblMensaje;
    private JLabel lblOportunidades;
    private JTextField txtLetra;
    private JTextField txtNuevaPalabra;
    private JButton btnIngresar;
    private JButton btnNuevoJuego;
    private JButton btnAgregarPalabra;

    public Cambiar_Palabras() {
        setTitle("Juego del Ahorcado");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        lblPalabra = new JLabel("_");
        lblMensaje = new JLabel("Ingresa una letra:");
        lblOportunidades = new JLabel("Oportunidades restantes: " + oportunidadesRestantes);
        txtLetra = new JTextField(1);
        txtNuevaPalabra = new JTextField(10);
        btnIngresar = new JButton("Ingresar Letra");
        btnNuevoJuego = new JButton("Nuevo Juego");
        btnAgregarPalabra = new JButton("Agregar Palabra");

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String letra = txtLetra.getText().toUpperCase();
                if (letra.length() == 1) {
                    verificarLetra(letra.charAt(0));
                }
                txtLetra.setText("");
            }
        });

        btnNuevoJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoJuego();
            }
        });

        btnAgregarPalabra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevaPalabra = txtNuevaPalabra.getText().toUpperCase();
                if (!nuevaPalabra.isEmpty() && indicePalabras < 10) {
                    palabras[indicePalabras++] = nuevaPalabra;
                    mostrarListadoPalabras();
                }
                if (indicePalabras >= 10) {
                    btnAgregarPalabra.setEnabled(false);
                }
                txtNuevaPalabra.setText("");
            }
        });

        JPanel panelJuego = new JPanel(new GridLayout(6, 1));
        panelJuego.add(lblPalabra);
        panelJuego.add(lblMensaje);
        panelJuego.add(txtLetra);
        panelJuego.add(btnIngresar);
        panelJuego.add(lblOportunidades);

        JPanel panelPalabras = new JPanel();
        panelPalabras.add(new JLabel("Nueva Palabra:"));
        panelPalabras.add(txtNuevaPalabra);
        panelPalabras.add(btnAgregarPalabra);

        add(panelJuego, BorderLayout.CENTER);
        add(panelPalabras, BorderLayout.NORTH);
        add(btnNuevoJuego, BorderLayout.SOUTH);
    }

    private void ingresarPalabras() {
        palabras = new String[10];
        indicePalabras = 0;
        while (indicePalabras < 10) {
            String palabra = JOptionPane.showInputDialog(this, "Ingrese una palabra (quedan " + (10 - indicePalabras) + "):");
            if (palabra != null && !palabra.trim().isEmpty()) {
                palabras[indicePalabras++] = palabra.toUpperCase();
            }
        }
        mostrarListadoPalabras();
        nuevoJuego();
    }

    private void mostrarListadoPalabras() {
        StringBuilder listado = new StringBuilder("Palabras ingresadas: ");
        for (int i = 0; i < indicePalabras; i++) {
            listado.append(palabras[i]);
            if (i < indicePalabras - 1) {
                listado.append(", ");
            }
        }
        JOptionPane.showMessageDialog(this, listado.toString());
    }

    private void nuevoJuego() {
        Random random = new Random();
        palabraSecreta = palabras[random.nextInt(indicePalabras)];
        palabraAdivinada = new StringBuilder("_".repeat(palabraSecreta.length()));
        oportunidadesRestantes = 5;

        actualizarPantalla();
    }

    private void verificarLetra(char letra) {
        boolean acierto = false;
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                palabraAdivinada.setCharAt(i, letra);
                acierto = true;
            }
        }

        if (!acierto) {
            oportunidadesRestantes--;
        }

        if (oportunidadesRestantes == 0 || palabraAdivinada.toString().equals(palabraSecreta)) {
            lblMensaje.setText(palabraAdivinada.toString().equals(palabraSecreta) ? "¡Felicidades! Adivinaste la palabra." : "Lo siento, has perdido. La palabra era " + palabraSecreta);
            txtLetra.setEnabled(false);
            btnIngresar.setEnabled(false);
        } else {
            lblMensaje.setText(acierto ? "¡Le pegaste a un carácter!" : "La palabra no contiene ese carácter.");
        }

        actualizarPantalla();
    }

    private void actualizarPantalla() {
        lblPalabra.setText(palabraAdivinada.toString());
        lblOportunidades.setText("Oportunidades restantes: " + oportunidadesRestantes);
        txtLetra.setEnabled(true);
        btnIngresar.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Starting application...");  // Debug output
                Cambiar_Palabras juego = new Cambiar_Palabras();
                juego.setVisible(true);
                System.out.println("GUI should be visible now.");  // Debug output
                juego.ingresarPalabras();
            }
}
}
}

