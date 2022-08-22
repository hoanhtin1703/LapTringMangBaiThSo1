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
        double stt1,stt2,tong;
        try {
//             Tạo Cổng Kết Nối
            ServerSocket serverSocket = new ServerSocket(8081);
            System.out.println("Server is connecting....");
            while (true) {
//                Kết Nối Thành Công
                Socket socket = serverSocket.accept();
                System.out.println("Server is connect");
                // Nhận giá trị từ Client
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                // Gửi giá trị cho Client
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                // Nhận số thứ nhất từ Client
                stt1 = inputStream.readDouble();
                // Nhận số thứ hai từ Client
                stt2 = inputStream.readDouble();
//                 Tính Tổng Hai Số
                tong = stt1 + stt2;
                System.out.println("STT1 so la: " + stt1);
                System.out.println("STT2 so la: " + stt2);
                System.out.println("Tong so la: " + tong);
                // Gửi giá trị Tổng về Client
                outputStream.writeDouble(tong);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
