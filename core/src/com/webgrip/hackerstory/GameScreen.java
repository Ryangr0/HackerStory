package com.webgrip.hackerstory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;



public class GameScreen implements Screen, InputProcessor{

    HackerStory game;

    AssetManager manager;



    Sprite sprite;

    TextureAtlas textureAtlas;
    TextureRegion[] attackFrames;

    Array<TextureAtlas.AtlasRegion> atlasRegions;

    Animation bodyfaceAnimation;
    AnimatedImage bodyfaceAnimationActor;

    Animation bodyAnimation;
    AnimatedImage bodyAnimationActor;

    Animation headAnimation;
    AnimatedImage headAnimationActor;


    Animation faceAnimation;
    AnimatedImage faceAnimationActor;

    Animation hairAnimation;
    AnimatedImage hairAnimationActor;

    Stage stage;
    //Texture

    TextureRegion currentFrame;

    float stateTime;

    Player p;



    private float BASE_SPEED = 0.33f;




//    HackerStory game;

    public GameScreen(final HackerStory game, final Player p){
        this.game = game;
        this.p = p;

        float playerScreenWidth = HackerStory.WORLD_WIDTH;
        float playerScreenHeight = HackerStory.WORLD_HEIGHT;

        float playerPositionX = playerScreenWidth*0.3f;
        float playerPositionY = playerScreenHeight*0.7f;

        manager = new AssetManager();

        Group x = new Group();
        PlayerAnimation walking = new PlayerAnimation(p, "walking", playerPositionX, playerPositionY, 0, 0);
        PlayerAnimation swing1 = new PlayerAnimation(p, "swing1", playerPositionX, playerPositionY, 3, -4);
        x.addActor(swing1.getGroup());



        stateTime = 0f;

        Viewport viewport = new FitViewport(HackerStory.WORLD_WIDTH, HackerStory.WORLD_HEIGHT);

        stage = new Stage(viewport);



        TextButton textButton = new TextButton("Save", game.skin);
        textButton.setPosition(HackerStory.WORLD_WIDTH * 0.2f, HackerStory.WORLD_HEIGHT * 0.3f);
        textButton.setWidth(HackerStory.WORLD_WIDTH * 0.3f);

        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("HackerStory", "Being saved: " + Gdx.files.getLocalStoragePath());
                game.saveGame(p);
            }
        });

        TextButton addSpeed = new TextButton("Add speed!", game.skin);
        addSpeed.setPosition(HackerStory.WORLD_WIDTH * 0.6f, HackerStory.WORLD_HEIGHT * 0.3f);
        addSpeed.setWidth(HackerStory.WORLD_WIDTH * 0.4f);

        addSpeed.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                p.setSpeed(p.getSpeed() + 1);
            }
        });

        stage.addActor(x);
        stage.addActor(textButton);
        stage.addActor(addSpeed);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {



        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime();
//        currentFrame = attackAnimation.getKeyFrame(stateTime, true);

        stage.act(stateTime);
        stage.draw();


//        game.batch.begin();
//
//        //game.batch.draw(currentFrame, 50, 50, Gdx.graphics.getWidth()*0.5f, Gdx.graphics.getHeight()*0.5f);
//        //stage.draw();
//        stage.act(stateTime);
//        stage.draw();
//
//        game.batch.end();



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

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        textureAtlas.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
