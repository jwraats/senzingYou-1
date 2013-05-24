package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.Timer;

import model.Game;
import control.levels.CaveLevel;
import control.levels.Level;
import control.levels.UnderwaterLevel;

public class GameController implements ActionListener
{
	private static final String audioURL = "src/audio/samples/"; 
	private Game game;
	private Level level;
	private final int UPDATES_PER_SECOND = 30;

	public GameController(Game game)
	{
		this.game = game;
		try
		{
			game.setSong(new Song(audioURL +"Canon in D Major"));
			game.getSong().play();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TODO: implement random level selection.
		level = new CaveLevel(game);
		(new Timer(1000/UPDATES_PER_SECOND, this)).start();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		level.update(1000 / UPDATES_PER_SECOND);
	}
}
