package com.proj.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    JWTTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        Claims claims = null;
        String jwtToken = null;

        String authHeder = httpServletRequest.getHeader("Authorization");
        if (authHeder != null && authHeder.startsWith("Bearer ")) {
            jwtToken = authHeder.substring(7);
            try {
                claims = jwtTokenUtils.getAllClaimsFromToken(jwtToken);
            } catch (Exception e) {
                httpServletRequest.setAttribute("wrong reqest", e.getMessage());
            }
        }

        if (claims != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(claims.getSubject(),
                    null, ((List<LinkedHashMap<String, String>>) jwtTokenUtils.getClaimFromToken(jwtToken, claims1 -> claims1.get("roles")))
                    .stream()
                    .map(role-> new SimpleGrantedAuthority(role.get("authority")))
                    .collect(Collectors.toList())
            ));
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
