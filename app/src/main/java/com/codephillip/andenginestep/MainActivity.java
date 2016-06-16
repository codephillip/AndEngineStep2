package com.codephillip.andenginestep;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
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

    private BitmapTextureAtlas bitmapTextureAtlas;

    private ITiledTextureRegion iTiledTextureRegion;
    private AnimatedSprite walkSprite;

    @Override
    protected void onCreateResources() throws IOException {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        //bitmapTextureAtlas of size 512 by 512
        //TextureOptions.BILINEAR_PREMULTIPLYALPHA smoothes out the pixel distortion
        bitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        iTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas, this, "walk2.png", 0, 0, 8, 1);
        /** [NOTE] load the BitmapTextureAtlas*/
        bitmapTextureAtlas.load();
    }

    @Override
    protected Scene onCreateScene() {
        Scene scene = new Scene();

        // 60, 60 is the position the sprite will be place
        walkSprite = new AnimatedSprite(120, 120, iTiledTextureRegion, this.getVertexBufferObjectManager());
        walkSprite.animate(50);
        scene.attachChild(walkSprite);
        scene.setBackground(new Background(Color.WHITE));
        return scene;
    }

    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_LENGTH);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), camera);
        return engineOptions;
    }
}


//    private BitmapTextureAtlas texCat;
//    private TiledTextureRegion regCat;
//    private AnimatedSprite  sprCat;
//    private Scene m_Scene;
//    private Camera    m_Camera;
//    private static int   SPR_COLUMN  = 2;
//    private static int   SPR_ROWS  = 4;
//
//    private static final int CAMERA_WIDTH = 800;
//    private static final int CAMERA_HEIGHT = 480;
//
//    @Override
//    public EngineOptions onCreateEngineOptions()
//    {
//        m_Camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
//        EngineOptions en = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(
//                CAMERA_WIDTH, CAMERA_HEIGHT), m_Camera);
//        return en;
//    }
//
//    @Override
//    protected void onCreateResources()
//    {
//        texCat = new BitmapTextureAtlas(this.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
//        regCat = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(texCat, this.getAssets(),"gfx/runningcat.png", 0, 0, SPR_COLUMN, SPR_ROWS);
//        texCat.load();
//    }
//
//    @Override
//    protected Scene onCreateScene()
//    {
//        m_Scene = new Scene();
//        m_Scene.setBackground(new Background(Color.WHITE));
//
//        sprCat = new AnimatedSprite(240, 240, regCat, this.getVertexBufferObjectManager());
//        m_Scene.attachChild(sprCat);
//
//        sprCat.animate(100);
//        return m_Scene;
//    }
//}