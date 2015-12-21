package com.webgrip.hackerstory;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;

public class LoadGameScreen implements Screen, InputProcessor {
    private Stage stage;


    public LoadGameScreen(final HackerStory game) {
        Viewport viewport = new FitViewport(HackerStory.WORLD_WIDTH, HackerStory.WORLD_HEIGHT);

        stage = new Stage(viewport);
        FileHandle[] playerFiles = Gdx.files.local("players/").list();
        Gdx.app.log("HackerStory", playerFiles.toString());

        int filled = 0;

        final List<Player> players = new ArrayList<Player>();

        for (FileHandle playerFile : playerFiles) {
            Json json = new Json();
            json.setOutputType(JsonWriter.OutputType.minimal);


            Player tmpPlayer = json.fromJson(Player.class, playerFile);
            players.add(tmpPlayer);

            Gdx.app.log("HackerStory", tmpPlayer.getName());
        }

        Table rootTable = new Table();
        rootTable.setWidth(HackerStory.WORLD_WIDTH);
        rootTable.setHeight(HackerStory.WORLD_HEIGHT);


        Table table = new Table();
        table.debug(); // TODO: Delete this

        table.setWidth(HackerStory.WORLD_WIDTH * (0.29f * 3));
        table.setHeight(HackerStory.WORLD_HEIGHT * (0.25f * 3));

        table.align(Align.center);


        for (int i = 0; i < 9; i++) {
            if(i%3==0){
                table.row().spaceBottom(game.WORLD_HEIGHT*0.05f);
            }
            if (i < players.size()) {

                final Player player = players.get(i);

                Label playerName = new Label(player.getName(), game.skin);

                Label playerLevel = new Label("Level: " + player.getSpeed(), game.skin); // TODO: change this into level
                playerLevel.setFontScale(0.5f);


                TextButton textButton = new TextButton(playerName.getText().toString(), game.skin);

                textButton.debug(); //TODO: Delete this

                textButton.add();
                textButton.row();
                textButton.add(playerLevel);

                textButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new GameScreen(game, player));
                        dispose();
                    }
                });

                table.add(textButton).height(HackerStory.WORLD_HEIGHT * 0.25f).width(HackerStory.WORLD_WIDTH * 0.25f).spaceRight(game.WORLD_WIDTH*0.02f);
            } else {

            }
        }

        rootTable.add(table);


        stage.addActor(rootTable);

        Gdx.input.setInputProcessor(stage);


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
        stage.dispose();
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
        return false;
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
