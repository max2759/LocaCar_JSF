package be.atc.LocacarJSF.beans;


import be.atc.LocacarJSF.services.CarsServices;
import be.atc.LocacarJSF.services.CarsServicesImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

@Named(value = "carsBean")
@RequestScoped
public class CarsBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -293903106522268390L;

    CarsServices carsServices = new CarsServicesImpl();

    private Part file;

    public void upload() throws IOException {
        Scanner scanner = new Scanner(file.getInputStream());

    }

    /// getter and setters

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
}
