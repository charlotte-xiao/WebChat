/*
 * Created by JFormDesigner on Tue Dec 15 17:21:05 CST 2020
 */

package Client.View;

import Client.Controller.ClientController;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

/**
 * @author unknown
 */
public class Login extends JFrame {
    private ClientController clientController;
    public Login() {
        FlatLightLaf.install();
        initComponents();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientController=new ClientController();
        clientController.init(this);
    }
    //登录
    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        if(textField1.getText().trim().equals("")||textField2.getText().trim().equals("")||textField3.getText().trim().equals("")){
            JOptionPane.showConfirmDialog(this,"url,账号,密码不能为空!","提示",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
        }
        if(!Pattern.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{1,5}",textField1.getText())){
            JOptionPane.showConfirmDialog(this,"url格式有误!","提示",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
        }
        if(!clientController.login(textField1.getText(),textField2.getText(),textField3.getText())){
            JOptionPane.showConfirmDialog(this,"登录失败!","提示",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
        }else{
            this.setVisible(false);
        }
    }
    //重置
    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        textField1.setText("127.0.0.1:8080");
        textField2.setText("");
        textField3.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        panel2 = new JPanel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JPasswordField();
        panel3 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(460, 330));
        setMaximizedBounds(new Rectangle(0, 0, 460, 330));
        setTitle("WebChat");
        setIconImage(new ImageIcon(getClass().getResource("/image/ico.png")).getImage());
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(10, 10));

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax .
            swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "charlotte-xiao" , javax. swing .border
            . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dialo\u0067"
            , java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,panel1. getBorder
            () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java
            . beans. PropertyChangeEvent e) { if( "borde\u0072" .equals ( e. getPropertyName () ) )throw new RuntimeException
            ( ) ;} } );
            panel1.setLayout(new GridLayout(3, 1, 25, 25));

            //---- label1 ----
            label1.setText("URL");
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label1);

            //---- label2 ----
            label2.setText("\u7528\u6237\u8d26\u53f7");
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label2);

            //---- label3 ----
            label3.setText("\u7528\u6237\u5bc6\u7801");
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label3);
        }
        contentPane.add(panel1, BorderLayout.WEST);

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayout(3, 1, 30, 30));

            //---- textField1 ----
            textField1.setMargin(new Insets(2, 2, 2, 2));
            textField1.setMaximumSize(new Dimension(61, 38));
            textField1.setText("127.0.0.1:8080");
            panel2.add(textField1);

            //---- textField2 ----
            textField2.setMaximumSize(new Dimension(61, 38));
            panel2.add(textField2);
            panel2.add(textField3);
        }
        contentPane.add(panel2, BorderLayout.CENTER);

        //======== panel3 ========
        {
            panel3.setLayout(new GridLayout(1, 2));

            //---- button1 ----
            button1.setText("\u767b\u5f55");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            panel3.add(button1);

            //---- button2 ----
            button2.setText("\u91cd\u7f6e");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            panel3.add(button2);
        }
        contentPane.add(panel3, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JPanel panel2;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField textField3;
    private JPanel panel3;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
