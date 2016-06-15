package com.codephillip.andenginestep;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by codephillip on 6/15/16.
 */
public class Circle extends Sprite {

    boolean moveLeft = false;
    public Circle(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        if(!moveLeft){
            this.mX += 6;
        } else {
            this.mX -= 6;
        }

        if (this.mX >= MainActivity.CAMERA_WIDTH){
            moveLeft = true;
        } else if (this.mX <= 0){
            moveLeft = false;
        }

//        this.mX += 6;
    }
}
