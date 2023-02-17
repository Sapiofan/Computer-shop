package com.sapiofan.shop.entities;

public class Laptop extends Computer {

    private String ram;

    private String os;

    private Integer storage;

    private Integer cores;

    private String screenDiagonal;

    public Laptop() {}

    public Laptop(Long id, String name, String brand, Integer price,
                  String ram, String os, Integer storage, Integer cores, String screenDiagonal) {
        super(id, name, brand, price);
        this.ram = ram;
        this.os = os;
        this.storage = storage;
        this.cores = cores;
        this.screenDiagonal = screenDiagonal;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public Integer getCores() {
        return cores;
    }

    public void setCores(Integer cores) {
        this.cores = cores;
    }

    public String getScreenDiagonal() {
        return screenDiagonal;
    }

    public void setScreenDiagonal(String screenDiagonal) {
        this.screenDiagonal = screenDiagonal;
    }

    @Override
    public String toString() {
        String res = super.toString();
        res = res.substring(0, 9) + ": Laptop " + res.substring(9) +
                "\nRAM = " + ram +
                "\nOS = " + os +
                "\nStorage = " + storage +
                "\nCores = " + cores +
                "\nScreen diagonal = " + screenDiagonal;

        return res;
    }
}
