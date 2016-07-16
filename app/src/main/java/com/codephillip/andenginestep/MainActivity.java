package com.codephillip.andenginestep;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import java.io.IOException;

public class MainActivity extends BaseGameActivity {

    private Camera camera;
    private static int CAMERA_WIDTH = 800;
    private static int CAMERA_HEIGHT = 800;

    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), camera);
        return engineOptions;
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
//        sceneManager.loadSplashResources();
        ResourceManager.getInstance().setup(this.getEngine(), this.getApplicationContext(), CAMERA_WIDTH, CAMERA_HEIGHT);
//        SceneManager.loadSplashResources();
        SceneManager.loadMenuResources();
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {
//        pOnCreateSceneCallback.onCreateSceneFinished(SceneManager.createSplashScene());
        pOnCreateSceneCallback.onCreateSceneFinished(SceneManager.createMenuScene());
    }

    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {

//        mEngine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
//            @Override
//            public void onTimePassed(TimerHandler pTimerHandler) {
//                mEngine.unregisterUpdateHandler(pTimerHandler);
//                sceneManager.loadMenuResources();
//                sceneManager.createMenuScene();
//                sceneManager.setCurrentScene(AllScenes.MENU);
//            }
//        }));
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }


}
