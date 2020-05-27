package com.planetas3d.jose;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;

import modelo.Mundo;
import objetos.Elemento3D;

public class Animacion3D extends Game {
    //private Mesh elemento3D;
    private Elemento3D esfera;
    private ShaderProgram shaderProgram;

    private Texture textura;
    private PerspectiveCamera camara3d;
	private Mundo mundo;

    public Animacion3D(){
        mundo = new Mundo();
    }




    @Override
    public void create() {
        // TODO Auto-generated method stub

        shaderProgram = new ShaderProgram(Gdx.files.internal("vertex.vert"), Gdx.files.internal("fragment.frag"));
        if (shaderProgram.isCompiled() == false) {
            Gdx.app.log("ShaderError", shaderProgram.getLog());
            System.exit(0);
        }
        AssetsJuego.cargarElementos3d();
        /*

        AssetManager assetManager = new AssetManager();
        assetManager.load("Modelos/Sol.g3db", Model.class);

        assetManager.finishLoading();

        Model model = assetManager.get("Modelos/Sol.g3db", Model.class);
        elemento3D = model.meshes.get(0);

        FileHandle imageFileHandle = Gdx.files.internal("Modelos/sphere1_auv.png");
        textura = new Texture(imageFileHandle);*/

        camara3d = new PerspectiveCamera();
        //esfera = new Elemento3D(new Vector3(0,-1f,-20f), 2f, new Vector3(0.1f,0.1f,2f));

    }
    private void dibujarElementosMoviles(){
        Texture textura=null;
        for (Elemento3D el3D : mundo.elementos3d){
            switch(el3D.getTipo()){
                case SOL:
                    textura = AssetsJuego.texturaSol;
                    break;
                case MERCURIO:
                    textura = AssetsJuego.texturaMercurio;
                    break;
                case VENUS:
                    textura = AssetsJuego.texturaVenus;
                    break;
                default:
                    textura = AssetsJuego.texturaLaTierra;
                    break;
            }
            textura.bind(0);
            shaderProgram.setUniformi("u_texture", 0);
            el3D.update(Gdx.graphics.getDeltaTime());
            shaderProgram.setUniformMatrix("u_worldView", camara3d.combined.cpy().mul(el3D.matriz));
            AssetsJuego.getElemento3d().render(shaderProgram, GL20.GL_TRIANGLES);
            if(el3D.getTipo()== Elemento3D.TIPOS_ELEMENTOS.MERCURIO) {
                if (el3D.posicion.z <= -20) {
                    el3D.velocidad.x = -6;
                    el3D.velocidad.y = -4;
                    el3D.velocidad.z = 6;
                } else if (el3D.posicion.x <= -6) {
                    el3D.velocidad.x = 6;
                    el3D.velocidad.y = 4;
                    el3D.velocidad.z = 6;
                } else if (el3D.posicion.z >= -8 ) {
                    el3D.velocidad.x = 6;
                    el3D.velocidad.y = 4;
                    el3D.velocidad.z = -6;
                } else if (el3D.posicion.x >= 6) {
                    el3D.velocidad.x = -6;
                    el3D.velocidad.y = -4;
                    el3D.velocidad.z = -6;
                }
            }
            if(el3D.getTipo()== Elemento3D.TIPOS_ELEMENTOS.VENUS) {
                if (el3D.posicion.x <= -8) {
                    el3D.velocidad.x = 4;
                    el3D.velocidad.z = 4;
                } else if (el3D.posicion.z >= -6) {
                    el3D.velocidad.x = 4;
                    el3D.velocidad.z = -4;
                } else if (el3D.posicion.x >= 8) {
                    el3D.velocidad.x = -4;
                    el3D.velocidad.z = -4;
                } else if (el3D.posicion.z <= -22) {
                    el3D.velocidad.x = -4;
                    el3D.velocidad.z = 4;
                }
            }
            if(el3D.getTipo()== Elemento3D.TIPOS_ELEMENTOS.LATIERRA) {
                if (el3D.posicion.z <= -26) {
                    el3D.velocidad.y = -2;
                    el3D.velocidad.z = 2;
                } else if (el3D.posicion.y <= -12) {
                    el3D.velocidad.y = 2;
                    el3D.velocidad.z = 2;
                } else if (el3D.posicion.z >= -2) {
                    el3D.velocidad.y = 2;
                    el3D.velocidad.z = -2;
                } else if (el3D.posicion.y >= 12) {
                    el3D.velocidad.y = -2;
                    el3D.velocidad.z = -2;
                }
            }
        }
    }

    @Override
    public void render() {

        Gdx.gl20.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);

        Gdx.gl20.glEnable(GL20.GL_DEPTH_TEST);
        //Gdx.gl20.glEnable(GL20.GL_TEXTURE_2D);


        shaderProgram.begin();
            dibujarElementosMoviles();
        shaderProgram.end();

        Gdx.gl20.glDisable(GL20.GL_TEXTURE_2D);
        Gdx.gl20.glDisable(GL20.GL_DEPTH_TEST);

    }

    @Override
    public void resize (int width,int height){
        // Definimos os parámetros da cámara
        float aspectRatio = (float) width / (float) height;
        camara3d.viewportWidth=aspectRatio*1f;
        camara3d.viewportHeight=1f;
        camara3d.far=1000f;
        camara3d.near=0.1f;
        camara3d.lookAt(0,0,0);
        camara3d.position.set(0f,0f,5f);
        camara3d.update();
    }

    @Override
    public void dispose(){
        shaderProgram.dispose();
        AssetsJuego.liberarElementos3d();

    }

}
