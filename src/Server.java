import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.JOptionPane.showMessageDialog;

public class Server {
    public static void main(String[] args) {
        int stt1,stt2,tong;
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server is connecting....");
            while (true) {
                Socket socket = serverSocket.accept();

                System.out.println("Server is connect");
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                stt1 = inputStream.readInt();
                stt2 = inputStream.readInt();
                tong = stt1 + stt2;
                System.out.println("STT1 so la: " + stt1);
                System.out.println("STT2 so la: " + stt2);
                System.out.println("Tong so la: " + tong);
                outputStream.writeInt(tong);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
