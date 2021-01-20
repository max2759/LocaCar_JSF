package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles_permissions", schema = "locacarjsf", catalog = "")
public class RolesPermissionsEntity {
    private int id;
    private int idRoles;
    private int idPermissions;
    private RolesEntity rolesByIdRoles;
    private PermissionsEntity permissionsByIdPermissions;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ID_Roles")
    public int getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(int idRoles) {
        this.idRoles = idRoles;
    }

    @Basic
    @Column(name = "ID_Permissions")
    public int getIdPermissions() {
        return idPermissions;
    }

    public void setIdPermissions(int idPermissions) {
        this.idPermissions = idPermissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolesPermissionsEntity that = (RolesPermissionsEntity) o;
        return id == that.id && idRoles == that.idRoles && idPermissions == that.idPermissions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idRoles, idPermissions);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Roles", referencedColumnName = "ID", nullable = false)
    public RolesEntity getRolesByIdRoles() {
        return rolesByIdRoles;
    }

    public void setRolesByIdRoles(RolesEntity rolesByIdRoles) {
        this.rolesByIdRoles = rolesByIdRoles;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Permissions", referencedColumnName = "ID", nullable = false)
    public PermissionsEntity getPermissionsByIdPermissions() {
        return permissionsByIdPermissions;
    }

    public void setPermissionsByIdPermissions(PermissionsEntity permissionsByIdPermissions) {
        this.permissionsByIdPermissions = permissionsByIdPermissions;
    }
}
