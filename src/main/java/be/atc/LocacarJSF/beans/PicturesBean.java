package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.CarsPicturesEntity;
import be.atc.LocacarJSF.services.CarsPicturesServices;
import be.atc.LocacarJSF.services.CarsPicturesServicesImpl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
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
@SessionScoped
public class PicturesBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -7193767475781021472L;

    private CarsPicturesEntity carsPicturesEntity;
    private CarsPicturesServices carsPicturesServices = new CarsPicturesServicesImpl();
    @Inject
    CarsBean carsBean;
    @Inject
    AdsBean adsBean;
    private List<CarsPicturesEntity> carsPicturesEntityList;
    private String folder = "A:\\Applications\\Drive\\Work\\IntelliJ\\uploads";
    private int idCars = 13;


    private Part file;


    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour CarsPicturesEntity
     */
    @PostConstruct
    public void init() {
        carsPicturesEntity = new CarsPicturesEntity();
    }

    /**
     * Save file
     *
     * @throws ServletException
     * @throws IOException
     */
    public void save() throws ServletException, IOException {

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
            upload(newFileName);
        } else {
            log.info("Poursuivre");
            init();
            copyFiles(fileName);
            upload(fileName);
        }

    }

    /**
     * Copy files to upload folder
     *
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
    private void upload(String fileName) {
        log.info("envoie à la DB");
        carsPicturesEntity.setLabel(fileName);
        carsPicturesEntity.setCarsByIdCars(carsBean.findCarsById(idCars));
        carsPicturesServices.add(carsPicturesEntity);
        log.info("Sauvegardé dans la DB");
    }

    /**
     * @param idCars
     * @return list of CarsPicturesEntity
     */
    public List<CarsPicturesEntity> findPictures(int idCars) {
        return carsPicturesServices.findByCarsId(idCars);
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

    public int getIdCars() {
        return idCars;
    }

    public void setIdCars(int idCars) {
        this.idCars = idCars;
    }

    public List<CarsPicturesEntity> getCarsPicturesEntityList() {
        return carsPicturesEntityList;
    }

    public void setCarsPicturesEntityList(List<CarsPicturesEntity> carsPicturesEntityList) {
        this.carsPicturesEntityList = carsPicturesEntityList;
    }
}
