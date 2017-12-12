package domain;

/**
 * @Author:L1ANN
 * @Description: 权限类
 * @Date:Created in 20:21 2017/11/27
 * @Modified By:
 */
public class Privilege {
    private String id;
    private String name; //添加分类...的权限
    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Privilege privilege = (Privilege) o;

        if (id != null ? !id.equals(privilege.id) : privilege.id != null) return false;
        if (name != null ? !name.equals(privilege.name) : privilege.name != null) return false;
        return description != null ? description.equals(privilege.description) : privilege.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
