package com.codephillip.andenginestep;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;

/**
 * Created by codephillip on 7/15/16.
 */
public class SplashScene extends Scene {
    Camera camera;
    Engine engine;

    public SplashScene(Camera camera, Engine engine) {
        this.camera = camera;
        this.engine = engine;
    }

    @Override
    public void attachChild(IEntity pEntity) {
        Sprite sprite = new Sprite(camera.getWidth() / 2, camera.getHeight() / 2, ResourceManager.splashTextureRegion, engine.getVertexBufferObjectManager());
        pEntity.attachChild(sprite);
        super.attachChild(pEntity);
    }
}
