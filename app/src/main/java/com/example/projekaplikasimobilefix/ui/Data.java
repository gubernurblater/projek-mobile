package com.example.projekaplikasimobilefix.ui;

public class Data {

    private String id, username, jadwal, status;

    public Data(String id, String username, String jadwal, String status) {
        this.setId(id);
        this.setUsername(username);
        this.setJadwal(jadwal);
        this.setStatus(status);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}