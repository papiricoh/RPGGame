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
import com.papiricoh.pokegame.controller.DialogueController;
import com.papiricoh.pokegame.controller.PlayerController;
import com.papiricoh.pokegame.dialogue.Dialogue;
import com.papiricoh.pokegame.dialogue.DialogueNode;
import com.papiricoh.pokegame.model.Actor;
import com.papiricoh.pokegame.model.Camera;
import com.papiricoh.pokegame.model.world.World;
import com.papiricoh.pokegame.model.world.objects.PokeballWorldObject;
import com.papiricoh.pokegame.model.world.objects.TreeWorldObject;
import com.papiricoh.pokegame.screen.ui.DialogueBox;
import com.papiricoh.pokegame.screen.ui.OptionBox;
import com.papiricoh.pokegame.util.AnimationSet;
import sun.reflect.generics.tree.Tree;

import java.util.Random;

public class GameScreen extends AbstractScreen {

    private DialogueController dialogueController;
    private Dialogue dialogue;
    private World world;
    private Actor player;
    private InputMultiplexer multiplexer;
    private PlayerController playerController;
    private Camera camera;

    private SpriteBatch batch;

    private int ui_scale = 2;
    private Stage uiStage;
    private Table root;
    private Viewport gameViewport;
    private DialogueBox dialogueBox;
    private OptionBox optionBox;

    private int xmasCounter;
    private int xmasTotalTrees;


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
        //world.addObject(new PokeballWorldObject(1,1));

        player = new Actor(world, 10, 10, animations);

        for (int x = 0; x < world.getMap().getWidth(); x++) {
            for (int y = 0; y < world.getMap().getHeight(); y++) {
                int rn = new Random().nextInt(20);
                if (rn == 0 && (x != player.getX() && y != player.getY())) {
                    xmasTotalTrees++;
                    world.addObject(new TreeWorldObject(x,y));
                }
            }
        }


        initUI();

        multiplexer = new InputMultiplexer();
        playerController = new PlayerController(player);
        dialogueController = new DialogueController(dialogueBox, optionBox);
        multiplexer.addProcessor(playerController);
        multiplexer.addProcessor(dialogueController);
        dialogue = new Dialogue();

        DialogueNode node1 = new DialogueNode("Recolecta todos los arboles que puedas" ,0);

        /*DialogueNode node2 = new DialogueNode("The objective of this game is to get the trees\nWanna play?" ,1);
        DialogueNode node3 = new DialogueNode("Yes" ,2);
        DialogueNode node4 = new DialogueNode("No" ,3);
        DialogueNode node5 = new DialogueNode("Cool" ,4);

        node1.makeLinear(node2.getID());
        node2.addChoice("Yes", 4);
        node2.addChoice("No", 1);

        dialogue.addNode(node1);
        dialogue.addNode(node2);
        dialogue.addNode(node3);
        dialogue.addNode(node4);
        */
        dialogue.addNode(node1);

        //dialogueController.startDialogue(dialogue);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        playerController.update(delta);
        dialogueController.update(delta);

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

        batch.draw(player.getSprite(), worldStartX + player.getWorldX() * Settings.SCALED_TILE_SIZE, worldStartY + player.getWorldY() * Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE + 32);
        batch.end();


        if(world.getObjectByCoord(player.getX(), player.getY()) != null && world.getObjectByCoord(player.getX(), player.getY()) instanceof TreeWorldObject) {
            xmasCounter++;
            world.deleteObjectByCoord(player.getX(), player.getY());

            if (xmasCounter >= xmasTotalTrees) {
                dialogue = new Dialogue();
                DialogueNode node1 = new DialogueNode("Has recolectado todos los arboles de navidad\nHAS GANADO!!!!" ,0);
                dialogue.addNode(node1);
                dialogueController.startDialogue(dialogue);
            } else {
                dialogue = new Dialogue();
                DialogueNode node1 = new DialogueNode("Has recolectado " + xmasCounter + " arboles de navidad" ,0);
                dialogue.addNode(node1);
                dialogueController.startDialogue(dialogue);
            }
        }

        uiStage.draw();
    }

    private void initUI() {
        uiStage = new Stage(new ScreenViewport());
        uiStage.getViewport().update(Gdx.graphics.getWidth()/ui_scale, Gdx.graphics.getHeight()/ui_scale, true);
        //uiStage.setDebugAll(true); //UI DEBUG MODE

        root = new Table();
        root.setFillParent(true);
        uiStage.addActor(root);
        Table dialogTable = new Table();

        dialogueBox = new DialogueBox(getApp().getSkin());
        dialogueBox.setVisible(false);
        optionBox = new OptionBox(getApp().getSkin());
        optionBox.setVisible(false);
        dialogTable.add(optionBox);
        dialogTable.add(dialogueBox);

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
