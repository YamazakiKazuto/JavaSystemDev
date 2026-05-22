//制作者 山﨑
package bean;

import java.io.Serializable;

public class Role implements Serializable {

    // 学校コード
    private String role;

    // 学校名
    private String name;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}