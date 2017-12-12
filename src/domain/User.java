package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author:L1ANN
 * @Description: 用户类
 * @Date:Created in 20:42 2017/11/27
 * @Modified By:
 */
public class User {
    private String id;
    private String username;
    private String password;
    private String description;
    private Set<Role> roles = new HashSet<>(); //该用户拥有的角色集合

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
