package com.papiricoh.rpggame.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.papiricoh.rpggame.RPGGame;
import com.papiricoh.rpggame.controller.DIRECTION;
import com.papiricoh.rpggame.model.world.Chunk;
import com.papiricoh.rpggame.model.world.World;
import com.papiricoh.rpggame.util.AnimationSet;

public class Actor {
    private static final float REFACE_TIME = 0.1f;
    private World world;
    private int x;
    private int y;
    private DIRECTION facing;

    //Camera and Sprite Animation
    private float worldX, worldY;
    private int srcX, srcY;
    private int destX, destY;
    private float animTimer;

    private float ANIM_TIME = 0.2f;

    private float walkTimer;
    private boolean moveRequestThisFrame;

    private ACTOR_STATE state;

    private AnimationSet animations;

    public Actor(World world, int x, int y, AnimationSet animations) {

        this.world = world;
        this.x = x;
        this.y = y;
        this.worldX = x;
        this.worldY = y;
        this.animations = animations;
        this.world.getMap().getTile(x, y).setActor(this);
        this.state = ACTOR_STATE.STANDING;
        this.facing = DIRECTION.SOUTH;
    }

    public float getWorldX() {
        return worldX;
    }

    public float getWorldY() {
        return worldY;
    }

    public boolean reface(DIRECTION dir) {
        if (state != ACTOR_STATE.STANDING) { // can only reface when standing
            return false;
        }
        if (facing == dir) { // can't reface if we already face a direction
            return true;
        }
        facing = dir;
        state = ACTOR_STATE.REFACING;
        animTimer = 0f;
        return true;
    }

    public enum ACTOR_STATE {
        WALKING,
        STANDING,
        REFACING,
    }
    public void update(float delta) {
        if (state == ACTOR_STATE.WALKING) {
            animTimer += delta;
            walkTimer += delta;
            worldX = Interpolation.linear.apply(srcX, destX, animTimer/ANIM_TIME);
            worldY = Interpolation.linear.apply(srcY, destY, animTimer/ANIM_TIME);
            if(animTimer > ANIM_TIME) {
                float leftOverTime = animTimer-ANIM_TIME;
                walkTimer -= leftOverTime;
                finishMove();
                if (moveRequestThisFrame) {
                    move(facing);
                }else {
                    walkTimer = 0f;
                }
            }
        }
        if (state == ACTOR_STATE.REFACING) {
            animTimer += delta;
            if( animTimer > REFACE_TIME) {
                state = ACTOR_STATE.STANDING;
            }
        }
        moveRequestThisFrame = false;
    }



    public boolean move(DIRECTION dir){
        if (state == ACTOR_STATE.WALKING) {
            if(facing == dir) {
                moveRequestThisFrame = true;
            }
            return false;
        }
        if(!world.getMap().getTile(x+dir.getDx(), y+dir.getDy()).isWalkable()) {
            return false;
        }
        /*if(x+dir.getDx() > world.getMap().getWidth() - 1 || x+dir.getDx() < 0 || y+dir.getDy() > world.getMap().getHeight() - 1 || y+dir.getDy() < 0) {
            return false;
        }*/
        if (world.getMap().getTile(x+dir.getDx(), y+dir.getDy()).getActor() != null) {
            return false;
        }
        /*if(world.getObjectByCoord(x+dir.getDx(), y+dir.getDy()) != null && world.getObjectByCoord(x+dir.getDx(), y+dir.getDy()).isCollision()) {
            return false;
        }*/
        initializeMove(dir);
        this.world.getMap().getTile(x,y).setActor(null);
        this.x += dir.getDx();
        this.y += dir.getDy();
        this.world.getMap().getTile(x,y).setActor(this);
        return true;
    }

    public void initializeMove(DIRECTION dir) {
        this.facing = dir;
        this.srcX = x;
        this.srcY = y;
        this.destX = x + dir.getDx();
        this.destY = y + dir.getDy();
        animTimer = 0f;
        state = ACTOR_STATE.WALKING;

    }

    public void finishMove() {
        state = ACTOR_STATE.STANDING;
        this.worldX = destX;
        this.worldY = destY;
        this.srcX = 0;
        this.srcY = 0;
        this.destX = 0;
        this.destY = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector2 getChunkCoords() {
        Chunk chunk = RPGGame.getGameScreen().getWorldManager().getWorld().getMap().getChunk(getX(), getY());
        return new Vector2(chunk.getX(), chunk.getY());
    }

    public TextureRegion getSprite() {
        if(state == ACTOR_STATE.WALKING) {

            return animations.getWalking(facing).getKeyFrame(walkTimer);
        } else if (state == ACTOR_STATE.STANDING) {
            return animations.getStanding(facing);
        } else if (state == ACTOR_STATE.REFACING) {
            return animations.getWalking(facing).getKeyFrames()[0];
        }
        return animations.getStanding(DIRECTION.SOUTH);
    }

}