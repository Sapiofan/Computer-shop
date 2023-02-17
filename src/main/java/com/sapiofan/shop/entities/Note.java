package com.sapiofan.shop.entities;

import java.time.Instant;
import java.util.Date;

public class Note {
    private Long id;

    private String phone;

    private String address;

    private Date createdAt = Date.from(Instant.now());

    private Computer device;

    public Note() {
    }

    public Note(Long id, String phone, String address, Computer device) {
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.device = device;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Computer getDevice() {
        return device;
    }

    public void setDevice(Computer device) {
        this.device = device;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
