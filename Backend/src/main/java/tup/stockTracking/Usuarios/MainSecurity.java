package tup.stockTracking.Usuarios;

import tup.stockTracking.Usuarios.JWT.JwtEntryPoint;
import tup.stockTracking.Usuarios.JWT.JwtTokenFilter;
import tup.stockTracking.Usuarios.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Metodos de WebSecurityConfigurerAdapter
    /*
     * @Override
     * protected void configure(AuthenticationManagerBuilder auth) throws Exception
     * {
     * auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()
     * );
     * }
     * 
     * @Bean
     * 
     * @Override
     * public AuthenticationManager authenticationManagerBean() throws Exception {
     * return super.authenticationManagerBean();
     * }
     * 
     * @Override
     * protected AuthenticationManager authenticationManager() throws Exception {
     * return super.authenticationManager();
     * }
     * 
     * @Override
     * protected void configure(HttpSecurity http) throws Exception {
     * http.cors().and().csrf().disable()
     * .authorizeRequests()
     * .antMatchers("/auth/**").permitAll()
     * .anyRequest().authenticated()
     * .and()
     * .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
     * .and()
     * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
     * http.addFilterBefore(jwtTokenFilter(),
     * UsernamePasswordAuthenticationFilter.class);
     * }
     */

    // Nueva implementacion

    // AuthenticationManager es reemplazado por UserDetailsManager o
    // UserDetailsService
    /*
     * Como nosotros tenemos crear un servicio de UserDetailService. podemos implementar la 2da opcion
     */

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
            UserDetailsServiceImpl userDetailService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    // Configure HttpSecurity es reemplzado por SecurityFilterChain

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
