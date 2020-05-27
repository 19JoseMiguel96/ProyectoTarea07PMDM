package objetos;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class Elemento3D {

    public Matrix4 matriz;
    public Vector3 posicion;
    public float escala;
    public Vector3 velocidad;
    private float rotar;
    private Vector3 temp;
    public static enum TIPOS_ELEMENTOS {SOL, MERCURIO, VENUS, LATIERRA};
    private TIPOS_ELEMENTOS tipo;

    public Elemento3D(Vector3 pos, float escala,Vector3 velocidad,TIPOS_ELEMENTOS tipo){
        matriz = new Matrix4();
        posicion = pos;
        this.escala=escala;
        this.velocidad = velocidad;
        this.tipo=tipo;

        temp = new Vector3();

    }
    public TIPOS_ELEMENTOS getTipo() {
        return tipo;
    }
    public void update(float delta){

        /*temp.set(velocidad);
        posicion.add(temp.scl(delta));


        matriz.idt();
        matriz.translate(posicion);
        matriz.scl(escala);*/
        if(tipo==TIPOS_ELEMENTOS.SOL){
            temp.set(velocidad);
            posicion.add(temp.scl(delta));

            rotar+=70f*delta;

            matriz.idt();
            matriz.translate(posicion);
            matriz.scl(escala);
            matriz.rotate(0, 1, 0, rotar);
        }
        if(tipo==TIPOS_ELEMENTOS.MERCURIO){
            temp.set(velocidad);
            posicion.add(temp.scl(delta));

            rotar+=62f*delta;

            matriz.idt();
            matriz.translate(posicion);
            matriz.scl(escala);
            matriz.rotate(0, 1, 0, rotar);
        }
        if(tipo==TIPOS_ELEMENTOS.VENUS){
            temp.set(velocidad);
            posicion.add(temp.scl(delta));

            rotar+=55f*delta;

            matriz.idt();
            matriz.translate(posicion);
            matriz.scl(escala);
            matriz.rotate(0, 1, 0, rotar);
        }
        if(tipo==TIPOS_ELEMENTOS.LATIERRA){
            temp.set(velocidad);
            posicion.add(temp.scl(delta));

            rotar+=100f*delta;

            matriz.idt();
            matriz.translate(posicion);
            matriz.scl(escala);
            matriz.rotate(0, 1, 0, rotar);
        }



    }

}
