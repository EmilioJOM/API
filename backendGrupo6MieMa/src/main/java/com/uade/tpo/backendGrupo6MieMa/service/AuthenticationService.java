package com.uade.tpo.backendGrupo6MieMa.service;

import com.uade.tpo.backendGrupo6MieMa.controllers.auth.AuthenticationRequest;
import com.uade.tpo.backendGrupo6MieMa.controllers.auth.AuthenticationResponse;
import com.uade.tpo.backendGrupo6MieMa.controllers.auth.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
