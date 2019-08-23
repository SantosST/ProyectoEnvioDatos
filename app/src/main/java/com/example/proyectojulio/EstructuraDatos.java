package com.example.proyectojulio;

public class EstructuraDatos {
    private int id;
    private String Nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getMaterias() {
        return Materias;
    }

    public void setMaterias(String materias) {
        Materias = materias;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }

    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {
        Imagen = imagen;
    }

    public String getLocalizacion() {
        return Localizacion;
    }

    public void setLocalizacion(String localizacion) {
        Localizacion = localizacion;
    }

    private String Materias;
    private int Precio;
    private int Imagen;
    private String Localizacion;

    public EstructuraDatos(int id, String nombre, String materias, int precio, int imagen, String localizacion) {
        this.id = id;
        Nombre = nombre;
        Materias = materias;
        Precio = precio;
        Imagen = imagen;
        Localizacion = localizacion;
    }




}
