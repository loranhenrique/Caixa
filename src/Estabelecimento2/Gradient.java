package Estabelecimento2;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Gradient extends JPanel {

	static final int WIDTH = 1350, HEIGHT = 700;

	public String getName() {
		return "";
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	/** Draw the example */
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setPaint(new GradientPaint(800, 1300, new Color(30, 144, 255), WIDTH, HEIGHT, Color.black));
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}
}
