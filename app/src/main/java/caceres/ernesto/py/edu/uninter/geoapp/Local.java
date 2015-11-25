package caceres.ernesto.py.edu.uninter.geoapp;

import java.util.ArrayList;

public class Local {
    private String _id;
    private String descripcion;
    private String direccion;
    private Boolean disponible;
    private ArrayList<String> foto;
    private double lat;
    private double lng;
    private int precio;
    private String telefono;
    private String titulo;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getDisponible() {
        return this.disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public ArrayList<String> getFoto() {
        return this.foto;
    }

    public void setFoto(ArrayList<String> foto) {
        this.foto = foto;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getPrecio() {
        return this.precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String toString() {
        return "Local{descripcion='" + this.descripcion + '\'' + ", titulo='" + this.titulo + '\'' + ", disponible=" + this.disponible + ", foto='" + this.foto + '\'' + ", lat=" + this.lat + ", lng=" + this.lng + ", precio=" + this.precio + ", telefono='" + this.telefono + '\'' + '}';
    }
}
