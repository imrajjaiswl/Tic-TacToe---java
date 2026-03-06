import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {

    JButton[] btn = new JButton[9];
    JLabel status;
    String p1, p2;
    boolean xTurn = true;
    int moveCount = 0;

    int[][] winIndex = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
    };

    TicTacToe() {

        p1 = JOptionPane.showInputDialog(this, "Enter Player 1 Name (X)");
        p2 = JOptionPane.showInputDialog(this, "Enter Player 2 Name (O)");

        if (p1 == null || p1.isEmpty()) p1 = "Player 1";
        if (p2 == null || p2.isEmpty()) p2 = "Player 2";

        setTitle("Tic Tac Toe");
        setSize(450, 520);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Font f = new Font("Arial", Font.BOLD, 50);

        int x = 50, y = 60, k = 0;
        for (int i = 0; i < 3; i++) {
            x = 50;
            for (int j = 0; j < 3; j++) {
                btn[k] = new JButton("");
                btn[k].setFont(f);
                btn[k].setBounds(x, y, 100, 100);
                btn[k].addActionListener(this);
                add(btn[k]);
                x += 110;
                k++;
            }
            y += 110;
        }

        status = new JLabel(p1 + "'s Turn (X)");
        status.setBounds(100, 10, 300, 30);
        status.setFont(new Font("Arial", Font.BOLD, 16));
        add(status);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) return;

        if (xTurn) {
            clicked.setText("X");
            clicked.setForeground(Color.BLUE);
            status.setText(p2 + "'s Turn (O)");
        } else {
            clicked.setText("O");
            clicked.setForeground(Color.RED);
            status.setText(p1 + "'s Turn (X)");
        }

        moveCount++;
        xTurn = !xTurn;

        if (checkWinner()) return;

        if (moveCount == 9) {
            JOptionPane.showMessageDialog(this, "Game Draw!");
            resetGame();
        }
    }

    boolean checkWinner() {

        for (int[] w : winIndex) {
            String a = btn[w[0]].getText();
            String b = btn[w[1]].getText();
            String c = btn[w[2]].getText();

            if (!a.equals("") && a.equals(b) && b.equals(c)) {

                btn[w[0]].setBackground(Color.GREEN);
                btn[w[1]].setBackground(Color.GREEN);
                btn[w[2]].setBackground(Color.GREEN);

                String winner = a.equals("X") ? p1 : p2;

                JOptionPane.showMessageDialog(this, winner + " Wins!");
                resetGame();
                return true;
            }
        }
        return false;
    }

    void resetGame() {

        for (JButton b : btn) {
            b.setText("");
            b.setBackground(null);
        }

        xTurn = true;
        moveCount = 0;
        status.setText(p1 + "'s Turn (X)");
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
