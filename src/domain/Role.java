package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author:L1ANN
 * @Description: 角色类
 * @Date:Created in 20:36 2017/11/27
 * @Modified By:
 */
public class Role {
    private String id;
    private String name;
    private String description;
    private Set<Privilege> privileges = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }
}
