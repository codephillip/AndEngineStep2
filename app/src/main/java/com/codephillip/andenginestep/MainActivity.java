package com.codephillip.andenginestep;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.color.Color;

import java.io.IOException;

public class MainActivity extends SimpleBaseGameActivity {

    private Camera camera;

    public static int CAMERA_WIDTH = 480;
    public static int CAMERA_LENGTH = 640;

    private BitmapTextureAtlas bitmapTextureAtlas;
    private TextureRegion circleRegion;
    private Circle sprite;

    @Override
    protected void onCreateResources() throws IOException {
        //create BitmapTextureAtlas of size 256*256
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        bitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 128, 128, TextureOptions.DEFAULT);
        circleRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, this,"rsz_circle.png",0 ,0 );
        bitmapTextureAtlas.load();
    }

    @Override
    protected Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());
        sprite = new Circle(300, 300, circleRegion, this.getVertexBufferObjectManager());
        Scene scene = new Scene();
        scene.attachChild(sprite);
        scene.registerTouchArea(sprite);
        scene.setBackground(new Background(Color.GREEN));
        return scene;
    }

    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_LENGTH);
//        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), camera);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_LENGTH), camera);
        return engineOptions;
    }
}
