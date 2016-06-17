package com.codephillip.andenginestep;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import java.io.IOException;

public class MainActivity extends BaseGameActivity {

    private Camera camera;
    private Scene scene;
    private static int CAMERA_WIDTH = 800;
    private static int CAMERA_HEIGHT = 480;

    private static final int MUTED = 1;
    private static final int UNMUTED = 0;

    private ITiledTextureRegion buttonTextureRegion;
    private BitmapTextureAtlas bitmapTextureAtlas;
    private TiledSprite muteTiledSprite;

    private Music music;


    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), camera);
        engineOptions.getAudioOptions().setNeedsSound(true);
        engineOptions.getAudioOptions().setNeedsMusic(true);
        return engineOptions;
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        bitmapTextureAtlas = new BitmapTextureAtlas(mEngine.getTextureManager(), 64, 32, TextureOptions.BILINEAR);
        buttonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas, this, "faces.png",0 ,0 , 2, 1);
        bitmapTextureAtlas.load();

        music = MusicFactory.createMusicFromAsset(mEngine.getMusicManager(), this, "mfx/gameSound.mp3");
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {
        scene = new Scene();
        pOnCreateSceneCallback.onCreateSceneFinished(scene);
    }

    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
        muteTiledSprite = new TiledSprite(CAMERA_WIDTH * 0.5f, CAMERA_HEIGHT * 0.5f, buttonTextureRegion, mEngine.getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if(pSceneTouchEvent.isActionDown()){
                    if (music.isPlaying()){
                        music.pause();
                        muteTiledSprite.setCurrentTileIndex(MUTED);
                    } else {
                        music.play();
                        muteTiledSprite.setCurrentTileIndex(UNMUTED);
                    }
                    return true;
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };

        muteTiledSprite.setCurrentTileIndex(UNMUTED);
        music.setLooping(true);
        music.play();

        scene.registerTouchArea(muteTiledSprite);
        scene.attachChild(muteTiledSprite);
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }
}

//    @Override
//    protected void onCreateResources() throws IOException {
//        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
//        bitmapTextureAtlas = new BitmapTextureAtlas(mEngine.getTextureManager(), 512, 64, TextureOptions.BILINEAR);
//        //column then row
//        buttonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas, this, "button01.png", 0, 0, 2, 1);
//        bitmapTextureAtlas.load();
//    }
//
//    @Override
//    protected Scene onCreateScene() {
//        Scene scene = new Scene();
//        scene.setTouchAreaBindingOnActionDownEnabled(true);
//        scene.setBackground(new Background(Color.GREEN));
//
//        final ButtonSprite buttonSprite = new ButtonSprite(CAMERA_WIDTH * 0.5f, CAMERA_HEIGHT * 0.5f, buttonTextureRegion, mEngine.getVertexBufferObjectManager()){
//            @Override
//            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
//
//                if (pSceneTouchEvent.isActionDown()){
//                    MainActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//
//                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
//            }
//        };
//
//        scene.registerTouchArea(buttonSprite);
//        scene.attachChild(buttonSprite);
//
//        return scene;
//    }
//
//    @Override
//    public EngineOptions onCreateEngineOptions() {
//        camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
//        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), camera);
//        return engineOptions;
//    }
//}
