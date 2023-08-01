import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class lolo extends JDialog {
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfSube;
    private JPasswordField tfSifre;
    private JPasswordField tfSifre2;
    private JButton btnKayıt;
    private JButton btnIptal;
    private JPanel kayıtSayfası;

    public lolo(JFrame parent) {
        super(parent);
        setTitle("Yeni hesap oluştur");
        setContentPane(kayıtSayfası);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);

        btnKayıt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        btnIptal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void registerUser() {
        String name = tfName.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String address = tfSube.getText();
        String password= String.valueOf(tfSifre.getPassword());
        String confirmPassword=String.valueOf(tfSifre2.getPassword());

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Lütfen Tüm Kutucukları Doldurunuz ! ",
                    "Tekrar Dene",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Confirm Password does not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    user=addUserToDatabe(name,email,phone,address,password);
    if (user!=null){
        dispose();
    }
    else {
        JOptionPane.showMessageDialog(this,
                "Yeni Kullanıcı oluştururken hata oluştu.",
                "Tekrar Deneyiniz.",JOptionPane.ERROR_MESSAGE);
    }
    }
    public  Kullanıcılar user;
    private Kullanıcılar addUserToDatabe(String name, String email, String phone,String address, String password){
        Kullanıcılar kullanıcılar   = null;

        return kullanıcılar;
    }


    public static void main(String []args){
        lolo myForm= new lolo(null);
    }
}
