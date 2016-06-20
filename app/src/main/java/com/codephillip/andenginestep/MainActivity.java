package com.codephillip.andenginestep;

import android.view.KeyEvent;

import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import java.io.IOException;

public class MainActivity extends BaseGameActivity {

    // ====================================================
    // CONSTANTS
    // ====================================================
    // We define these constants to setup the game to use an
    // appropriate camera resolution independent of the actual
    // end-user's screen resolution.

    // The resolution of the screen with which you are developing.
    static float DESIGN_SCREEN_WIDTH_PIXELS = 800f;
    static float DESIGN_SCREEN_HEIGHT_PIXELS = 480f;
    // The physical size of the screen with which you are developing.
    static float DESIGN_SCREEN_WIDTH_INCHES = 4.472441f; // 1 inche = 25.4mm   Width = 93mm
    static float DESIGN_SCREEN_HEIGHT_INCHES = 2.805118f; // 1 inche = 25.4mm   Height = 56mm
    // Define a minimum and maximum screen resolution (to prevent
    // cramped or overlapping screen elements).
    static float MIN_WIDTH_PIXELS = 320f, MIN_HEIGHT_PIXELS = 240f;
    static float MAX_WIDTH_PIXELS = 1600f, MAX_HEIGHT_PIXELS = 960f;

    // ====================================================
    // VARIABLES
    // ====================================================
    // These variables will be set in onCreateEngineOptions().
    public float cameraWidth;
    public float cameraHeight;
    public float actualScreenWidthInches;
    public float actualScreenHeightInches;

    // If a Layer is open when the Back button is pressed, hide the layer.
    // If a Game scene or non-MainMenu is active, go back to the MainMenu.
    // Otherwise, exit the game.
    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if(ResourceManager.getInstance().engine!=null){
                if(SceneManager.getInstance().isLayerShown)
                    SceneManager.getInstance().currentLayer.onHideLayer();
                else if(SceneManager.getInstance().mCurrentScene.getClass().getGenericSuperclass().equals(ManagedGameScene.class) ||
                        (SceneManager.getInstance().mCurrentScene.getClass().getGenericSuperclass().equals(ManagedMenuScene.class) &!
                                SceneManager.getInstance().mCurrentScene.getClass().equals(MainMenu.class)))
                    SceneManager.getInstance().showMainMenu();
                else
                    System.exit(0);
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    @Override
    public EngineOptions onCreateEngineOptions() {
        return null;
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {

    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {

    }

    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {

    }
}

//    private Camera camera;
//
//    private static int CAMERA_WIDTH = 480;
//    private static int CAMERA_LENGTH = 800;
//
//    @Override
//    protected void onCreateResources() throws IOException {
//
//    }
//
//    @Override
//    protected Scene onCreateScene() {
//        Scene scene = new Scene();
//        scene.setBackground(new Background(Color.GREEN));
//        return scene;
//    }
//
//    @Override
//    public EngineOptions onCreateEngineOptions() {
//        camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_LENGTH);
//        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), camera);
//        return engineOptions;
//    }
//}
