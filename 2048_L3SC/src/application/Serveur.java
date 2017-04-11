package application;

import modele.Parametres;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by jerem on 23/02/2017.
 */
public class Serveur implements Parametres {

    /*private ServerSocket serveur;
    private boolean isRunning = true;

    public Serveur() {
        try {
            serveur = new ServerSocket(PORT);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRunning) {
                    try {
                        Socket client = serveur.accept();
                        System.out.println("Connexion client reçue");
                        Thread t = new Thread(new Connexion(client));
                        t.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    serveur.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    serveur = null;
                }
            }
        });

        t.start();
    }

    public void close() {
        isRunning = false;
    }*/

    public static void main(String[] zero) {
        ServerSocket socket;

        try {
            socket = new ServerSocket(PORT);
            Thread t = new Thread(new Accepter_clients(socket));
            t.start();
            System.out.println("Les threads sont prêts");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    static class Accepter_clients implements Runnable  {

        private ServerSocket socketServer;
        private Socket socket;
        private int nbClient = 1;

        public Accepter_clients(ServerSocket s) {
            socketServer = s;
        }

        @Override
        public void run() {
            try {
                while(true) {
                    socket = socketServer.accept();
                    System.out.println("Connexion du client n°"+ nbClient);
                    nbClient++;

                    // Reception message client
                    BufferedReader in = new BufferedReader((new InputStreamReader(socket.getInputStream())));
                    String message_distant = in.readLine();
                    System.out.println(message_distant);

                    //socket.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
