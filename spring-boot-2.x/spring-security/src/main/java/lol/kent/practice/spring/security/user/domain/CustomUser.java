package lol.kent.practice.spring.security.user.domain;

public class CustomUser {

    private Long id;

    private String name;

    private String password;

    public CustomUser(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public CustomUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public CustomUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}