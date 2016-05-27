package com.whixpyl.montyrun.Textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Alien {

    //Variables
    private static final int speed = 500;
    private static final int gravity = -40;
    private static final int rechargeSpeed = 3;
    private int jumpHeight = 600;
    private int flightTime = 100;

    //For animation purposes
    public int runPosition1 = 0;
    private boolean jumping;

    private Vector2 position;
    private Vector2 velocity;
    private int groundHeight;
    private Rectangle bounds;

    //Assets folder
    private Texture alien_jump;
    private Texture alien_walk1;
    private Texture alien_walk2;
    private Texture alien_fly;
    private Sound jump;

    public Alien(int x, int y){
        position = new Vector2(x,y);
        velocity = new Vector2(2,0);
        groundHeight = 75;
        alien_jump = new Texture("alienBeige_jump.png");
        alien_walk1 = new Texture("alienBeige_walk1.png");
        alien_walk2 = new Texture("alienBeige_walk2.png");
        alien_fly = new Texture("alien_fly.png");
        bounds = new Rectangle(position.x, position.y, alien_walk1.getWidth(), alien_walk1.getHeight());
        jump = Gdx.audio.newSound(Gdx.files.internal("ufoHum_short.mp3"));
        jumping = false;

    }

    public void update(float delta) {
        if (position.y <= groundHeight) {
            position.y = groundHeight;
            if (flightTime < 100) {
                flightTime = flightTime + rechargeSpeed;
            }
            else if (flightTime > 100){
                flightTime = 100;
            }
        }
        else {
            velocity.add(0,gravity);
        }
        velocity.scl(delta);
        position.add(speed * delta, velocity.y);

        velocity.scl(1/delta);
    }

    public Vector2 getPosition(){
        bounds.setPosition(position.x, position.y);
        return position;

    }

    public Texture getAlien(){

        if (position.y <= groundHeight + 3){
            if (runPosition1 <= 10){
                runPosition1 = runPosition1 + 1;
                return alien_walk1;
            }
            else {
                runPosition1 = runPosition1 + 1;
                if (runPosition1 >= 20) {runPosition1 = 0;}
                return alien_walk2;
            }
        }
        else if (jumping == true && flightTime > 0)
        {
            return alien_fly;
        }
        else {
            return alien_jump;
        }

    }

    public void jump(boolean jumping){
        if (flightTime > 0) {
            velocity.y = jumpHeight;
            flightTime = flightTime - 1;
                jump.play();
            this.jumping = jumping;
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        alien_jump.dispose();
        alien_walk1.dispose();
        alien_walk2.dispose();
        alien_fly.dispose();
        jump.dispose();
    }

    public int getFlightTime(){
        return flightTime;
    }

    public void noJumping() {
        jumping = false;
    }

    public int getGroundHeight(){
        return groundHeight;
    }



}
