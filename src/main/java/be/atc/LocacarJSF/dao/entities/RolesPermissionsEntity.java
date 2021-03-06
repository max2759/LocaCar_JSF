package be.atc.LocacarJSF.dao.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles_permissions", schema = "locacarjsf", catalog = "")
@NamedQueries({
        @NamedQuery(name = "RolesPermissions.findAll", query = "SELECT r from RolesPermissionsEntity r"),
        @NamedQuery(name = "RolesPermissions.findByIDRoles", query = "SELECT r from RolesPermissionsEntity r where r.rolesByIdRoles.id = :idRoles"),
        @NamedQuery(name = "RolesPermissions.findForRolesAndPermissions", query = "SELECT rp FROM RolesPermissionsEntity rp WHERE rp.rolesByIdRoles.id = :id "),
        @NamedQuery(name = "RolesPermissions.addControl", query = "SELECT rp from RolesPermissionsEntity rp where rp.rolesByIdRoles.id = :idRole and rp.permissionsByIdPermissions.id = :idPerm"),

})
public class RolesPermissionsEntity {
    private int id;
    private RolesEntity rolesByIdRoles;
    private PermissionsEntity permissionsByIdPermissions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolesPermissionsEntity that = (RolesPermissionsEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Roles", referencedColumnName = "id", nullable = false)
    public RolesEntity getRolesByIdRoles() {
        return rolesByIdRoles;
    }

    public void setRolesByIdRoles(RolesEntity rolesByIdRoles) {
        this.rolesByIdRoles = rolesByIdRoles;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Permissions", referencedColumnName = "id", nullable = false)
    public PermissionsEntity getPermissionsByIdPermissions() {
        return permissionsByIdPermissions;
    }

    public void setPermissionsByIdPermissions(PermissionsEntity permissionsByIdPermissions) {
        this.permissionsByIdPermissions = permissionsByIdPermissions;
    }
}
