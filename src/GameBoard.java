import java.util.ArrayDeque;
import java.util.Deque;

public class GameBoard {
	private GameMark[][] board;
	private GameMark startingTurn, currentTurn;
	public static final GameMark DEFAULT_STARTING_TURN = GameMark.x;
	public GameBoard() {
		this(new GameMark[Game.BOARD_SIZE][Game.BOARD_SIZE]);
	}
	public GameBoard(GameMark[][] board) {
		this.board = board.clone();
		startingTurn = GameBoard.DEFAULT_STARTING_TURN;
		currentTurn = startingTurn;
		for(int i = 0; i < Game.BOARD_SIZE; i++)
			for(int j = 0; j < Game.BOARD_SIZE; j++)
				this.board[i][j] = GameMark.none;
	}
	public GameBoard(GameBoard gameBoard) {
		this(gameBoard.getBoard());
		setStartingTurn(gameBoard.getStartingTurn());
	}
	public void makeTurn(int row, int col) {
		if (row < Game.BOARD_SIZE && col < Game.BOARD_SIZE &&
				row > -1 && col > -1
				&& board[row][col] == GameMark.none) {
			board[row][col] = currentTurn;
			if(currentTurn == GameMark.x)
				currentTurn = GameMark.o;
			else
				currentTurn = GameMark.x;
		}
	}
	public void setStartingTurn(GameMark mark) {
		if(mark != GameMark.none)
			startingTurn = mark;
	}
	public GameMark getStartingTurn() {
		return startingTurn;
	}
	public void clean() {
		for(int i = 0; i < Game.BOARD_SIZE; i++)
			for(int j = 0; j < Game.BOARD_SIZE; j++)
				this.board[i][j] = GameMark.none;
		currentTurn = startingTurn;
	}
	public GameMark[][] getBoard(){
		return board.clone();
	}
	public GameState getBoardState() {
		boolean winnerRow, winnerCol, winnerLeftDiagonal, winnerRightDiagonal, noneFound;
		winnerLeftDiagonal = winnerRightDiagonal = true;
		noneFound = false;
		for(int i = 0; i < Game.BOARD_SIZE; i++) {
			winnerRow = winnerCol = true;
			for(int j = 0; j < Game.BOARD_SIZE - 1; j++) {
				if(board[i][j] != board[i][j+1])
					winnerRow = false;
				if(board[j][i] != board[j+1][i])
					winnerCol = false;
			}
			for(int j = 0; j < Game.BOARD_SIZE; j++)
				if(board[i][j] == GameMark.none)	
					noneFound = true;
			
			if(winnerRow)
				return getWinnerStateByMark(board[i][0]);
			if(winnerCol)
				return getWinnerStateByMark(board[0][i]);
			if(i+1<Game.BOARD_SIZE) {
				if(board[i][i] != board[i+1][i+1])
					winnerLeftDiagonal = false;
				if(board[i][Game.BOARD_SIZE - 1 - i] != board[i+1][Game.BOARD_SIZE - 2 - i])
					winnerRightDiagonal = false;
			}
			
		}
		if (winnerLeftDiagonal)
			return getWinnerStateByMark(board[0][0]);
		if (winnerRightDiagonal)
			return getWinnerStateByMark(board[Game.BOARD_SIZE-1][0]);
		if(!noneFound)
			return GameState.DRAW;
		return GameState.ACTIVE_GAME;
	}
	public GameMark getCurrentTurn() {
		return currentTurn;
	}
	public void restart() {
		clean();
	}
	public String toString() {
		StringBuilder ret = new StringBuilder();
		for(int i = 0; i < Game.BOARD_SIZE; i++) {
			ret.append(" ");
			for (int j = 0; j < Game.BOARD_SIZE; j++) {
				switch(board[i][j]) {
				case none:
					ret.append(" ");
					break;
				case x:
					ret.append("X");
					break;
				case o:
					ret.append("O");
					break;
				}
				if(j+1 != Game.BOARD_SIZE)
					ret.append("||");
			}
			ret.append("\n");
			if(i+1 < Game.BOARD_SIZE) {
				ret.append("---".repeat(Game.BOARD_SIZE));
				ret.append("\n");
			}

	
		}
		return ret.toString();
	}
	private GameState getWinnerStateByMark(GameMark mark) {
		if (mark == GameMark.none)
			return GameState.ACTIVE_GAME;
		if (mark == GameMark.x)
			return GameState.WINNING_X;
		return GameState.WINNING_O;
	}

}
