package com.whixpyl.montyrun.Textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Darren on 5/26/2016.
 */
public class Bat {
    private Texture bat1, bat2;
    private Random rand;
    private Vector2 position;
    private int flux = 1500;
    private int fly = 0;
    private int flySpeed = 2;
    private Rectangle bounds;

    public Bat(float x){
        bat1 = new Texture("bat.png");
        bat2 = new Texture("bat_fly.png");
        rand = new Random();
        position = new Vector2(x + rand.nextInt(flux),  300 + rand.nextInt(350));
        bounds = new Rectangle(position.x + 10 , position.y + 10, bat1.getWidth() - 10 , bat1.getHeight() - 10);
    }

    public Texture getBat(){
        if (fly <= 20){
            fly = fly + 1;
            return bat1;
        }
        else {
            fly = fly + 1;
            if (fly >= 40) {fly = 0;}
            return bat2;
        }
    }

    public Vector2 getPosition(){
        position.x = position.x - flySpeed;
        bounds.setPosition(position.x + 10, position.y + 10);
        return position;
    }

    public Vector2 updatePosition(float x){
        Random rand2 = new Random();
        position.x = x + rand2.nextInt(flux);
        position.y = 300 + rand2.nextInt(350);
        bounds.setPosition(position.x + 10, position.y - 10);
        return position;
    }

    public boolean collides (Rectangle alien){
        return alien.overlaps(bounds);
    }

    public void dispose(){
        bat1.dispose();
        bat2.dispose();
    }
}
