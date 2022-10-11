package com.app.glicoCheck.model;

public class GlicoseUser {
    private String glicose, dia;

    public GlicoseUser(String glicose, String dia) {
        this.glicose = glicose;
        this.dia = dia;
    }

    public String getGlicose() {
        return glicose;
    }

    public void setGlicose(String glicose) {
        this.glicose = glicose;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
