package model.entities.rainforest;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;

import model.entities.Entity;

public class Bird extends Entity
{
	private ArrayList<Image> images = new ArrayList<Image>();
	public Bird()
	{
		super();
		if(Math.random() < 0.5){
			images.add(Toolkit.getDefaultToolkit().getImage("./images/rainforest/birdRed.png"));
		}
		else{
			images.add(Toolkit.getDefaultToolkit().getImage("./images/rainforest/birdGreen.png"));
		}
	}

	@Override
	public Point2D getRotationPoint()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension2D getDimensions()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Image> getImages()
	{
		
		return images;
	}

	@Override
	public void update(double time)
	{
		super.update(time);
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public AudioInputStream getSound() throws UnsupportedAudioFileException, IOException
	{
		return null;
	}
	
	@Override
	public AudioInputStream getHitSound() throws UnsupportedAudioFileException, IOException
	{
		return null;
	}

}
