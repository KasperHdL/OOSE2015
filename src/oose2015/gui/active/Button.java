package oose2015.gui.active;

import oose2015.gui.ShopKeeperMenu;
import oose2015.states.ShopKeeperState;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by @Kasper on 26/04/2015
 * <p/>
 * Description:
 * ---
 * <p/>
 * Usage:
 * ---
 */

public class Button extends ActiveElement {

    public String text;

    public boolean isDown;
    public Color textColor;
    public Color downColor;

    //blink
    protected int stopTextBlink = 0;
    protected int blinkTextLength = 1500;

    protected Color blinkTextColor;
    protected Color blinkBtnColor;
    protected String blinkText;

    public Button(ShopKeeperMenu menu, Vector2f position, Vector2f size, String text) {
        super(menu,position, size);


        this.text = text;


        color = Color.gray;
        textColor = Color.white;
        overColor = Color.lightGray;
        downColor = Color.black;
    }

    @Override
    public void render(Graphics graphics){
        graphics.pushTransform();
        graphics.translate(position.x, position.y);

        //graphic

        if(stopBlink > ShopKeeperState.TIME)
            graphics.setColor(blinkColor);
        else if(isDown)
            graphics.setColor(downColor);
        else if(isOver)
            graphics.setColor(overColor);
        else
            graphics.setColor(color);

        String txt = text;

        if(stopTextBlink > ShopKeeperState.TIME){
            graphics.setColor(blinkBtnColor);
            txt = blinkText;
        }

        Font f = graphics.getFont();
        int textWidth = f.getWidth(txt);
        int textHeight = f.getHeight(txt);

        graphics.fillRect(size.x/2 - (textWidth/2 + 10),size.y/2 - (textHeight/2 + 5), textWidth+20,textHeight + 10) ;

        //text

        if(stopTextBlink > ShopKeeperState.TIME)
            graphics.setColor(blinkTextColor);
        else
            graphics.setColor(textColor);

        graphics.drawString(txt,size.x/2 - textWidth/2,5);

        graphics.popTransform();
    }


    public void blinkText(String text,Color textColor,Color btnColor){
        stopTextBlink = ShopKeeperState.TIME + blinkTextLength;
        blinkTextColor = textColor;
        blinkBtnColor = btnColor;
        blinkText = text;
    }

    @Override
    public void select(){
        isDown = true;
        menu.isReady = true;
        text = "Player " + (menu.playerIndex + 1) + " is ready";
    }

    @Override
    public void movedOver(){
        super.movedOver();
        stopTextBlink = 0;
    }

    @Override
    public void movedAway(){
        super.movedAway();
        if(isDown) {
            blinkText("Player " + (menu.playerIndex + 1) + " needs a little more time", Color.red, Color.black);
            text = "Done?";
            isDown = false;
            menu.isReady = false;
        }
    }

}
