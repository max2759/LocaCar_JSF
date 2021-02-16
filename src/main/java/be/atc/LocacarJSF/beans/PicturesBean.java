package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.CarsPicturesEntity;
import be.atc.LocacarJSF.services.CarsPicturesServices;
import be.atc.LocacarJSF.services.CarsPicturesServicesImpl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public static Collection<Part> getAllParts(Part part) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getParts().stream().filter(p -> part.getName().equals(p.getName())).collect(Collectors.toList());
    }

    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste carEntities
     */
    @PostConstruct
    public void init() {
        carsPicturesEntity = new CarsPicturesEntity();
    }

    public void save() throws ServletException, IOException {

        log.info("début de la sauvegarde");


        String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        log.info("Nom de l'image :" + fileName);

        try (InputStream input = file.getInputStream()) {

            Files.copy(input, new File(folder, fileName).toPath());
            carsPicturesEntity.setLabel(fileName);
            carsPicturesEntity.setCarsByIdCars(carsBean.findCarsById(idCars));
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
