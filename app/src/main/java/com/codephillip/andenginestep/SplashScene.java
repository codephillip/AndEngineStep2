package com.codephillip.andenginestep;

import android.util.Log;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.ui.activity.BaseGameActivity;

/**
 * Created by codephillip on 7/15/16.
 */
public class SplashScene extends Scene {
    private static final String TAG = SplashScene.class.getSimpleName();
    Camera camera;
    Engine engine;
    Sprite sprite;
    BaseGameActivity activity;

    public SplashScene(BaseGameActivity activity, Camera camera, Engine engine) {
        this.camera = camera;
        this.engine = engine;
        this.activity = activity;
    }

    @Override
    public void attachChild(IEntity pEntity) {
        sprite = new Sprite(camera.getWidth() / 2, camera.getHeight() / 2, ResourceManager.splashTextureRegion, engine.getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                switch (pSceneTouchEvent.getAction()) {
                    case TouchEvent.ACTION_DOWN:
                        this.setAlpha(0.5f);
                        break;
                    case TouchEvent.ACTION_UP:
                        this.setAlpha(1.0f);
                        updateUI();
                        Log.d(TAG, "onAreaTouched: clicked");
                        break;
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        super.attachChild(sprite);
    }

    @Override
    public void registerTouchArea(ITouchArea pTouchArea) {
        super.registerTouchArea(sprite);
    }

    @Override
    public void registerUpdateHandler(IUpdateHandler pUpdateHandler) {
        super.registerUpdateHandler(pUpdateHandler);
    }

    public void updateUI(){
        this.registerUpdateHandler(new TimerHandler(5f, true, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                SceneManager sceneManager = new SceneManager(activity, engine, camera);
                sceneManager.loadMenuResources();
                sceneManager.createMenuScene();
                sceneManager.setCurrentScene(AllScenes.MENU);
            }
        }));
    }
}
