import java.awt.Color;
import java.awt.Graphics;

public class GameMarkO implements GUIGameMark {

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(x, y, width, height);
		g.setColor(Color.white);
		g.fillOval(x+10, y+10, width-20, height-20);
		

	}

}
