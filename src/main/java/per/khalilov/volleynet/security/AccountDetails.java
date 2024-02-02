package per.khalilov.volleynet.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import per.khalilov.volleynet.models.Account;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class AccountDetails implements UserDetails {

    private final Account account;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(account.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return account.getHashPassword();
    }

    @Override
    public String getUsername() {
        return account.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return account.getStatus() != Account.Status.BLOCKED;
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
