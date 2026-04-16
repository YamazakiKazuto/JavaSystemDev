package bean;

import java.io.Serializable;

public class Subject implements Serializable {

    /**
     * 科目コード：String
     */
    private String cd;

    /**
     * 科目名：String
     */
    private String name;

    /**
     * 学校コード：String
     */
    private String schoolCd;

    /**
     * ゲッタ・セッタ
     */
    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolCd() {
        return schoolCd;
    }

    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }
}
