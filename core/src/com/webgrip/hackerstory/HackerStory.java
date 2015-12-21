package com.webgrip.hackerstory;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Json;

public class HackerStory extends Game {
	SpriteBatch batch;
	BitmapFont font;
	public Skin skin;

	String TITLE = "HackerStory", VERSION = "0.0.1";

	public static final float WORLD_WIDTH = 16* 30;
	public static final float WORLD_HEIGHT = 9* 30;

	@Override
	public void create () {
		Gdx.app.log("ryansApp", "Created");

		batch = new SpriteBatch();

		font = new BitmapFont();


		skin = new Skin(Gdx.files.internal("uiskin.json"));

		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		skin.dispose();
	}

	public void saveGame(Player p){
		Json json = new Json();
		String playerJson = json.toJson(p);

		FileHandle file = Gdx.files.local("players/" + p.getId() + ".json");
		file.writeString(playerJson, false);

		Gdx.app.log("HackerStory", "File saved to: "+Gdx.files.getExternalStoragePath());
	}

//	public Player loadGame(String id){
//		//TODO: get id.json, instantiate player, and return it.
//	}
}
