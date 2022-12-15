package com.papiricoh.pokegame.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.papiricoh.pokegame.controller.DIRECTION;
import com.papiricoh.pokegame.util.AnimationSet;

public class Actor {
    private TileMap map;
    private int x;
    private int y;
    private DIRECTION facing;

    //Camera and Sprite Animation
    private float worldX, worldY;
    private int srcX, srcY;
    private int destX, destY;
    private float animTimer;
    private float ANIM_TIME = 0.5f;

    private float walkTimer;
    private boolean moveRequestThisFrame;

    private ACTOR_STATE state;

    private AnimationSet animations;

    public Actor(TileMap map, int x, int y, AnimationSet animations) {
        this.map = map;
        this.x = x;
        this.y = y;
        this.worldX = x;
        this.worldY = y;
        this.animations = animations;
        this.map.getTile(x, y).setActor(this);
        this.state = ACTOR_STATE.STANDING;
        this.facing = DIRECTION.SOUTH;
    }

    public float getWorldX() {
        return worldX;
    }

    public float getWorldY() {
        return worldY;
    }

    public enum ACTOR_STATE {
        WALKING,
        STANDING
    }
    public void update(float delta) {
        if (state == ACTOR_STATE.WALKING) {
            animTimer += delta;
            walkTimer += delta;
            worldX = Interpolation.pow2.apply(srcX, destX, animTimer/ANIM_TIME);
            worldY = Interpolation.pow2.apply(srcY, destY, animTimer/ANIM_TIME);
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
        moveRequestThisFrame = false;
    }

    public boolean move(DIRECTION dir){
        if (state == ACTOR_STATE.WALKING) {
            if(facing == dir) {
                moveRequestThisFrame = true;
            }
            return false;
        }
        if(x+dir.getDx() > map.getWidth() - 1 || x+dir.getDx() < 0 || y+dir.getDy() > map.getHeight() - 1 || y+dir.getDy() < 0) {
            return false;
        }
        if (map.getTile(x+dir.getDx(), y+dir.getDy()).getActor() != null) {
            return false;
        }
        initializeMove(dir);
        this.map.getTile(x,y).setActor(null);
        this.x += dir.getDx();
        this.y += dir.getDy();
        this.map.getTile(x,y).setActor(this);
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

    public TextureRegion getSprite() {
        if(state == ACTOR_STATE.WALKING) {

            return animations.getWalking(facing).getKeyFrame(walkTimer);
        } else if (state == ACTOR_STATE.STANDING) {
            return animations.getStanding(facing);
        }
        return animations.getStanding(DIRECTION.SOUTH);
    }
}