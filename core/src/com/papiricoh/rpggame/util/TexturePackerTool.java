package com.papiricoh.rpggame.util;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TexturePackerTool {
    public static void main(String[] args) {
        TexturePacker.process("res/unpacked/images", "res/packed", "textures");
    }
}
