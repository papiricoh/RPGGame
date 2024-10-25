package com.papiricoh.rpggame.util;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
    public static Texture pixelTexture;

    public static void load() {
        // Crear un Pixmap de 1x1 píxel
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 1, 1, 1); // Color blanco, opaco
        pixmap.fill(); // Llenar el pixmap con este color

        // Crear una textura a partir del Pixmap
        pixelTexture = new Texture(pixmap);

        // Liberar el Pixmap ya que no lo necesitamos más
        pixmap.dispose();
    }

    public static void dispose() {
        if (pixelTexture != null) {
            pixelTexture.dispose();
        }
    }
}
