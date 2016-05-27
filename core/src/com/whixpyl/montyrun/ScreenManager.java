package com.whixpyl.montyrun;

import com.badlogic.gdx.Screen;

/**
 * Created by Darren on 5/25/2016.
 */
public class ScreenManager implements Screen {

    private MontyRun game;
    private int to;
    private int distance = -1;
    private int adNumber = -1;

    public ScreenManager(MontyRun game, int to, int distance, int adNumber){
        this.game = game;
        this.to = to;
        if (distance != -1) {
            this.distance = distance;
        }
        if (adNumber != -1){
            this.adNumber = adNumber;
        }
    }

    @Override
    public void show() {
        if (to == 1){
            game.setScreen(new PlayScreen(game, adNumber));
        }
        else if (to == 2){
            game.setScreen(new PlayAgain(game, distance, adNumber));
        }


    }

    @Override
    public void render(float delta) {

        if (to == 0){
            game.setScreen(new MainMenu(game));
        }


    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
