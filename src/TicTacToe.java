import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    private char currentPlayer = 'X';
    private char[][] board = new char[3][3];
    private JButton[][] buttons = new JButton[3][3];
    private String player1Name = "Gracz 1";
    private String player2Name = "Gracz 2";

    public TicTacToe() {
        super("Tic-Tac-Toe");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getPlayerNames();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(100, 100));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        makeMove((JButton) e.getSource());
                    }
                });
                panel.add(button);
                buttons[i][j] = button;
            }
        }

        add(panel);
        setVisible(true);
    }

    private void getPlayerNames() {
        player1Name = JOptionPane.showInputDialog(this, "Gracz 1, wprowadź swoją nazwę:");
        player2Name = JOptionPane.showInputDialog(this, "Gracz 2, wprowadź swoją nazwę:");
    }

    private void makeMove(JButton button) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (button == buttons[i][j]) {
                    if (board[i][j] == 0) {
                        board[i][j] = currentPlayer;
                        button.setText(String.valueOf(currentPlayer));
                        if (currentPlayer == 'X') {
                            button.setBackground(Color.RED);
                        } else {
                            button.setBackground(Color.BLUE);
                        }
                        if (checkWin(currentPlayer)) {
                            resetGame();
                        } else if (isFull()) {
                            JOptionPane.showMessageDialog(this, "draw!");
                            resetGame();
                        } else {
                            if (currentPlayer == 'X') {
                                currentPlayer = 'O';
                            } else {
                                currentPlayer = 'X';
                            }
                        }
                    }
                    return;
                }
            }
        }
    }

    private boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                if (player == 'X') {
                    JOptionPane.showMessageDialog(this, player1Name + " wins!");
                } else {
                    JOptionPane.showMessageDialog(this, player2Name + " wins!");
                }
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                if (player == 'X') {
                    JOptionPane.showMessageDialog(this, player1Name + " wins!");
                } else {
                    JOptionPane.showMessageDialog(this, player2Name + " wins!");
                }
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            if (player == 'X') {
                JOptionPane.showMessageDialog(this, player1Name + " wins!");
            } else {
                JOptionPane.showMessageDialog(this, player2Name + " wins!");
            }
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            if (player == 'X') {
                JOptionPane.showMessageDialog(this, player1Name + " wins!");
            } else {
                JOptionPane.showMessageDialog(this, player2Name + " wins!");
            }
            return true;
        }
        return false;
    }

    private boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        currentPlayer = 'X';
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(null);
            }
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
    }
}
