package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.AddressesEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AddressesServicesImplTest {

    AddressesServices addressesServices = new AddressesServicesImpl();

    @Test
    void findByIdUser() {
        int idUser = 5;

        AddressesEntity addressesEntityList = addressesServices.findByIdUser(idUser);
        boolean test = addressesEntityList == null ? false : true;
        assertThat(test).isEqualTo(true);
    }
}