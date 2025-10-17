package br.com.unumpeople.cad.users.web.controller;


import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.core.ports.UserServicePort;
import br.com.unumpeople.cad.users.web.converter.DtoToNewUserConverter;
import br.com.unumpeople.cad.users.web.converter.DtoToUserConverter;
import br.com.unumpeople.cad.users.web.converter.UserToDtoConverter;
import br.com.unumpeople.cad.users.web.dto.UserDto;
import br.com.unumpeople.cad.users.web.dto.UserRoleContextDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("internal")
@Slf4j
public class InternalController {

    private final UserServicePort usuarioService;
    private final UserToDtoConverter usuarioToDto;

    @GetMapping("/find-by-email-and-update-last-access/{email}")
    public ResponseEntity<UserDto> findUserByEmailAndUpdateLastAccess(@PathVariable("email") String email) {
        User user = usuarioService.getUserByEmailAndUpdateLastAccess(email);
        return ResponseEntity.ok(usuarioToDto.convert(user));
    }
}
