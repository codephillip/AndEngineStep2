package com.codephillip.andenginestep;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.RepeatingSpriteBackground;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.AssetBitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.BaseGameActivity;

import java.io.IOException;

/**
 * The SpriteBackground class allows us to attach a single Sprite object to our scene
 *as a background image
 */

public class MainActivity extends BaseGameActivity {

    private static final String TAG = "background";
    private Camera camera;
    private Scene scene;
    private static int CAMERA_WIDTH = 800;
    private static int CAMERA_HEIGHT = 480;

//    private BitmapTextureAtlas backgroundBitmapTextureAtlas;
//    private ITextureRegion backgroundTextureRegion;

    private AssetBitmapTexture assetBitmapTexture;
    private ITextureRegion repeatingTextureRegion;


    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), camera);
        return engineOptions;
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
//        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
//        backgroundBitmapTextureAtlas = new BitmapTextureAtlas(mEngine.getTextureManager(), 800, 600, BitmapTextureFormat.RGB_565);
//        backgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(backgroundBitmapTextureAtlas, this, "background.png", 0, 0);
//        backgroundBitmapTextureAtlas.load();

        assetBitmapTexture = new AssetBitmapTexture(mEngine.getTextureManager(), getAssets(), "gfx/profilepic.png", TextureOptions.REPEATING_BILINEAR);
        assetBitmapTexture.load();

        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {
        scene = new Scene();
        pOnCreateSceneCallback.onCreateSceneFinished(scene);
    }

    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {

//        /* Create a rectangle in the bottom left corner of the Scene */
//        Rectangle rectangleLeft = new Rectangle(100, 100, 200, 200, mEngine.getVertexBufferObjectManager());
///* Create a rectangle in the top right corner of the Scene */
//        Rectangle rectangleRight = new Rectangle(CAMERA_WIDTH - 100, CAMERA_HEIGHT - 100, 200, 200, mEngine.getVertexBufferObjectManager());
//
////        Entity backgroundEntity = new Entity();
////
////        backgroundEntity.attachChild(rectangleLeft);
////        backgroundEntity.attachChild(rectangleRight);
//
//        final float positionX = CAMERA_WIDTH * 0.5f;
//        final float positionY = CAMERA_HEIGHT * 0.5f;
//
//        Sprite sprite = new Sprite(positionX, positionY, backgroundTextureRegion, mEngine.getVertexBufferObjectManager());
//
//
//        /* Define the background color properties */
//        final float red = 0;
//        final float green = 1;
//        final float blue = 0;
///* Create the EntityBackground, specifying its background color &
//entity to represent the background image */
////        EntityBackground background = new EntityBackground(red, green,
////                blue, backgroundEntity);
///* Set & enable the background */
//
//        SpriteBackground background = new SpriteBackground(red, green, blue, sprite);


        repeatingTextureRegion = TextureRegionFactory.extractFromTexture(assetBitmapTexture);

        float repeatingScale = 0.5f;
//        float repeatingScale = 1f;
        RepeatingSpriteBackground  background= new RepeatingSpriteBackground(CAMERA_WIDTH, CAMERA_HEIGHT, repeatingTextureRegion, repeatingScale ,mEngine.getVertexBufferObjectManager());

        scene.setBackground(background);
        scene.setBackgroundEnabled(true);

        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }
}
