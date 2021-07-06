import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUITicTacToe extends JPanel {
	private GameMark currentTurn;
	private JPanel controlsPanel, gamePanel;
	private JButton btnRestart;
	private JLabel lblCurrentTurn;
	private GUIGameMark[][] board;
	private final int BOARD_LINE_WIDTH = 6,
			SPACE_BETWEEN_MARK_AND_LINE = 2;
	private int width, height, lineLength, marginSides, marginTopAndDown;
	public GUITicTacToe(MouseAdapter controlsAdapter, MouseAdapter gameAdapter, GameMark startTurn) {
		currentTurn = startTurn;
		board = new GUIGameMark[Game.BOARD_SIZE][Game.BOARD_SIZE];
		btnRestart = new JButton("Restart");
		lblCurrentTurn = new JLabel();
		setLayout(new BorderLayout());
		controlsPanel = new JPanel();
		btnRestart.addMouseListener(controlsAdapter);
		controlsPanel.addMouseListener(controlsAdapter);
		gamePanel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				width = getWidth(); height = getHeight();
				lineLength = Math.min(width, height);
				marginSides = 0;
				marginTopAndDown = 0;
				if(width >= height)
					marginSides = (width - height) / 2;
				else
					marginTopAndDown = (height - width) / 2;
				
				for(int i = 0; i < Game.BOARD_SIZE - 1; i++) {
					g.setColor(Color.BLACK);
					g.fillRect(marginSides - (BOARD_LINE_WIDTH / 2) + (lineLength/Game.BOARD_SIZE)*(i+1), marginTopAndDown, BOARD_LINE_WIDTH, lineLength);
					g.fillRect(marginSides,marginTopAndDown - (BOARD_LINE_WIDTH / 2) + (lineLength/Game.BOARD_SIZE)*(i+1), lineLength, BOARD_LINE_WIDTH);

				}
				int markX, markY, markWidth, markHeight;
				
				for(int i = 0; i < Game.BOARD_SIZE; i++) {
					for(int j = 0; j < Game.BOARD_SIZE; j++) {
						markX = marginSides + (lineLength/Game.BOARD_SIZE)*i;
						markY = marginTopAndDown + (BOARD_LINE_WIDTH * j) + (lineLength/Game.BOARD_SIZE)*j;
						markWidth = ((lineLength - BOARD_LINE_WIDTH * (Game.BOARD_SIZE - 1))/3);
						markHeight = ((lineLength - BOARD_LINE_WIDTH * (Game.BOARD_SIZE - 1))/3);
						if(board[i][j] != null)
							board[i][j].draw(markX, markY, markWidth, markHeight, g);
					}
				}	
			}
		};
		gamePanel.addMouseListener(gameAdapter);
		controlsPanel.add(btnRestart);
		controlsPanel.add(lblCurrentTurn);
		
		add(controlsPanel, BorderLayout.NORTH);
		add(gamePanel, BorderLayout.CENTER);
		
	}
	public void setBoard(GameMark[][] board) {
		for(int i = 0; i < Game.BOARD_SIZE; i++) {
			for (int j = 0; j < Game.BOARD_SIZE; j++) {
				if(board[i][j] == GameMark.x)
					this.board[i][j] = new GameMarkX();
				if(board[i][j] == GameMark.o)
					this.board[i][j] = new GameMarkO();
			}
		}
		validate();
		repaint();
	}
	public void setCurrentTurn(GameMark mark) {
		if(mark != GameMark.none)
			currentTurn = mark;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	}
	public void announceWinner(GameMark winner) {
		if(winner == GameMark.x)
			JOptionPane.showMessageDialog(null, "X WON!");
		if(winner == GameMark.o)
			JOptionPane.showMessageDialog(null, "O WON!");
	}
	public void restart() {
		board = new GUIGameMark[Game.BOARD_SIZE][Game.BOARD_SIZE];
		validate();
		repaint();	
	}
	public Point getRowAndColByXAndY(int x, int y) {
		
		return new Point(3*x / getWidth() , 3*y/getHeight());
	}
	public void announceDraw() {
		JOptionPane.showMessageDialog(null, "DRAW");
	}
}
