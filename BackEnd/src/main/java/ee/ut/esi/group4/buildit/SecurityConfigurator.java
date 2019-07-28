package ee.ut.esi.group4.buildit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfigurator extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    void authentication(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
            .withUser(userBuilder.username("site-engineer").password("password").roles("SITE-ENGINEER"))
            .withUser(userBuilder.username("work-engineer").password("password").roles("WORKS-ENGINEER"))
            .withUser(userBuilder.username("rentit1").password("password").roles("SUPPLIER"))
            .withUser(userBuilder.username("rentit2").password("password").roles("SUPPLIER"));
//            .and()
//            .inMemoryAuthentication()
//            .withUser(userBuilder.username("user2").password("user2").roles("CLERK"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and()
            .authorizeRequests()
            .antMatchers("/h2-console").permitAll()
//            .antMatchers("/sayHello").hasAnyRole("USER1")
            .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
            .antMatchers("/api/**").authenticated()
            .and().httpBasic();
        http.headers().frameOptions().disable();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
