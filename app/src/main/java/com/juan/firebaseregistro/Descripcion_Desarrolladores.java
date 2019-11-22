package com.juan.firebaseregistro;

public class Descripcion_Desarrolladores {
    private String nombre, descripcion,desarrollado, nombre1,nombre2,nombre3,nombre4,nombre5,nombre6,instructor,nombre7;
    private int imgFoto;

    public Descripcion_Desarrolladores() {
    }

    public Descripcion_Desarrolladores(String nombre, String descripcion, String desarrollado, String nombre1, String nombre2, String nombre3, String nombre4, String nombre5, String nombre6, String instructor, String nombre7, int imgFoto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.desarrollado = desarrollado;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.nombre3 = nombre3;
        this.nombre4 = nombre4;
        this.nombre5 = nombre5;
        this.nombre6 = nombre6;
        this.instructor = instructor;
        this.nombre7 = nombre7;
        this.imgFoto = imgFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDesarrollado() {
        return desarrollado;
    }

    public void setDesarrollado(String desarrollado) {
        this.desarrollado = desarrollado;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getNombre3() {
        return nombre3;
    }

    public void setNombre3(String nombre3) {
        this.nombre3 = nombre3;
    }

    public String getNombre4() {
        return nombre4;
    }

    public void setNombre4(String nombre4) {
        this.nombre4 = nombre4;
    }

    public String getNombre5() {
        return nombre5;
    }

    public void setNombre5(String nombre5) {
        this.nombre5 = nombre5;
    }

    public String getNombre6() {
        return nombre6;
    }

    public void setNombre6(String nombre6) {
        this.nombre6 = nombre6;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getNombre7() {
        return nombre7;
    }

    public void setNombre7(String nombre7) {
        this.nombre7 = nombre7;
    }

    public int getImgFoto() {
        return imgFoto;
    }

    public void setImgFoto(int imgFoto) {
        this.imgFoto = imgFoto;
    }
}
