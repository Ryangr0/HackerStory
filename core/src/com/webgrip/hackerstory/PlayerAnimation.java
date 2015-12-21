package com.webgrip.hackerstory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by rgrip on 12/21/2015.
 */
public class PlayerAnimation {

    private TextureAtlas textureAtlas;

    private Group bodyHead;

    private Animation bodyHeadAnimation;
    private AnimatedImage bodyHeadAnimationActor;

    private Animation faceAnimation;
    private AnimatedImage faceAnimationActor;

    private Animation hairAnimation;
    private AnimatedImage hairAnimationActor;

    public PlayerAnimation(Player p, String animation, float playerX, float playerY, int offsetX, int offsetY){

//        Body + Head
        textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheets/bodyface/"+p.body+"/"+animation+".atlas"));

        bodyHeadAnimation = new Animation(0.25f , textureAtlas.getRegions()); // textureAtlas.getRegions()
        bodyHeadAnimationActor = new AnimatedImage(bodyHeadAnimation);
        float bodyHeadWidth = bodyHeadAnimation.getKeyFrame(0).getRegionWidth();
        float bodyHeadHeight = bodyHeadAnimation.getKeyFrame(0).getRegionHeight();
        bodyHeadAnimationActor.setPosition(playerX-bodyHeadWidth/2, playerY - bodyHeadHeight/2);
        float bodyHeadX = bodyHeadAnimationActor.getX();
        float bodyHeadY = bodyHeadAnimationActor.getY();

        bodyHead = new Group();
        bodyHead.addActor(bodyHeadAnimationActor);

//        Face

        textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheets/face/" + p.gender + "/" + p.face + "/" + animation + ".atlas"));

        faceAnimation = new Animation(0.25f, textureAtlas.getRegions());
        faceAnimationActor = new AnimatedImage(faceAnimation);
        faceAnimationActor.setPosition(bodyHeadX + 4 + offsetX, bodyHeadY + 7 + offsetY);

        bodyHead.addActor(faceAnimationActor);


//        Hair

        textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheets/hair/"+p.gender+"/"+p.hair+"/"+p.hairColor+"/"+animation+".atlas"));

        hairAnimation = new Animation(0.25f, textureAtlas.getRegions());
        hairAnimationActor = new AnimatedImage(hairAnimation);
        hairAnimationActor.setPosition(bodyHeadX + 1 + offsetX, bodyHeadY + 21 + offsetY); //14
        bodyHead.addActor(hairAnimationActor);
    }

    public Group getGroup(){
        return bodyHead;
    }
}
