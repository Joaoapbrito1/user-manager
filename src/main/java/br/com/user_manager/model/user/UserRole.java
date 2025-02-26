package br.com.user_manager.model.user;


public enum UserRole {

    ROLE_ADMIN("admin"),
    ROLE_USER("user");

    private final String role;

    UserRole(String role){
        this.role = role;
    }
    public String role(){
        return role;
    }
}