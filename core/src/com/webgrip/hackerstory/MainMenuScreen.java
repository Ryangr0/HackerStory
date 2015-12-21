package com.webgrip.hackerstory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class MainMenuScreen implements Screen{

    private HackerStory game;


    private Stage stage;

    private TextButton startButton, loadButton, createButton, quitButton;




    public MainMenuScreen(final HackerStory game){
        this.game = game;

        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();


        Viewport viewport = new FitViewport(HackerStory.WORLD_WIDTH, HackerStory.WORLD_HEIGHT);

        stage = new Stage(viewport);







        Gdx.app.log("HackerStory - MainMenuScreen.java", "Test");





        startButton = new TextButton("New Game", game.skin);
        loadButton = new TextButton("Load Game", game.skin);
        createButton = new TextButton("Create Character", game.skin);
        quitButton = new TextButton("Quit Game", game.skin);


        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, new Player("Ryan", 20)));
                dispose();
            }
        });

        createButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new CharacerCreationScreen(game));
                dispose();
            }
        });

        loadButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadGameScreen(game));
                dispose();
            }
        });




        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("HackerStory - MainMenuScreen.java", "You Clicked!");
            }
        });


        Table table = new Table();
        table.debug();

        table.setWidth(HackerStory.WORLD_WIDTH);
        table.setHeight(HackerStory.WORLD_HEIGHT);

        table.add(startButton).width(HackerStory.WORLD_WIDTH*0.6f).height(HackerStory.WORLD_HEIGHT * 0.15f).padBottom(HackerStory.WORLD_HEIGHT * 0.1f);
        table.row();
        table.add(loadButton).width(HackerStory.WORLD_WIDTH*0.6f).height(HackerStory.WORLD_HEIGHT*0.15f).padBottom(HackerStory.WORLD_HEIGHT*0.1f);
        table.row();
        table.add(createButton).width(HackerStory.WORLD_WIDTH*0.6f).height(HackerStory.WORLD_HEIGHT*0.15f).padBottom(HackerStory.WORLD_HEIGHT*0.1f);
        table.row();
        table.add(quitButton).width(HackerStory.WORLD_WIDTH*0.6f).height(HackerStory.WORLD_HEIGHT*0.15f).padBottom(HackerStory.WORLD_HEIGHT*0.1f);


        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }


    public void create() {

    }

    @Override
    public void dispose() {
        stage.dispose();

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
