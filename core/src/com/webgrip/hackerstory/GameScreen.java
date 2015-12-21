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

        textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheets/body/light/walking.atlas"));

        bodyAnimation = new Animation(0.25f , textureAtlas.getRegions()); // textureAtlas.getRegions()
        bodyAnimationActor = new AnimatedImage(bodyAnimation);
        bodyAnimationActor.setPosition(playerPositionX, playerPositionY);

        float bodyWidth = bodyAnimation.getKeyFrame(0).getRegionWidth();
        float bodyHeight = bodyAnimation.getKeyFrame(0).getRegionHeight();
        float bodyX = bodyAnimationActor.getX();
        float bodyY = bodyAnimationActor.getY();



        Group body = new Group();
        body.addActor(bodyAnimationActor);


        Group head = new Group();

        textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheets/head/light/walking.atlas"));

        headAnimation = new Animation(0.25f , textureAtlas.getRegions()); // textureAtlas.getRegions()
        headAnimationActor = new AnimatedImage(headAnimation);
        headAnimationActor.setPosition(bodyX - bodyWidth * 0.05f, bodyY + bodyHeight * 0.87f);

        float headWidth = headAnimation.getKeyFrame(0).getRegionWidth();
        float headHeight = headAnimation.getKeyFrame(0).getRegionHeight();
        float headX = headAnimationActor.getX();
        float headY = headAnimationActor.getY();

        head.addActor(headAnimationActor);
 


        textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheets/face/male/002/walking.atlas"));

        faceAnimation = new Animation(0.25f, textureAtlas.getRegions());
        faceAnimationActor = new AnimatedImage(faceAnimation);
        faceAnimationActor.setPosition(headX+headWidth*0.25f, headY + headHeight*0.1f);

        head.addActor(faceAnimationActor);


        textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheets/hair/male/002/black/walking.atlas"));

        hairAnimation = new Animation(0.25f, textureAtlas.getRegions());
        hairAnimationActor = new AnimatedImage(hairAnimation);
        hairAnimationActor.setPosition(/*headX+headWidth*0.085f*/headX+hairAnimation.getKeyFrame(0).getRegionWidth()*0.04f, headY + headHeight*0.39f);

        head.addActor(hairAnimationActor);


        Group player = new Group();
        player.addActor(body);
        player.addActor(head);



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
                //super.clicked(event, x, y);
            }
        });

        TextButton addSpeed = new TextButton("Add speed!", game.skin);
        addSpeed.setPosition(HackerStory.WORLD_WIDTH * 0.6f, HackerStory.WORLD_HEIGHT * 0.3f);
        addSpeed.setWidth(HackerStory.WORLD_WIDTH * 0.4f);

        addSpeed.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                p.setSpeed(p.getSpeed() + 1);
                //super.clicked(event, x, y);
            }
        });





//        stage.addActor(attackAnimationActor);
//        stage.addActor(faceAnimationActor);
        stage.addActor(player);
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
