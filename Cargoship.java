import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.FileInputStream;


public class Cargoship implements Serializable {
    private long serialNum;
    private String name;
    private int creationYear;
    private String ownerCountry;
    private double price;
    private int speed;

    public long getSerialNum() {
        return serialNum;
    }
    public void setSerialNum(long serialNum) {
        this.serialNum = serialNum;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCreationYear() {
        return creationYear;
    }
    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }
    public String getOwnerCountry() {
        return ownerCountry;
    }
    public void setOwnerCountry(String ownerCountry) {
        this.ownerCountry = ownerCountry;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Cargoship() {
        serialNum = 1231232;
        name = "Horizon";
        creationYear = 2020;
        ownerCountry = "Brazil";
        price = 9129129.33;
        speed = 808;
    }
    public Cargoship(long serialNum ,String name, int creationYear, String ownerCountry, double price, int speed) {
        this.serialNum = serialNum;
        this.name = name;
        this.creationYear = creationYear;
        this.ownerCountry = ownerCountry;
        this.price = price;
        this.speed = speed;
    }
    public Cargoship(Cargoship CC) {
        this.serialNum = CC.serialNum;
        this.name = CC.name;
        this.creationYear = CC.creationYear;
        this.ownerCountry = CC.ownerCountry;
        this.price = CC.price;
        this.speed = CC.speed;
    }

    public String toString() {
        return getSerialNum() + " " + getName() + " " + getCreationYear() +
                " " + getOwnerCountry() + " " + getPrice() + " " + getSpeed();
    }

    public boolean equals(Object data) {
        if (data == null) {
            return false;
        } else if (this.getClass() != data.getClass()) {
            return false;
        } else {
            Cargoship c = (Cargoship) data;
            return (this.getSerialNum() == c.getSerialNum() && this.getName() ==c.getName() && this.getCreationYear() == c.getCreationYear() &&
                    this.getOwnerCountry() == c.getOwnerCountry() && this.getPrice() == c.getPrice() && this.getSpeed() == c.getSpeed());
        }
    }

}
