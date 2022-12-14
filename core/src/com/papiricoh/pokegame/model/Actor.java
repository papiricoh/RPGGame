package com.papiricoh.pokegame.model;

import com.badlogic.gdx.math.Interpolation;

public class Actor {
    private TileMap map;
    private int x;
    private int y;

    //Camera and Sprite Animation
    private float worldX, worldY;
    private int srcX, srcY;
    private int destX, destY;
    private float animTimer;
    private float ANIM_TIME = 0.5f;

    private ACTOR_STATE state;

    public Actor(TileMap map, int x, int y) {
        this.map = map;
        this.x = x;
        this.y = y;
        this.map.getTile(x, y).setActor(this);
        this.state = ACTOR_STATE.STANDING;
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
            worldX = Interpolation.pow2.apply(srcX, destX, animTimer/ANIM_TIME);
            worldY = Interpolation.pow2.apply(srcY, destY, animTimer/ANIM_TIME);
            if(animTimer > ANIM_TIME) {
                finishMove();
            }
        }
    }

    public boolean move(int dx, int dy){
        if (state != ACTOR_STATE.STANDING) {
            return false;
        }
        if(x+dx > map.getWidth() || x+dx < 0 || y+dy > map.getHeight() || y+dy < 0) {
            return false;
        }
        if (map.getTile(x+dx, y+dy).getActor() != null) {
            return false;
        }
        initializeMove(x, y, dx, dy);
        this.map.getTile(x,y).setActor(null);
        this.x += dx;
        this.y += dy;
        this.map.getTile(x,y).setActor(this);
        return true;
    }

    public void initializeMove(int oldX, int oldY, int dx, int dy) {
        this.srcX = oldX;
        this.srcY = oldY;
        this.destX = oldX + dx;
        this.destY = oldY + dy;
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
}