package components.emybank.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    //Hash password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Add authorize for path API
        http.authorizeHttpRequests()
                .antMatchers("/api/v1/*").permitAll()
                .antMatchers("/api/v1/account/*").permitAll()
                .antMatchers("/api/v1/accountType/*").permitAll()
                .antMatchers("/api/v1/loan/account/*").permitAll()
                .antMatchers("/api/v1/transaction/*").permitAll()
                .antMatchers("/api/v1/transaction/excel").permitAll()
                .antMatchers("/api/v1/loan/excel").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable().httpBasic();
    }
}

