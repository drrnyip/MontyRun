package com.whixpyl.montyrun.Textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Darren on 5/26/2016.
 */
public class Snake {

    private Vector2 position;
    private int flux = 2000;
    private Random rand;
    private Texture snake1, snake2;
    private int animate = 0;
    private Rectangle bounds;

    public Snake (float x) {
        snake1 = new Texture("fatSnake1.png");
        snake2 = new Texture("fatSnake2.png");
        rand = new Random();
        position = new Vector2(x + rand.nextInt(flux), (-snake1.getHeight() / 2) + rand.nextInt(250));
        bounds = new Rectangle(position.x + 10, position.y - 10, snake1.getWidth() - 20, snake1.getHeight() - 10);

    }

    public Texture getSnake(){
        if (animate <= 20){
            animate = animate + 1;
            return snake1;
        }
        else {
            animate = animate + 1;
            if (animate >= 40) {animate = 0;}
            return snake2;
        }
    }

    public Vector2 getPosition(){
        return position;
    }

    public Vector2 updatePosition(float x){
        Random rand2 = new Random();
        position.x = x + rand2.nextInt(flux);
        position.y = (-snake1.getHeight() / 2) + rand2.nextInt(250);
        bounds.setPosition(position.x + 10, position.y - 10);
        return position;
    }

    public boolean collides (Rectangle alien){
        return alien.overlaps(bounds);
    }

    public void dispose() {
        snake1.dispose();
        snake2.dispose();
    }



}
