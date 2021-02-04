package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cars", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Cars.findAll", query = "SELECT c from CarsEntity c "),
})
public class CarsEntity {
    private int id;
    private int releaseYear;
    private int horsePower;
    private int kilometer;
    private boolean isActive;
    private Collection<AdsEntity> adsById;
    private ModelsEntity modelsByIdModels;
    private CarsTypesEntity carsTypesByIdCarsTypes;
    private CarsGearboxEntity carsGearboxByIdCarsGearBox;
    private CarsFuelsEntity carsFuelsByIdCarsFuels;
    private CarsColorsEntity carsColorsByIdCarsColors;
    private CarsPicturesEntity carsPicturesByIdCarsPictures;
    private Collection<CarsOptionsEntity> carsOptionsById;
    private Collection<ContractsEntity> contractsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Basic
    @Column(name = "Release_Year")
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Basic
    @Column(name = "HorsePower")
    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    @Basic
    @Column(name = "Kilometer")
    public int getKilometer() {
        return kilometer;
    }

    public void setKilometer(int kilometer) {
        this.kilometer = kilometer;
    }

    @Basic
    @Column(name = "IsActive")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarsEntity that = (CarsEntity) o;
        return id == that.id && releaseYear == that.releaseYear && horsePower == that.horsePower && kilometer == that.kilometer && isActive == that.isActive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, releaseYear, horsePower, kilometer, isActive);
    }

    @OneToMany(mappedBy = "carsByIdCars")
    public Collection<AdsEntity> getAdsById() {
        return adsById;
    }

    public void setAdsById(Collection<AdsEntity> adsById) {
        this.adsById = adsById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Models", referencedColumnName = "ID", nullable = false)
    public ModelsEntity getModelsByIdModels() {
        return modelsByIdModels;
    }

    public void setModelsByIdModels(ModelsEntity modelsByIdModels) {
        this.modelsByIdModels = modelsByIdModels;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Cars_Types", referencedColumnName = "ID", nullable = false)
    public CarsTypesEntity getCarsTypesByIdCarsTypes() {
        return carsTypesByIdCarsTypes;
    }

    public void setCarsTypesByIdCarsTypes(CarsTypesEntity carsTypesByIdCarsTypes) {
        this.carsTypesByIdCarsTypes = carsTypesByIdCarsTypes;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Cars_GearBox", referencedColumnName = "ID", nullable = false)
    public CarsGearboxEntity getCarsGearboxByIdCarsGearBox() {
        return carsGearboxByIdCarsGearBox;
    }

    public void setCarsGearboxByIdCarsGearBox(CarsGearboxEntity carsGearboxByIdCarsGearBox) {
        this.carsGearboxByIdCarsGearBox = carsGearboxByIdCarsGearBox;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Cars_Fuels", referencedColumnName = "ID", nullable = false)
    public CarsFuelsEntity getCarsFuelsByIdCarsFuels() {
        return carsFuelsByIdCarsFuels;
    }

    public void setCarsFuelsByIdCarsFuels(CarsFuelsEntity carsFuelsByIdCarsFuels) {
        this.carsFuelsByIdCarsFuels = carsFuelsByIdCarsFuels;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Cars_Colors", referencedColumnName = "id", nullable = false)
    public CarsColorsEntity getCarsColorsByIdCarsColors() {
        return carsColorsByIdCarsColors;
    }

    public void setCarsColorsByIdCarsColors(CarsColorsEntity carsColorsByIdCarsColors) {
        this.carsColorsByIdCarsColors = carsColorsByIdCarsColors;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Cars_Pictures", referencedColumnName = "id", nullable = false)
    public CarsPicturesEntity getCarsPicturesByIdCarsPictures() {
        return carsPicturesByIdCarsPictures;
    }

    public void setCarsPicturesByIdCarsPictures(CarsPicturesEntity carsPicturesByIdCarsPictures) {
        this.carsPicturesByIdCarsPictures = carsPicturesByIdCarsPictures;
    }

    @OneToMany(mappedBy = "carsByIdCars")
    public Collection<CarsOptionsEntity> getCarsOptionsById() {
        return carsOptionsById;
    }

    public void setCarsOptionsById(Collection<CarsOptionsEntity> carsOptionsById) {
        this.carsOptionsById = carsOptionsById;
    }

    @OneToMany(mappedBy = "carsByIdCars")
    public Collection<ContractsEntity> getContractsById() {
        return contractsById;
    }

    public void setContractsById(Collection<ContractsEntity> contractsById) {
        this.contractsById = contractsById;
    }
}
