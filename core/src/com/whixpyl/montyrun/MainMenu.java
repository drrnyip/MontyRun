package com.whixpyl.montyrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MainMenu implements Screen {

    private MontyRun game;
    private OrthographicCamera gamecam;
    private Texture playBtn;
    private Texture background;
    private Texture title;
    private Music music;
    private Sound start;

    public MainMenu(MontyRun game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, game.V_WIDTH , game.V_HEIGHT);
        Gdx.gl.glClearColor(1,1,1,0);

    }

    @Override
    public void show() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gamecam.update();
        playBtn = new Texture("play_small.png");
        background = new Texture("bg4.png");
        start = Gdx.audio.newSound(Gdx.files.internal("jingles_PIZZA10.ogg"));
        music = Gdx.audio.newMusic(Gdx.files.internal("good_day_to_shop.mp3"));
        title = new Texture("montyRun2.png");

    }

    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(gamecam.combined);


        game.batch.begin();
        music.setVolume(0.35f);
        music.setLooping(true);
        music.play();
        game.batch.draw(background, 0, 0, game.V_WIDTH, game.V_HEIGHT);
        game.batch.draw(title, (game.V_WIDTH / 2 - title.getWidth() / 2), (game.V_HEIGHT / 2 - playBtn.getHeight() / 2) + title.getHeight() + 100);
        game.batch.draw(playBtn, (game.V_WIDTH / 2 - playBtn.getWidth() / 2), (game.V_HEIGHT / 2 - playBtn.getHeight() / 2) - 75);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            Vector2 tmp = new Vector2(Gdx.input.getX(), Gdx.input.getY());
//            camera.unproject(tmp);
            Rectangle textureBounds = new Rectangle((game.V_WIDTH / 2 - playBtn.getWidth() / 2), (game.V_HEIGHT / 2 - playBtn.getHeight() / 2), playBtn.getWidth(), playBtn.getHeight());
            if (textureBounds.contains(tmp.x, tmp.y)) {
                start.play();
                game.setScreen(new ScreenManager(game, 1, -1, -1));
                dispose();
            }
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
        dispose();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        start.dispose();

    }
}
