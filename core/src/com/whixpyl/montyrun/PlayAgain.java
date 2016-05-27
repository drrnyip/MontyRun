package com.whixpyl.montyrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PlayAgain implements Screen {
    private MontyRun game;

    //Integers
    private int adNumber;
    private int score;
    private int highScore;
    private int first;

    //High score
    private java.util.prefs.Preferences prefs;

    //Textures
    private Texture playBtn;
    private Texture background;

    //Font
    private BitmapFont font;
    private FreeTypeFontGenerator freeType;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    //Sound
    private Sound start;
    private Sound count;

    private OrthographicCamera gamecam;


    public PlayAgain(MontyRun game, int distance, int adNumber){
        this.game = game;
        if (adNumber != -1){
            this.adNumber = adNumber;
        }
        this.score = distance;

        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, game.V_WIDTH, game.V_HEIGHT);
        Gdx.gl.glClearColor(1,1,1,0);


    }

    @Override
    public void show() {
        //Textures
        playBtn = new Texture("sign7.png");
        background = new Texture("bg4.png");

        //Sound
        start = Gdx.audio.newSound(Gdx.files.internal("jingles_PIZZA10.ogg"));
        count = Gdx.audio.newSound(Gdx.files.internal("counter.mp3"));

        //Font
        freeType = new FreeTypeFontGenerator(Gdx.files.internal("kenvector_future.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 25;
        font = new BitmapFont();
        font = freeType.generateFont(fontParameter);

        //Score counting
        first = 0;

        //High score
        prefs = java.util.prefs.Preferences.userRoot().node(this.getClass().getName());
        String ID1 = "High Score";
        highScore = prefs.getInt(ID1,0);
        if (score > highScore) {
            highScore = score;
            prefs.putInt(ID1, highScore);
        }

    }

    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(gamecam.combined);

        //Game batch begin
        game.batch.begin();

        game.batch.draw(background,0,0,game.V_WIDTH,game.V_HEIGHT);
        game.batch.draw(playBtn,(game.V_WIDTH/2 - playBtn.getWidth()/2),(game.V_HEIGHT/2 - playBtn.getHeight()/2));

        if (first < score){
            font.draw(game.batch, "Score: " + first, (game.V_WIDTH/2 - playBtn.getWidth()/2) + 75,(game.V_HEIGHT/2 - playBtn.getHeight()/2) + 57);
            first = first + 13;
                count.play();
        }
        else {
            font.draw(game.batch, "Score: " + score, (game.V_WIDTH/2 - playBtn.getWidth()/2) + 75,(game.V_HEIGHT/2 - playBtn.getHeight()/2) + 57);

            if (Gdx.input.isTouched()) {
                Vector2 tmp = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                Rectangle textureBounds = new Rectangle((game.V_WIDTH / 2 - playBtn.getWidth() / 2), (game.V_HEIGHT / 2 - playBtn.getHeight() / 2), playBtn.getWidth(), playBtn.getHeight());
                if (textureBounds.contains(tmp.x, tmp.y)) {
                    start.play();
                    game.setScreen(new ScreenManager(game, 1, -1, adNumber));
                }
            }
        }

        //Score render
        font.draw(game.batch, "High Score: " + highScore, (game.V_WIDTH/2 - playBtn.getWidth()/2) + 15    ,(game.V_HEIGHT/2 - playBtn.getHeight()/2 + 150));


        game.batch.end();
        //Game batch end

    }

    @Override
    public void resize(int width, int height) {


    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

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
        font.dispose();
        freeType.dispose();
    }
}
