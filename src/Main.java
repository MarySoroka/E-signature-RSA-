import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import static java.lang.String.valueOf;

public class Main extends JFrame {

    //definition of components
    private JLabel p = new JLabel("P");
    private JTextField keyP = new JTextField();
    private JTextField keyQ = new JTextField();
    private JLabel q = new JLabel("Q");
    private JTextField keyD = new JTextField();
    private JLabel d = new JLabel("D");
    private JTextField keyR = new JTextField();
    private JTextField keyE = new JTextField();


    private JLabel nameOfFile = new JLabel("Your file");
    private JComboBox<String> chooseAction = new JComboBox<>();


    private Main() {

        //cretion of main form (size and so on)
        super("StreamSchifer");
        this.setBounds(100, 100, 1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(20, 40));
        p.setVerticalAlignment(JLabel.CENTER);
        keyP.addKeyListener(new KeyListener() {
            // limit for input
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        container.add(p);
        container.add(keyP);
        keyQ.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        container.add(q);
        container.add(keyQ);
        q.setVerticalAlignment(JLabel.CENTER);
        keyD.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        container.add(d);
        container.add(keyD);

        keyR.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        JLabel r = new JLabel("R");
        container.add(r);
        container.add(keyR);
        r.setVerticalAlignment(JLabel.CENTER);
        keyE.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        JLabel e = new JLabel("E");
        container.add(e);
        container.add(keyE);


        container.add(nameOfFile);
        JButton openFile = new JButton("Find file");
        container.add(openFile);
        chooseAction.addItem("Encode");
        chooseAction.addItem("Decode");
        ComboboxCheck check = new ComboboxCheck();
        chooseAction.addItemListener(check);
        container.add(chooseAction);
        JButton enc = new JButton("DO");
        container.add(enc);
        openFile.addActionListener(new OpenFile());

        enc.addActionListener(new EventListener());


    }

    class ComboboxCheck implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            if (chooseAction.getSelectedIndex() == 1) {
                keyQ.setVisible(false);
                keyP.setVisible(false);
                keyD.setVisible(false);
                q.setVisible(false);
                p.setVisible(false);
                d.setVisible(false);
            } else {
                keyQ.setVisible(true);
                keyP.setVisible(true);
                keyD.setVisible(true);
                q.setVisible(true);
                p.setVisible(true);
                d.setVisible(true);
            }
        }
    }

    // listener of opening file
    class OpenFile implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen = new JFileChooser();
            fileopen.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int ret = fileopen.showDialog(null, "OpenFile");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileopen.getSelectedFile();
                nameOfFile.setText(file.getAbsolutePath());

            }

        }
    }

    private void showResult(String message, String title) {
        JOptionPane.showMessageDialog(null,
                message,
                title,
                JOptionPane.PLAIN_MESSAGE);
    }


    private static Boolean isPrime(long x) {
        for (long i = 2; i <= Math.sqrt(x); i++)
            if (x % i == 0)
                return false;
        return true;
    }


    //event class for components of main form
    class EventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
            int p = Integer.parseInt(keyP.getText());
            int q = Integer.parseInt(keyQ.getText());
            keyR.setText(valueOf(p*q));
            int r = Integer.parseInt(keyR.getText());
            int D = Integer.parseInt(keyD.getText());
            if(!isPrime(p)) {
                showResult("P should be prime","ERROR");
            }else {
                if (!isPrime(q)) {
                    showResult("Q should be prime", "ERROR");
                } else {
                    if ((D <= 1) || (D > ((p - 1) * (q - 1)))) {
                        showResult("D should be from 2 to phi(p*q) - 1", "ERROR");
                    } else {
                        if ((MathAlgorithm.gcd(D, (p - 1) * (q - 1)) != 1)) {
                            showResult("D should be mutually simple with phi(p*q)", "ERROR");
                        } else {
                            keyE.setText(valueOf(MathAlgorithm.EvclidAlgorithm((p-1)*(q-1),D)));
                            int E = Integer.parseInt(keyE.getText());
                                RSA rsa = new RSA(p, q);
                                switch (chooseAction.getSelectedIndex()) {
                                    case 0: {
                                        int[] hash = rsa.encode(nameOfFile.getText(), r, D, E);
                                        showResult(String.format("\nHash of message: %s\nDigital signature: %s", valueOf(hash[0]), valueOf(hash[1])), "Done");
                                    }
                                    break;
                                    case 1: {
                                        int[] hash = rsa.decode(nameOfFile.getText(), r, E);
                                        if (hash[0] != hash[1]) {
                                            showResult(String.format("\nHash of message: %s\nDigital signature: %s \nDigital signature is not valid, because S != Hash(M) ", valueOf(hash[0]), valueOf(hash[1])), "Done");
                                        } else {
                                            showResult(String.format("\nHash of message: %s\nDigital signature: %s\nDigital signature is valid, because S == Hash(M)", valueOf(hash[0]), valueOf(hash[1])), "Done");

                                        }
                                    }
                                    break;
                                }


                        }
                    }
                }
            }} catch (IOException ex) {
                showResult("Invalid file", "ERROR");
            } catch (NumberFormatException ex) {
                showResult("Invalid register", "ERROR");
                ex.printStackTrace();
            }

            }
    }

    //main function for creation of main form
    public static void main(String[] args) {
        Main app = new Main();
        app.setVisible(true);

    }
}