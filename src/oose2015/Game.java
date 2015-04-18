package oose2015;
import java.util.logging.Level;
import java.util.logging.Logger;

import oose2015.states.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame
{

	public Game(String gamename){
		super(gamename);
	}

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
		gameContainer.setTargetFrameRate(120);
        addState(new MainMenuState());
        addState(new GamePlayState());

    }

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Game("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	protected void preUpdateState(GameContainer container, int delta){

	}
}