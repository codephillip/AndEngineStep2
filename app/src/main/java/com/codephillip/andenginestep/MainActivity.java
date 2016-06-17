package com.codephillip.andenginestep;

import android.widget.Toast;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.color.Color;

import java.io.IOException;

public class MainActivity extends SimpleBaseGameActivity {

    private Camera camera;

    private static int CAMERA_WIDTH = 800;
    private static int CAMERA_LENGTH = 480;

    private ITiledTextureRegion buttonTextureRegion;
    private BitmapTextureAtlas bitmapTextureAtlas;

    @Override
    protected void onCreateResources() throws IOException {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        bitmapTextureAtlas = new BitmapTextureAtlas(mEngine.getTextureManager(), 512, 64, TextureOptions.BILINEAR);
        //column then row
        buttonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas, this, "button01.png", 0, 0, 2, 1);
        bitmapTextureAtlas.load();
    }

    @Override
    protected Scene onCreateScene() {
        Scene scene = new Scene();
        scene.setTouchAreaBindingOnActionDownEnabled(true);
        scene.setBackground(new Background(Color.GREEN));

        final ButtonSprite buttonSprite = new ButtonSprite(CAMERA_WIDTH * 0.5f, CAMERA_LENGTH * 0.5f, buttonTextureRegion, mEngine.getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (pSceneTouchEvent.isActionDown()){
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };

        scene.registerTouchArea(buttonSprite);
        scene.attachChild(buttonSprite);

        return scene;
    }

    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_LENGTH);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), camera);
        return engineOptions;
    }
}
