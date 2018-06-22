package com.example.dani.app12.model;

/**
 * Created by dani on 06/06/18.
 */
public class Usuario {

    private Integer id;
    private String nome;
    private String img;
    private String frase;
    private Boolean segue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public Boolean getSegue() {
        return segue;
    }

    public void setSegue(Boolean segue) {
        this.segue = segue;
    }

    public Usuario() {

    }

    public Usuario(Integer id, String nome, String img, String frase, Boolean segue) {
        this.id = id;
        this.nome = nome;
        this.img = img;
        this.frase = frase;
        this.segue = segue;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", img='" + img + '\'' +
                ", frase='" + frase + '\'' +
                ", segue=" + segue +
                '}';
    }
}
