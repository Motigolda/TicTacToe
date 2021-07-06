import java.awt.Color;
import java.awt.Graphics;

public class GameMarkX implements GUIGameMark {

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(x, y, x+width, y+height);
		g.drawLine(x+width, y, x, y+height);
	}

}
