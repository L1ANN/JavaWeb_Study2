package domain;

/**
 * @Author:L1ANN
 * @Description: 资源类
 * @Date:Created in 20:26 2017/11/27
 * @Modified By:
 */
public class Resource {
    private String id;
    private String uri;         //day20/Servlet1
    private String description;
    private Privilege privilege;//权限属性

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
}
