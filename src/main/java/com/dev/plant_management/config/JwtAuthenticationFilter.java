package com.dev.plant_management.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired(required = true)
    private JwtUtil jwtUtil;

    @Autowired(required = true)
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwtToken);
                logger.debug("JWT found, username extracted: {}", username);
            } catch (Exception e) {
                logger.error("Failed to extract username from JWT: {}", e.getMessage());
            }
        } else {
            logger.debug("No JWT token found in Authorization header");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.debug("Loading user details for: {}", username);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwtToken, userDetails.getUsername())) {
                logger.info("JWT validated successfully for user: {}", username);
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                logger.warn("Invalid JWT for user: {}", username);
            }
        }

        filterChain.doFilter(request, response);
    }
}

