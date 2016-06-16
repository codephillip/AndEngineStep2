package com.codephillip.andenginestep;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import java.io.IOException;

public class MainActivity extends SimpleBaseGameActivity {

    private Camera camera;

    private static int CAMERA_WIDTH = 800;
    private static int CAMERA_LENGTH = 480;

//    private BuildableBitmapTextureAtlas buildableBitmapTextureAtlas;
    private BitmapTextureAtlas bitmapTextureAtlas;
    private ITextureRegion iTextureRegion;

    @Override
    protected void onCreateResources() throws IOException {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        //BuildableBitmapTextureAtlas auto scales
//        buildableBitmapTextureAtlas = new BuildableBitmapTextureAtlas(mEngine.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        bitmapTextureAtlas = new BitmapTextureAtlas(mEngine.getTextureManager(), 1024, 1024, TextureOptions.DEFAULT);
        iTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, this, "background1024.jpg", 0, 0);
        bitmapTextureAtlas.load();

//        try {
//            buildableBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0,1,1));
//        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
//            e.printStackTrace();
//        }
//
//        buildableBitmapTextureAtlas.load();
    }

    @Override
    protected Scene onCreateScene() {

        final float positionX = CAMERA_WIDTH * 0.5f;
        final float positionY = CAMERA_LENGTH * 0.5f;
        Sprite sprite = new Sprite(positionX, positionY, iTextureRegion, mEngine.getVertexBufferObjectManager());

        Scene scene = new Scene();
        scene.attachChild(sprite);
//        scene.setBackground(new Background(Color.GREEN));
        return scene;
    }

    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_LENGTH);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), camera);
        return engineOptions;
    }
}
