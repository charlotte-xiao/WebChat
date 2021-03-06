package Client.View;/*
 * Created by JFormDesigner on Tue Dec 15 18:16:44 CST 2020
 */

import Client.Controller.ClientController;
import com.formdev.flatlaf.FlatLightLaf;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author unknown
 */
@Data
public class ClientView extends JFrame {
    private ClientController clientController;
    public ClientView() {
        FlatLightLaf.install();
        initComponents();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
     public void  init(ClientController clientController){
        this.clientController=clientController;
         comboBox1.removeAllItems();
         comboBox1.addItem("管理员");
         comboBox1.addItem("潇潇机器人");
     }
    //注销
    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        clientController.offline();
        comboBox1.removeAllItems();
        comboBox1.addItem("管理员");
        comboBox1.addItem("潇潇机器人");
    }
    //关闭
    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
            clientController.offline();
            System.exit(0);
    }
    //发送
    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        if(!textField1.getText().trim().equals("")){
              clientController.sendMsg();
        }
    }

    private void textField1KeyPressed(KeyEvent e) {
        // TODO add your code here
        if(e.getKeyChar()==KeyEvent.VK_ENTER){
            if(!textField1.getText().trim().equals("")){
                clientController.sendMsg();
            }
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        panel2 = new JPanel();
        button3 = new JButton();
        panel3 = new JPanel();
        label3 = new JLabel();
        label4 = new JLabel();
        panel4 = new JPanel();
        comboBox1 = new JComboBox<>();
        textField1 = new JTextField();

        //======== this ========
        setResizable(false);
        setTitle("WebChat");
        setIconImage(new ImageIcon(getClass().getResource("/image/ico.png")).getImage());
        setMinimumSize(new Dimension(500, 350));
        setMaximizedBounds(new Rectangle(0, 0, 500, 350));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(5, 5));

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
            . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing
            .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
            Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
            ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
            public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e. getPropertyName (
            ) ) )throw new RuntimeException( ) ;} } );
            panel1.setLayout(new GridLayout(1, 2, 10, 10));

            //---- button1 ----
            button1.setText("\u6ce8\u9500");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            panel1.add(button1);

            //---- button2 ----
            button2.setText("\u9000\u51fa");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            panel1.add(button2);
        }
        contentPane.add(panel1, BorderLayout.NORTH);

        //======== scrollPane1 ========
        {

            //---- textArea1 ----
            textArea1.setColumns(10);
            textArea1.setEditable(false);
            textArea1.setRows(10);
            scrollPane1.setViewportView(textArea1);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);

        //======== panel2 ========
        {
            panel2.setLayout(new BorderLayout());

            //---- button3 ----
            button3.setText("\u53d1\u9001");
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button3MouseClicked(e);
                }
            });
            panel2.add(button3, BorderLayout.PAGE_END);

            //======== panel3 ========
            {
                panel3.setLayout(new GridLayout(2, 1));

                //---- label3 ----
                label3.setText("\u53d1\u9001\u81f3:");
                label3.setMaximumSize(new Dimension(15, 19));
                label3.setMinimumSize(new Dimension(15, 19));
                label3.setPreferredSize(new Dimension(15, 19));
                label3.setHorizontalTextPosition(SwingConstants.CENTER);
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                panel3.add(label3);

                //---- label4 ----
                label4.setText("\u53d1\u9001\u6d88\u606f");
                label4.setMaximumSize(new Dimension(15, 19));
                label4.setMinimumSize(new Dimension(15, 19));
                label4.setHorizontalTextPosition(SwingConstants.CENTER);
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                panel3.add(label4);
            }
            panel2.add(panel3, BorderLayout.LINE_START);

            //======== panel4 ========
            {
                panel4.setLayout(new GridLayout(2, 1));

                //---- comboBox1 ----
                comboBox1.setMaximumRowCount(50);
                comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u7ba1\u7406\u5458"
                }));
                panel4.add(comboBox1);

                //---- textField1 ----
                textField1.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        textField1KeyPressed(e);
                    }
                });
                panel4.add(textField1);
            }
            panel2.add(panel4, BorderLayout.CENTER);
        }
        contentPane.add(panel2, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JPanel panel2;
    private JButton button3;
    private JPanel panel3;
    private JLabel label3;
    private JLabel label4;
    private JPanel panel4;
    private JComboBox<String> comboBox1;
    private JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}