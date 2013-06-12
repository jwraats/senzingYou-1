package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.List;

import model.Highscore;
import model.Score;

public class HighscoreView
{
	private Highscore highscore;
	private Text rankText;
	private Text songTitleText, songArtistText;
	private Text scoreText;

	public HighscoreView(Highscore highscore)
	{
		this.highscore = highscore;
		rankText = new Text(Color.WHITE, 20);
		songTitleText = new Text(Color.ORANGE, 20);
		songArtistText = new Text(Color.ORANGE, 16);
		scoreText = new Text(Color.GREEN, 22);
	}

	public void drawScore(Graphics2D g2, int rank, Score score, Point2D position)
	{
		AffineTransform ax = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		
		if (score == highscore.getLastScore())
			g2.setColor(new Color(0xFF, 0x45, 0x00, 0x7F));
		else
			g2.setColor(new Color(0x7A, 0x7A, 0x7A, 0x7F));
		
		g2.fill(ax.createTransformedShape(new Rectangle2D.Double(-10, 0, 470, 69)));
		
		// Draw the rank
		ax.translate(0, 40);
		rankText.draw(g2, ax, rank + ")");
		
		// Move to the right
		ax.translate(0, -40);
		ax.translate(25, 0);

		// Draw the capture
		AffineTransform imageTransform = (AffineTransform)ax.clone();
		Image image = score.getCapture().getImage();
		imageTransform.scale(91.0 / image.getWidth(null), 69.0 / image.getHeight(null));
		g2.drawImage(image, imageTransform, null);
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		g2.draw(imageTransform.createTransformedShape(new Rectangle2D.Double(0, 0, 640, 480)));

		// Move to the right
		ax.translate(100, 0);
		
		// Draw all the text
		ax.translate(0, 25);
		scoreText.draw(g2, ax, "" + score.toString());
		ax.translate(0, 20);
		songTitleText.draw(g2, ax, score.getSongTitle());
		ax.translate(0, 15);
		songArtistText.draw(g2, ax, score.getSongArtist());
	}

	public void draw(Graphics2D g2)
	{
		List<Score> scores = highscore.getScores();
		
		int rank = 0;

		for (Score score : scores)
		{
			drawScore(g2, rank+1, score, new Point2D.Double(100, 10 + rank * 80));
			rank++;
		}
	}
}