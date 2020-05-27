package modelo;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;

import objetos.Elemento3D;

public class Mundo {
    public ArrayList<Elemento3D>elementos3d;

    public Mundo(){
        elementos3d = new ArrayList<Elemento3D>();

        elementos3d.add(new Elemento3D(new Vector3(0,0,-14f),1f,new Vector3(0,0,0),Elemento3D.TIPOS_ELEMENTOS.SOL));

        elementos3d.add(new Elemento3D(new Vector3(6f,4f,-14f),1f,new Vector3(-6,-4,-6),Elemento3D.TIPOS_ELEMENTOS.MERCURIO));

        elementos3d.add(new Elemento3D(new Vector3(0,0,-22f),1f,new Vector3(-4,0,4),Elemento3D.TIPOS_ELEMENTOS.VENUS));

        elementos3d.add(new Elemento3D(new Vector3(0,12f,-14f),1f,new Vector3(0,-2,-2),Elemento3D.TIPOS_ELEMENTOS.LATIERRA));


    }

}