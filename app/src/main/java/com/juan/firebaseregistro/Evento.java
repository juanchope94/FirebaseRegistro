package com.juan.firebaseregistro;

import java.io.Serializable;

public class Evento implements Serializable {

    String nombre;
    String telefono;
    String urlImagen;

    public Evento(String nombre, String telefono, String urlImagen, String categoria, String descripcion, String direccion, String fecha, long latitud, long longitud) {
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

    String categoria;
    String descripcion;
    String direccion;
    String fecha;

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

    public long getLatitud() {
        return latitud;
    }

    public void setLatitud(long latitud) {
        this.latitud = latitud;
    }

    public long getLongitud() {
        return longitud;
    }

    public void setLongitud(long longitud) {
        this.longitud = longitud;
    }

    long latitud, longitud;



}
