package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.CarsEntity;
import be.atc.LocacarJSF.dao.entities.CarsPicturesEntity;
import be.atc.LocacarJSF.services.CarsPicturesServices;
import be.atc.LocacarJSF.services.CarsPicturesServicesImpl;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * @Author: Maximilien - Zabbara
 */
@Named("picturesBean")
@ViewScoped
public class PicturesBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -7193767475781021472L;

    private CarsPicturesEntity carsPicturesEntity;
    private final CarsPicturesServices carsPicturesServices = new CarsPicturesServicesImpl();
    private List<CarsPicturesEntity> carsPicturesEntityList;

    private String folder = "A:\\Applications\\Drive\\Work\\IntelliJ\\LocacarJSF\\src\\main\\webapp\\resources\\upload";

    private Part file;


    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour CarsPicturesEntity
     */
    @PostConstruct
    public void init() {
        carsPicturesEntity = new CarsPicturesEntity();
        carsPicturesEntityList = carsPicturesServices.findAll();
    }

    /**
     * Save file
     *
     * @throws ServletException
     * @throws IOException
     */
    public void save(CarsEntity carsEntity) throws ServletException, IOException {

        log.info("début de la sauvegarde");

        String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        log.info("Nom de l'image :" + fileName);

        carsPicturesEntity = carsPicturesServices.findByLabel(fileName);
        boolean pictureExist = carsPicturesEntity != null;

        if (pictureExist) {
            log.info("Changer le nom du fichier");
            Random random = new Random();
            int randomNbr = random.nextInt(999999999);
            log.info("Nombre généré :" + randomNbr);
            String randomNbrToString = Integer.toString(randomNbr);
            String replacementString = randomNbrToString + "_" + fileName;
            String newFileName = fileName.replace(fileName, replacementString);
            log.info("Nouveau nom : " + newFileName);
            copyFiles(newFileName);
            upload(newFileName, carsEntity);
        } else {
            log.info("Poursuivre");
            init();
            copyFiles(fileName);
            upload(fileName, carsEntity);
        }


    }

    /**
     * Copy files to upload folder
     * @param fileName
     * @throws IOException
     */
    private void copyFiles(String fileName) throws IOException {
        try (InputStream input = file.getInputStream()) {
            Files.copy(input, new File(folder, fileName).toPath());
            log.info("Bien sauvegardé dans le dossier uploads");
        } catch (IOException e) {
            throw new IOException("Erreur");
        }
    }

    /**
     * Upload file to db
     *
     * @param fileName
     */
    private void upload(String fileName, CarsEntity carsEntity) {
        log.info("envoie à la DB");
        carsPicturesEntity.setLabel(fileName);
        carsPicturesEntity.setCarsByIdCars(carsEntity);
        carsPicturesServices.add(carsPicturesEntity);
        log.info("Sauvegardé dans la DB");
    }

    /**
     * Get a list of pictures by cars id
     *
     * @param idCars
     * @return List of Cars pictures Entity
     */
    public List<CarsPicturesEntity> findCarsPicturesByIdCars(int idCars) {
        return carsPicturesServices.findCarsPicturesByIdCars(idCars);
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
    }

    public List<CarsPicturesEntity> getCarsPicturesEntityList() {
        return carsPicturesEntityList;
    }

    public void setCarsPicturesEntityList(List<CarsPicturesEntity> carsPicturesEntityList) {
        this.carsPicturesEntityList = carsPicturesEntityList;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
