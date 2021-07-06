import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends MouseAdapter {
	public static final int BOARD_SIZE = 3;
	private GUITicTacToe gui;
	private GameBoard board;
	public Game() {
		board = new GameBoard();
		gui = new GUITicTacToe(this, this, board.getStartingTurn());
		createFrameWithPanel(gui);
	}
	public void mousePressed(MouseEvent e) {

		Object source = e.getSource();
		boolean restartClicked = false;
		try {
			JButton b = (JButton)source;
			restartClicked = true;
		} catch(ClassCastException exception) {
			
		}
		if(!restartClicked) {
			Point p = gui.getRowAndColByXAndY(e.getX(),e.getY());
			board.makeTurn(p.x, p.y);
			gui.setBoard(board.getBoard());
			switch(board.getBoardState()) {
			case WINNING_X:
				gui.announceWinner(GameMark.x);
				restart();
				break;
			case WINNING_O:
				gui.announceWinner(GameMark.o);
				restart();
				break;
			case DRAW:
				gui.announceDraw();
				restart();
				break;
			default:
				break;
			}
		} else {
			restart();
		}
		
	}
	private void restart() {
		gui.restart();
		board.restart();
	}
	private void createFrameWithPanel(JPanel panel) {
		JFrame frame = new JFrame("Tic Tac Toe");
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(panel);
		frame.setVisible(true);	
	}
}
