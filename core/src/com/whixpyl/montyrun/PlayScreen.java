package com.whixpyl.montyrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.whixpyl.montyrun.Textures.Alien;
import com.whixpyl.montyrun.Textures.Bat;
import com.whixpyl.montyrun.Textures.FlightBar;
import com.whixpyl.montyrun.Textures.Snake;


public class PlayScreen implements Screen{

    private MontyRun game;
    private int adNumber;
    private int adCallTime = 7;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private BitmapFont font;
    private FreeTypeFontGenerator freeType;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    //Score
    private int distance;

    //FlightBar
    private FlightBar fbar;

    //Sounds
    private Sound start;
    private Sound end;
    private boolean first = true;

    //Background replication
    private Texture background1;
    private Texture background2;
    int x1 = 0;
    int x2 = 0;

    //Alien
    private Alien alien;

    //Snakes
    private Array<Snake> snakes;
    private int num_snakes = 5;
    private int snake_space = 800;
    private int snake_width = 30;
    private int countSnake;

    //Bats
    private Array<Bat> bats;
    private int num_bats = 5;
    private int countBat;

    //Adcaller
    AdCall adCaller;



    public PlayScreen(MontyRun game, int adNumber, AdCall adCaller){

        this.adCaller = adCaller;

        this.game = game;
        this.adNumber = adNumber + 1;

        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, game.V_WIDTH, game.V_HEIGHT);
        gamePort = new StretchViewport(game.V_WIDTH, game.V_HEIGHT , gamecam);

        //Fonts
        freeType = new FreeTypeFontGenerator(Gdx.files.internal("kenvector_future.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 35;
        font = new BitmapFont();
        font = freeType.generateFont(fontParameter);
        font.setColor(Color.FOREST);


        //Sound
        if (first == false) {
            end.dispose(); //disposes previous instance of end
        }
        end = Gdx.audio.newSound(Gdx.files.internal("jingles_PIZZA07.ogg"));
        start = Gdx.audio.newSound(Gdx.files.internal("jingles_PIZZA10.ogg"));
        first = false;


        alien = new Alien(0,200);

        //Snakes
        snakes = new Array<Snake>();
        for (int i = 1; i <= num_snakes + 1; i++) {
            snakes.add(new Snake(i * (snake_width + snake_space)));
        }

        //Bats
        bats = new Array<Bat>();
        for (int i = 1; i <= num_bats + 1; i++) {
            bats.add(new Bat(i * (snake_width + snake_space)));
        }

        //Flightbar
        fbar = new FlightBar(alien.getFlightTime());

    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(1,1,1,0);


        background1 = new Texture("bg4.png");
        background2 = background1;

        start.play();

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Score calculation
        distance = distance + 1;

        x2 = x1 + background1.getWidth();
        if(gamecam.position.x >= x2 + gamecam.viewportWidth / 2) {
            x1 = x2;
        }

        //Game batch begin
        game.batch.begin();

        //Background
        game.batch.draw(background1, x1, 0);
        game.batch.draw(background2, x2, 0);

        //Alien
        if (Gdx.input.isTouched()){
            alien.jump(true);
        }
        else {
            alien.noJumping();
        }
        game.batch.draw(alien.getAlien(),alien.getPosition().x, alien.getPosition().y);
        alien.update(delta);

        //Snakes render
        countSnake = 1;
        for (Snake snake: snakes){
            game.batch.draw(snake.getSnake(), snake.getPosition().x, snake.getPosition().y);
            if (gamecam.position.x - gamecam.viewportWidth> snake.getPosition().x) {
                snake.updatePosition(gamecam.position.x + (countSnake * (snake_width + snake_space)));
                countSnake = countSnake + 1;
            }
            if (snake.collides(alien.getBounds()) == true){
                end.play();
                if (adNumber <= adCallTime) {
                    game.setScreen(new ScreenManager(game, 2, distance, adNumber, adCaller));
                    dispose();

                }
                else {
                    adNumber = 0;
                    game.setScreen(new ScreenManager(game, 2, distance, adNumber, adCaller));
                    dispose();

                }
            }
        }

        //Bats render
        countBat = 1;
        for (Bat bat: bats){
            game.batch.draw(bat.getBat(), bat.getPosition().x, bat.getPosition().y);
            if (gamecam.position.x - gamecam.viewportWidth> bat.getPosition().x) {
                bat.updatePosition(gamecam.position.x + (countBat * (snake_width + snake_space)));
                countBat = countBat + 1;
            }
            if (bat.collides(alien.getBounds()) == true){
                end.play();

                if (adNumber <= adCallTime) {
                    game.setScreen(new ScreenManager(game, 2, distance, adNumber, adCaller));
                    dispose();

                }
                else {
                    adNumber = 0;
                    game.setScreen(new ScreenManager(game, 2, distance, adNumber, adCaller));
                    dispose();

                }
            }
        }

        //Gamecam
        gamecam.position.x = alien.getPosition().x + (gamecam.viewportWidth /2) - 50;
        game.batch.setProjectionMatrix(gamecam.combined);
        gamecam.update();

        //Score render
        font.draw(game.batch, "Distance: " + distance, alien.getPosition().x + 100, background1.getHeight() - 70);

        //Flightbar render
        fbar.updateFlightTime(fbar, alien.getFlightTime());
        game.batch.draw(fbar.getBar(fbar), gamecam.position.x - 50,background1.getHeight() - 215);


        game.batch.end();
        //Game batch end

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }



    @Override
    public void hide() {
        end.play();
        dispose();

    }


    @Override
    public void dispose() {
        background1.dispose();
        background2.dispose();

        alien.dispose();
        for (Snake snake: snakes){
            snake.dispose();
        }
        for (Bat bat:bats){
            bat.dispose();
        }

        fbar.dispose();
        start.dispose();

    }
}
