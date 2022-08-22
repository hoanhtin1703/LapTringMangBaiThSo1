import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ProgressMonitor;
import javax.swing.UIManager;

public class AppTinhTong extends javax.swing.JFrame{
    private double stt1, stt2,tong;
        private JPanel app;
    private JTextField st1;
    private JTextField st2;
    private  JButton button2;
    private JButton okButton;

    private JLabel kq;
    public AppTinhTong(){
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("App Tinh Tong");
      this.setContentPane(app);
      this.pack();
      // Ok Click
        okButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(app,"Waiting for Server...");
         try {
             // Tạo Kết Nối
                 Socket ClientSocket = new Socket("Localhost", 8081);
//                 Nhận value từ Server
             DataInputStream inputStream = new DataInputStream(ClientSocket.getInputStream());
//             Đẩy value lên serve
             DataOutputStream outputStream = new DataOutputStream(ClientSocket.getOutputStream());
                 stt1 = Double.parseDouble(AppTinhTong.this.st1.getText());
                 stt2 = Double.parseDouble(AppTinhTong.this.st2.getText());
//                 Đẩy số thứ nhất lên server
                 outputStream.writeDouble(stt1);
          //   Đẩy số thứ hai lên server
                 outputStream.writeDouble(stt2);
                 outputStream.flush();
                 // Nhận giá trị tổng từ server
                 tong = inputStream.readDouble();
                 new Thread() {
                     @Override
                     public void run() {
                         try {
                             this.sleep(1000);

                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                         AppTinhTong.this.kq.setText(String.valueOf(tong));

                     }
                 }.start();
        } catch (IOException ex) {
   Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
            }
        });
        // Cancel Click
        button2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppTinhTong.this.dispose();
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new AppTinhTong();
frame.setSize(300,250);
//        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


