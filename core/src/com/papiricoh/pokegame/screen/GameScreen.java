package com.papiricoh.pokegame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.papiricoh.pokegame.PokeGame;
import com.papiricoh.pokegame.Settings;
import com.papiricoh.pokegame.controller.OptionBoxController;
import com.papiricoh.pokegame.controller.PlayerController;
import com.papiricoh.pokegame.model.Actor;
import com.papiricoh.pokegame.model.Camera;
import com.papiricoh.pokegame.model.world.World;
import com.papiricoh.pokegame.model.world.objects.PokeballWorldObject;
import com.papiricoh.pokegame.screen.ui.DialogueBox;
import com.papiricoh.pokegame.screen.ui.OptionBox;
import com.papiricoh.pokegame.util.AnimationSet;

public class GameScreen extends AbstractScreen {

    private World world;
    private Actor player;
    private InputMultiplexer multiplexer;
    private PlayerController playerController;
    private OptionBoxController optionsController;
    private Camera camera;

    private SpriteBatch batch;

    private int ui_scale = 2;
    private Stage uiStage;
    private Table root;
    private Viewport gameViewport;
    private DialogueBox dialogueBox;
    private OptionBox optionBox;


    public GameScreen(PokeGame app) {
        super(app);
        gameViewport = new ScreenViewport();
        batch = new SpriteBatch();

        TextureAtlas atlas = app.getAssetManager().get("player/playerTextures.atlas", TextureAtlas.class);

        AnimationSet animations = new AnimationSet(
                new Animation(0.3f/2f, atlas.findRegions("player_walking_north"), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3f/2f, atlas.findRegions("player_walking_south"), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3f/2f, atlas.findRegions("player_walking_west"), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3f/2f, atlas.findRegions("player_walking_east"), Animation.PlayMode.LOOP_PINGPONG),

                atlas.findRegion("player_standing_north"), atlas.findRegion("player_standing_south"),
                atlas.findRegion("player_standing_west"), atlas.findRegion("player_standing_east")

                );

        camera = new Camera();
        world = new World(20, 20);
        world.addObject(new PokeballWorldObject(1,1));
        player = new Actor(world, 0, 0, animations);

        initUI();

        multiplexer = new InputMultiplexer();
        playerController = new PlayerController(player);
        optionsController = new OptionBoxController(optionBox);
        multiplexer.addProcessor(playerController);
        multiplexer.addProcessor(optionsController);

    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(playerController);
    }

    @Override
    public void render(float delta) {
        playerController.update(delta);

        player.update(delta);
        camera.update(player.getWorldX()+0.5f, player.getWorldY()+0.5f);
        uiStage.act(delta);

        gameViewport.apply();
        batch.begin();
        //TODO: WorldManager.java
        float worldStartX = (float) Gdx.graphics.getWidth() / 2 - camera.getCameraX() * Settings.SCALED_TILE_SIZE;
        float worldStartY = (float) Gdx.graphics.getHeight() / 2 - camera.getCameraY() * Settings.SCALED_TILE_SIZE;

        int mapWidth = world.getMap().getWidth();
        int mapHeight = world.getMap().getHeight();
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                batch.draw(world.getMap().getTile(x, y).getTexture(), worldStartX + x * Settings.SCALED_TILE_SIZE, worldStartY + y * Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE);
            }
        }

        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                if (world.getObjectByCoord(x, y) != null) {
                    batch.draw(world.getObjectByCoord(x, y).getTexture(), worldStartX + x * Settings.SCALED_TILE_SIZE, worldStartY + y * Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE);
                }
            }
        }

        batch.draw(player.getSprite(), worldStartX + player.getWorldX() * Settings.SCALED_TILE_SIZE, worldStartY + player.getWorldY() * Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE);
        batch.end();

        uiStage.draw();
    }

    private void initUI() {
        uiStage = new Stage(new ScreenViewport());
        uiStage.getViewport().update(Gdx.graphics.getWidth()/ui_scale, Gdx.graphics.getHeight()/ui_scale, true);
        //uiStage.setDebugAll(true); //UI DEBUG MODE

        root = new Table();
        root.setFillParent(true);
        uiStage.addActor(root);
        /*
        dialogueBox = new DialogueBox(getApp().getSkin());
        dialogueBox.animateText("\"Luis es tonto JEJEJJEJEJ\"\n - A-Lopecin");
         */

        Table dialogTable = new Table();
        optionBox = new OptionBox(getApp().getSkin());
        optionBox.addOption("Option1");
        optionBox.addOption("Option2");
        optionBox.addOption("Option3");
        dialogTable.add(optionBox);

        root.add(dialogTable).expand().align(Align.bottom);
    }

    @Override
    public void resize(int width, int height) {
        batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
        uiStage.getViewport().update(width/ui_scale, height/ui_scale, true);
        gameViewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
