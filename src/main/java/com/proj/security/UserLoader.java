package com.proj.security;

import com.proj.security.documents.Account;
import com.proj.security.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserLoader  implements UserDetailsService {
    @Autowired
    AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = repository.findById(login).orElse(null);
        if(account == null) throw new UsernameNotFoundException("login not registred");
        Set<String> roles = account.getRoles();
        return new User(login,
                 account.getPassword(),
                AuthorityUtils.createAuthorityList(
                         roles.stream()
                        .collect(Collectors.toList())
                        .toArray(new String[roles.size()])
                ));
    }
}
