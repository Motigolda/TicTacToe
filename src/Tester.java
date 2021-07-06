
public class Tester {
	public static void main(String[] args) {
		GameBoard board = new GameBoard();
		board.makeTurn(0, 0);
		System.out.println(board);
		System.out.println(board.getBoardState());
		board.makeTurn(0, 1);
		System.out.println(board);
		System.out.println(board.getBoardState());
		board.makeTurn(1, 0);
		System.out.println(board);
		System.out.println(board.getBoardState());
		board.makeTurn(0, 2);
		System.out.println(board);
		System.out.println(board.getBoardState());
		board.makeTurn(2, 0);
		System.out.println(board);
		System.out.println(board.getBoardState());
		board.clean();
		System.out.println(board);
		System.out.println(board.getBoardState());	
		
		
	}
}
