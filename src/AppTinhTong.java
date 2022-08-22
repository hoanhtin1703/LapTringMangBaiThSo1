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
    private int stt1, stt2,tong;
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
        okButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(app,"Waiting for Server...");
         try {
                 Socket ClientSocket = new Socket("Localhost", 8080);
             DataInputStream inputStream = new DataInputStream(ClientSocket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(ClientSocket.getOutputStream());
                 stt1 = Integer.parseInt(AppTinhTong.this.st1.getText());
                 stt2 = Integer.parseInt(AppTinhTong.this.st2.getText());
                 outputStream.writeInt(stt1);
                 outputStream.writeInt(stt2);
                 outputStream.flush();
                 tong = inputStream.readInt();
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


