package com.app.islam.perantara;

public class User {

    private int umur;
    private String jk, pendidikan, pekerjaan, email;

    public User() {

    }

    public User(int umur, String jk, String pendidikan, String pekerjaan, String email) {
        this.umur = umur;
        this.jk = jk;
        this.pendidikan = pendidikan;
        this.pekerjaan = pekerjaan;
        this.email = email;
    }
}
