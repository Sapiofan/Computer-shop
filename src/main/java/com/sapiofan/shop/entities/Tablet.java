package com.sapiofan.shop.entities;

public class Tablet extends Computer {
    private String mainCamera;

    private String frontCamera;

    private Boolean bluetooth;

    private String housingMaterial;

    public Tablet() {
    }

    public Tablet(Long id, String name, String brand, Integer price, String mainCamera, String frontCamera,
                  Boolean bluetooth, String housingMaterial) {
        super(id, name, brand, price);
        this.mainCamera = mainCamera;
        this.frontCamera = frontCamera;
        this.bluetooth = bluetooth;
        this.housingMaterial = housingMaterial;
    }

    public String getMainCamera() {
        return mainCamera;
    }

    public void setMainCamera(String mainCamera) {
        this.mainCamera = mainCamera;
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public void setFrontCamera(String frontCamera) {
        this.frontCamera = frontCamera;
    }

    public Boolean getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(Boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getHousingMaterial() {
        return housingMaterial;
    }

    public void setHousingMaterial(String housingMaterial) {
        this.housingMaterial = housingMaterial;
    }

    @Override
    public String toString() {
        String res = super.toString();
        res = res.substring(0, 9) + ": Tablet " + res.substring(9) +
                "\nMain camera = " + mainCamera + " MP" +
                "\nFront camera = " + frontCamera + " MP" +
                "\nBluetooth = " + (bluetooth ? "Yes" : "No") +
                "\nHousing material = " + housingMaterial;
        return res;
    }
}
