package application;

import modele.Parametres;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by jerem on 23/02/2017.
 */
public class Connexion implements Parametres /*, Runnable*/ {

    /*private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Connexion(Socket s) {
        this.socket = s;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Lancement du traitement de la connexion client");
        try {
            while (!socket.isClosed()) {
                String ligne = in.readLine();
                if (ligne == null) break;
                out.println(ligne);
                out.flush();
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
    }*/

    public static void main(String[] zero) {
        Socket socket;

        try {
            socket = new Socket("localhost", PORT);
            socket.close();

            /*in = new BufferedReader((new InputStreamReader(socket.getInputStream())));
            String message_distant = in.readLine();
            System.out.println(message_distant);

                socket.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
