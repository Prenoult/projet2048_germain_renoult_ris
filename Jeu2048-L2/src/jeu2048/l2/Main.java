/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu2048.l2;

import java.util.Scanner;

/**
 *
 * @author Sylvain
 */
public class Main implements Parametres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grille g = new Grille();
        boolean b = g.nouvelleCase();
        b = g.nouvelleCase();
        System.out.println(g);
        Scanner sc = new Scanner(System.in);
        /*System.out.println("X:");
        int x= sc.nextInt();
        System.out.println("Y:");
        int y= sc.nextInt();
        System.out.println("Valeur:");
        int valeur= sc.nextInt();
        Case c = new Case(x,y,valeur);
        g.getGrille().remove(c);
        System.out.println(g);*/
        
        while (!g.partieFinie()) {
            System.out.println("Déplacer vers la Droite (d), Gauche (g), Haut (h), ou Bas (b) ?");
            String s = sc.nextLine();
            s.toLowerCase();
            if (!(s.equals("d") || s.equals("droite")
                    || s.equals("g") || s.equals("gauche")
                    || s.equals("h") || s.equals("haut")
                    || s.equals("b") || s.equals("bas"))) {
                System.out.println("Vous devez écrire d pour Droite, g pour Gauche, h pour Haut ou b pour Bas");
            } else {
                int direction;
                if (s.equals("d") || s.equals("droite")) {
                    direction = DROITE;
                } else if (s.equals("g") || s.equals("gauche")) {
                    direction = GAUCHE;
                } else if (s.equals("h") || s.equals("haut")) {
                    direction = HAUT;
                } else {
                    direction = BAS;
                }
                boolean b2 = g.lanceurDeplacerCases(direction);
                if (b2) {
                    b = g.nouvelleCase();
                    if (!b) g.gameOver();
                }
                System.out.println(g);
                if (g.getValeurMax()>=OBJECTIF) g.victory();
            }
        }
        g.gameOver();
        /*
        // Bout de code pour tester manuellement si une grille contient une case ou pas
        Scanner sc = new Scanner(System.in);
        System.out.println("x :");
        int x = sc.nextInt();
        System.out.println("y :");
        int y = sc.nextInt();
        Case c = new Case(x, y, 2);
        Case c2 = new Case(x, y, 4);
        System.out.println("test1=" + g.getGrille().contains(c));
        System.out.println("test2=" + g.getGrille().contains(c2));
         */
    }

}
