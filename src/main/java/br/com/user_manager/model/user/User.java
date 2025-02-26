package br.com.user_manager.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name ="users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    private  String id;
    private  String login;
    private String password;
    private UserRole role;

    // Metodo para garantir que apenas usuários com o papel ADMIN tenham ambas as permissões (ROLE_ADMIN e ROLE_USER), enquanto os demais usuários terão apenas ROLE_USER.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       if(this.role == UserRole.ROLE_ADMIN)
           return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
               new SimpleGrantedAuthority("ROLE_USER"));
       else {
           return List.of(new SimpleGrantedAuthority("ROLE_USER"));
       }
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
