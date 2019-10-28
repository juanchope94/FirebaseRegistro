package com.juan.firebaseregistro;

public class PojoFavoritos {
    public String getCorreo() {
        return correo;
    }

    public PojoFavoritos(String correo, String idEvento) {
        this.correo = correo;
        this.idEvento = idEvento;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public PojoFavoritos() {
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    String correo;
    String idEvento;
}
