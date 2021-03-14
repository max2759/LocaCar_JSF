package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "roles", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Roles.findAll", query = "SELECT r from RolesEntity r where r.active = true"),
        @NamedQuery(name = "Roles.findByLabel", query = "SELECT r from RolesEntity r where r.label = :label"),
        @NamedQuery(name = "Roles.findByIdUser", query = "select r from RolesEntity r join UsersEntity u on u.rolesByIdRoles.id = r.id where u.id = :idUser"),

})
public class RolesEntity {
    private int id;
    private String label;
    private boolean isActive;
    private Collection<RolesPermissionsEntity> rolesPermissionsById;
    private Collection<UsersEntity> usersById;

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
    @Column(name = "Label", nullable = false, unique = true)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
        RolesEntity that = (RolesEntity) o;
        return id == that.id && isActive == that.isActive && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, isActive);
    }

    @OneToMany(mappedBy = "rolesByIdRoles")
    public Collection<RolesPermissionsEntity> getRolesPermissionsById() {
        return rolesPermissionsById;
    }

    public void setRolesPermissionsById(Collection<RolesPermissionsEntity> rolesPermissionsById) {
        this.rolesPermissionsById = rolesPermissionsById;
    }

    @OneToMany(mappedBy = "rolesByIdRoles")
    public Collection<UsersEntity> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<UsersEntity> usersById) {
        this.usersById = usersById;
    }
}
