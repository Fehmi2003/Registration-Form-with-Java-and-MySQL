import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class lolo extends JDialog {
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfSube;
    private JPasswordField tfSifre;
    private JPasswordField tfSifre2;
    private JButton btnKayit;
    private JButton btnIptal;
    private JPanel kayitSayfasi;

    public lolo(JFrame parent) {
        super(parent);
        setTitle("Yeni hesap oluştur");
        setContentPane(kayitSayfasi);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnKayit.addActionListener(new ActionListener() {
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
                    "Şifreler birbirleriyle eşleşmedi ! ",
                    "Tekrar Deneyiniz",
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
    public  User user;
    private User addUserToDatabe(String name, String email, String phone,String address, String password){
        User user   = null;
        final String DB_URL="jdbc:mysql://localhost/javaform?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";


      try{
        Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        // Connected to database successfully...

        Statement stmt = conn.createStatement();
        String sql = "INSERT INTO users (name, email, phone, address, password) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, phone);
        preparedStatement.setString(4, address);
        preparedStatement.setString(5, password);

        //Insert row into the table
        int addedRows = preparedStatement.executeUpdate();
        if (addedRows > 0) {
            user = new User();
            user.name = name;
            user.email = email;
            user.phone = phone;
            user.address = address;
            user.password = password;
        }

        stmt.close();
        conn.close();
    }
      catch(Exception e)
    {
        e.printStackTrace();
    }

        return user;
}


    public static void main(String []args){
        lolo myForm= new lolo(null);
            User user =myForm.user;
            if (user!=null){
                System.out.println("Giriş Başarılı. Hoşgeldiniz "+user.name);
            }
            else{
                System.out.println("Giriş İptal Edildi.");
            }
    }
}
