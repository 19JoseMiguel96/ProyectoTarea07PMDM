package com.planetas3d.jose;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;

public class AssetsJuego {
    public static Texture texturaSol, texturaMercurio, texturaVenus, texturaLaTierra;
    private static Mesh elemento3D;
    /**
     * Método encargado de cargar todas las texturas.
     */
    public static void cargarElementos3d(){
        AssetManager assetManager = new AssetManager();
        assetManager.load("Modelos/Sol.g3db", Model.class);
        assetManager.load("Modelos/Mercurio.g3db", Model.class);
        assetManager.load("Modelos/Venus.g3db", Model.class);
        assetManager.load("Modelos/Tierra.g3db", Model.class);
        assetManager.finishLoading();
        Model model = assetManager.get("Modelos/Sol.g3db", Model.class);
        elemento3D = model.meshes.get(0);
        FileHandle imageFileHandle = Gdx.files.internal("Modelos/sphere1_auv.png");
        texturaSol = new Texture(imageFileHandle);

        model = assetManager.get("Modelos/Mercurio.g3db", Model.class);
        elemento3D = model.meshes.get(0);
        imageFileHandle = Gdx.files.internal("Modelos/Mercurio.png");
        texturaMercurio= new Texture(imageFileHandle);

        model = assetManager.get("Modelos/Venus.g3db", Model.class);
        elemento3D = model.meshes.get(0);
        imageFileHandle = Gdx.files.internal("Modelos/Venus.png");
        texturaVenus= new Texture(imageFileHandle);

        model = assetManager.get("Modelos/Tierra.g3db", Model.class);
        elemento3D = model.meshes.get(0);
        imageFileHandle = Gdx.files.internal("Modelos/Tierra.png");
        texturaLaTierra= new Texture(imageFileHandle);
    }

    /**
     * Método encargado de liberar todas as texturas.
     */
    public static void liberarElementos3d(){
        texturaSol.dispose();
        elemento3D.dispose();
    }
    public static Mesh getElemento3d(){
        return elemento3D;
    }
    public static Texture getTexturaSol(){
        return texturaSol;
    }
    public static Texture getTexturaMercurio(){
        return texturaMercurio;
    }
    public static Texture getTexturaVenus(){
        return texturaVenus;
    }
    public static Texture getTexturaLaTierra(){
        return texturaSol;
    }
}
