/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Azucena
 */
public class Principal_TransformacionG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String ruta1 = "/Users/Martha Jazmin/Documents/NetBeansProjects/Tranformaciones_Geometricas/src/main/java/img/Clima.jpg";
        String ruta2 = "/Users/Martha Jazmin/Documents/NetBeansProjects/Tranformaciones_Geometricas/src/main/java/img/AmongUs.jpg";
        String ruta3 = "/Users/Martha Jazmin/Documents/NetBeansProjects/Tranformaciones_Geometricas/src/main/java/img/Foto.jpg";
        String ruta4 = "/Users/Martha Jazmin/Documents/NetBeansProjects/Tranformaciones_Geometricas/src/main/java/img/Triste.jpg";

        System.out.println("¿Qué tipo de transformación lineal desea aplicar a la imagen?");
        System.out.println("-> Traslacion");
        System.out.println("-> Rotacion");
        System.out.println("-> Escalado");
        System.out.println("-> Deformacion(Shear)");
        System.out.println(" ");
        System.out.println("Introduzca el nombre de uno de los anteriores: ");
        Scanner scanner = new Scanner(System.in);
        String respuesta = scanner.nextLine();
        BufferedImage img1 = null;

        try {
            if (respuesta.equals("Traslacion")) {
                System.out.println("Introduzca la coordenada en X y en Y en número entero, que desea trasladar la imagen: ");
                System.out.println("x:");
                int x = scanner.nextInt();
                System.out.println("y:");
                int y = scanner.nextInt();
                img1 = ImageIO.read(new File(ruta1));
                ImageIO.write(traslacion(img1, x, y), "png",
                        new File("/Users/Martha Jazmin/Documents/NetBeansProjects/Tranformaciones_Geometricas/src/main/java/img/Traslacion.png"));

            } else if (respuesta.equals("Rotacion")) {
                System.out.println("Introduzca el número de grados que desea aplicar a la rotación: ");
                double grados = scanner.nextDouble();
                img1 = ImageIO.read(new File(ruta2));
                ImageIO.write(rotacion(img1, grados), "png",
                        new File("/Users/Martha Jazmin/Documents/NetBeansProjects/Tranformaciones_Geometricas/src/main/java/img/Rotacion.png"));

            } else if (respuesta.equals("Escalado")) {
                System.out.println("Introduzca el número de aumento que desea aplicar:");
                System.out.println("ancho:");
                double a = scanner.nextDouble();
                System.out.println("alto:");
                double b = scanner.nextDouble();
                img1 = ImageIO.read(new File(ruta3));
                ImageIO.write(escalado(img1, a, b), "png",
                        new File("/Users/Martha Jazmin/Documents/NetBeansProjects/Tranformaciones_Geometricas/src/main/java/img/Escalado.png"));

            } else {
                img1 = ImageIO.read(new File(ruta4));
                System.out.println("Introduzca el número para  x:");
                System.out.println("x:");
                double a = scanner.nextDouble();
                System.out.println("Introduzca el número para y:");
                System.out.println("y:");
                double b = scanner.nextDouble();
                ImageIO.write(deformacion(img1, a, b), "png",
                        new File("/Users/Martha Jazmin/Documents/NetBeansProjects/Tranformaciones_Geometricas/src/main/java/img/Deformacion.png"));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static BufferedImage traslacion(BufferedImage img1, int a, int b) {

        int ancho = img1.getWidth();
        int alto = img1.getHeight();
        int typeOfImage = img1.getType();

        BufferedImage img2 = new BufferedImage(ancho, alto, typeOfImage);

        Graphics2D img2D = img2.createGraphics();

        img2D.drawImage(img1, null, a, b);

        return img2;
    }
    
    public static BufferedImage rotacion(BufferedImage img1, double grados) {
        int ancho = img1.getWidth();
        int alto = img1.getHeight();
        int typeOfImage = img1.getType();

        BufferedImage img2 = new BufferedImage(ancho, alto, typeOfImage);

        Graphics2D img2D = img2.createGraphics();

        img2D.rotate(Math.toRadians(grados), ancho / 2, alto / 2);

        img2D.drawImage(img1, null, 0, 0);

        return img2;
    }

    public static BufferedImage escalado(BufferedImage img1, double a, double b) {

        int ancho = img1.getWidth();
        int alto = img1.getHeight();
        int typeOfImage = img1.getType();

        int s = (int) Math.round(ancho * a);
        int d = (int) Math.round(alto * b);

        BufferedImage img2 = new BufferedImage(s, d, typeOfImage);

        Graphics2D img2D = img2.createGraphics();

        img2D.scale(a, b);

        img2D.drawImage(img1, null, 0, 0);

        return img2;
    }

    public static BufferedImage deformacion(BufferedImage img1, double a, double b) {

        int ancho = img1.getWidth();
        int alto = img1.getHeight();
        int typeOfImage = img1.getType();
      
        int ancho2 = (int)((ancho/2)+ancho);
        int alto2 = (int)((ancho/2)+alto);
        
        BufferedImage img2 = new BufferedImage(ancho2, alto2, typeOfImage);

        Graphics2D img2D = img2.createGraphics();
         
        AffineTransform tx = new AffineTransform();
         
        float div1 = (float)a/100;
        float div2 = (float)b/100;

        tx.shear(div1, div2);
        
        img2D.setTransform(tx);
        
        int r1= Math.round(Math.abs(div1)*alto); 
        int r2= Math.round(Math.abs(div2)*ancho); 
        
        img2D.drawImage(img1, null,r1,r2);
 
        return img2;
    }


}
