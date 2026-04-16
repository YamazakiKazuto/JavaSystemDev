package bean;

public class User {

    // 認証フラグ（true: 認証済み）
    private boolean isAuthenticated;

//    public User() {
//        this.isAuthenticated = false;
//    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }
}