package com.codephillip.andenginestep;

import org.andengine.entity.IEntity;
import org.andengine.entity.scene.Scene;

/**
 * Created by codephillip on 6/22/16.
 */
public class CustomScene extends Scene {
    public void clearScene(){
        this.detachChildren();
        this.clearEntityModifiers();
        this.clearChildScene();
        this.clearTouchAreas();
        this.clearUpdateHandlers();
    }

    public void removeEntity(final IEntity entity){
        MainActivity.instance.runOnUpdateThread(new Runnable() {
            @Override
            public void run() {
                MainActivity.mScene.detachChild(entity);
                MainActivity.mScene.unregisterTouchArea(entity);
            }
        });
    }
}
