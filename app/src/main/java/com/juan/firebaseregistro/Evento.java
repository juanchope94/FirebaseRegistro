package com.juan.firebaseregistro;

import java.io.Serializable;

public class Evento implements Serializable {



    String id;
    String nombre;
    String telefono;
    String urlImagen;
    String latitud, longitud;
    String categoria;
    String descripcion;
    String direccion;
    String fecha;


    public Evento(String nombre, String telefono, String urlImagen, String categoria, String descripcion, String direccion, String fecha, String latitud, String longitud) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.urlImagen = urlImagen;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.fecha = fecha;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Evento() {
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }





}
