// 山崎壱人
package bean;

import java.io.Serializable;

public class ClassNum implements Serializable {

    // クラス番号
    private String classNum;

    // 学校
    private School school;

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
}
