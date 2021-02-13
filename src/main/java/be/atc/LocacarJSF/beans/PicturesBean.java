package be.atc.LocacarJSF.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("picturesBean")
@RequestScoped
public class PicturesBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -7193767475781021472L;
/*
    private CarsPicturesEntity carsPicturesEntity;
    private CarsPicturesServices carsPicturesServices = new CarsPicturesServicesImpl();

    private Part file;

    *//**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste carEntities
     *//*
    @PostConstruct
    public void init() {
        carsPicturesEntity = new CarsPicturesEntity();
    }

    public void save() {

        log.info("début de la sauvegarde");

        String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        log.info("Nom de l'image :" + fileName);

        try (InputStream input = file.getInputStream()) {

            Files.copy(input, new File("A:\\Applications\\Drive\\Work\\IntelliJ\\uploads", fileName).toPath());
            carsPicturesEntity.setLabel(fileName);
            upload();
            log.info("Bien sauvegardé dans le dossier uploads");
        } catch (IOException e) {
            // Show faces message?
        }
    }

    public void upload() {
        log.info("envoie à la DB");
        carsPicturesServices.add(carsPicturesEntity);
    }


    ////// Getters and setters /////

    public CarsPicturesEntity getCarsPicturesEntity() {
        return carsPicturesEntity;
    }

    public void setCarsPicturesEntity(CarsPicturesEntity carsPicturesEntity) {
        this.carsPicturesEntity = carsPicturesEntity;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }*/
}
