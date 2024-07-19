package com.ttdat.eazybank.config;

import com.ttdat.eazybank.model.Customer;
import com.ttdat.eazybank.repository.CustomerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EazyBankUserDetails implements UserDetailsService {

    CustomerRepository customerRepository;

    public EazyBankUserDetails(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password;
        List<GrantedAuthority> authorities;
        List<Customer> customers = customerRepository.findByEmail(username);
        if(customers.isEmpty()){
            throw new UsernameNotFoundException("User details not found for the user: " + username);
        } else{
            userName = customers.getFirst().getEmail();
            password = customers.getFirst().getPwd();
            authorities = List.of(new SimpleGrantedAuthority(customers.getFirst().getRole()));
        }
        return new User(userName, password, authorities);
    }
}
