package model.levels.underwater;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Random;

import model.Camera;
import model.User;
import model.entities.HostileEntity;

public class HarpoonDiver extends HostileEntity
{
	private double startX;
	private double startY = 80;

	private double jumpX, jumpY;
	private final Point2D gravity = new Point2D.Double(0, 0.4);

	public HarpoonDiver(List<User> users)
	{
		super(users);
		Random rand = new Random();
		startX = rand.nextInt(Camera.VIEW_WIDTH);
		startY = -getDimensions().getHeight();
		jumpX = 1;
		position.setLocation(startX, startY);
		velocity = new Point2D.Double(0, 0);
	}

	private void initJump()
	{
		if (!users.isEmpty())
		{
			Random rd = new Random();
			Point2D userP = users.get(rd.nextInt(users.size())).getMidpoint();
			if (position.getX() < userP.getX())
				jumpX = 5;
			else
				jumpX = -5;
			jumpY = -30;
		}
	}

	public void update(double time)
	{
		super.update(time);
		if (!users.isEmpty())
		{
			Point2D userP = users.get(0).getMidpoint();
			if (userP != null)
			{
				jumpY += time / 60 + gravity.getY() * time / 60;
				position = new Point2D.Double(position.getX() + jumpX * time
						/ 60, position.getY() + jumpY);
				if (position.getY() >= Camera.VIEW_HEIGHT - 80)
					initJump();
			}
		}
	}

	@Override
	public int getReward()
	{
		return 100;
	}

	@Override
	public Point2D getRotationPoint()
	{
		return new Point2D.Double(0, 0);
	}

	@Override
	public Dimension2D getDimensions()
	{
		return new Dimension(80, 80);
	}
	
	@Override
	public String[] getImageNames()
	{
		return new String[] { "underwater/harpoonDiver.png" };
	}

	@Override
	public String getHitSoundName()
	{
		return "underwater/harpoonImpact.wav";
	}
}
