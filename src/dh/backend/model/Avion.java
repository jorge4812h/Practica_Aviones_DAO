package dh.backend.model;

import java.time.LocalDate;

public class Avion {

    private Integer id;
    private String nombre;
    private String modelo;
    private String matricula;
    private LocalDate fechaEntrada;

    public Avion(Integer id, String nombre, String modelo, String matricula, LocalDate fechaEntrada) {
        this.id = id;
        this.nombre = nombre;
        this.modelo = modelo;
        this.matricula = matricula;
        this.fechaEntrada = fechaEntrada;
    }

    public Avion(String nombre, String modelo, String matricula, LocalDate fechaEntrada) {
        this.nombre = nombre;
        this.modelo = modelo;
        this.matricula = matricula;
        this.fechaEntrada = fechaEntrada;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    @Override
    public String toString() {
        return "Avion(" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", modelo='" + modelo + '\'' +
                ", matricula='" + matricula + '\'' +
                ", fechaEntrada=" + fechaEntrada +
                ')';
    }
}
