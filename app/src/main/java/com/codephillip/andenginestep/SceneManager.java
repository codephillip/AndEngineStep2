package com.codephillip.andenginestep;

import android.util.Log;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

/**
 * Created by codephillip on 7/15/16.
 */
public class SceneManager {
    private static final String TAG = SceneManager.class.getSimpleName();
    private AllScenes currentScene;
    private BaseGameActivity activity;
    private Engine engine;
    private Camera camera;
    private BitmapTextureAtlas splashTextureAtlas;
    private ITextureRegion splashTextureRegion;
    private Scene splashScene;
    private static Scene gameScene;
    private static Scene menuScene;
    private SceneManager INSTANCE = null;

    public SceneManager getInstance(){
        return INSTANCE = new SceneManager();
    }


//    public SceneManager(BaseGameActivity act, Engine eng, Camera cam) {
//        this.activity = act;
//        this.engine = eng;
//        this.camera = cam;
//    }

    public AllScenes getCurrentScene() {
        return currentScene;
    }

    public static void loadSplashResources() {
        ResourceManager.loadSplashScreeResources();
//        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
//        splashTextureAtlas = new BitmapTextureAtlas(engine.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
//        splashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, this.activity, "splash.png", 0, 0);
//        splashTextureAtlas.load();
    }

    public void loadGameResources() {

    }

    public static void loadMenuResources() {
        ResourceManager.loadSplashScreeResources();
    }

    public static Scene createSplashScene() {
        SplashScene splashScene = new SplashScene(ResourceManager.getInstance().context, ResourceManager.getInstance().engine);
        splashScene.attachChild(null);
        splashScene.registerTouchArea(null);
        return splashScene;
    }

    public static Scene createMenuScene() {
        MenuScene menuScene = new MenuScene(ResourceManager.getInstance().context, ResourceManager.getInstance().engine);
        menuScene.attachChild(null);
        menuScene.registerTouchArea(null);
        Log.d(TAG, "createMenuScene: finished");
        return menuScene;
//        menuScene = new Scene();
//        menuScene.setBackground(new Background(1, 1, 1));
//
//        Sprite sprite = new Sprite(camera.getWidth() / 2, camera.getHeight() / 2, splashTextureRegion, engine.getVertexBufferObjectManager());
//        menuScene.attachChild(sprite);
//
//        return menuScene;
    }

    public Scene createGameScene() {
       return null;
    }

    public static void setCurrentScene(AllScenes currentScene, Scene scene) {
        //after we set the enum's current placeholder
        //we're going to tell the engine to go to the
        //newly made current scene.
        switch (currentScene) {
            case SPLASH:
                Log.d(TAG, "setCurrentScene: SPLASH SCENE");
                ResourceManager.getInstance().engine.setScene(scene);
                //this case we don't really have to worry about
                break;
            case MENU:
                Log.d(TAG, "setCurrentScene: MENU SCENE");
                //once we setup the engine we'll tell it
                //to load the menu scene
                ResourceManager.getInstance().engine.setScene(scene);
                break;
            case GAME:
                //once we setup the engine we'll tell it
                //to load the game scene
                ResourceManager.getInstance().engine.setScene(scene);
                break;
            default:
                throw new UnsupportedOperationException("Unknown sceen");
        }
    }
}
