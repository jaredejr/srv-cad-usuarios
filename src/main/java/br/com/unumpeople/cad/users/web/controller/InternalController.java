package br.com.unumpeople.cad.users.web.controller;


import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.core.ports.UserServicePort;
import br.com.unumpeople.cad.users.web.converter.UserConverter;
import br.com.unumpeople.cad.users.web.dto.UserInternalResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("internal")
@Slf4j
public class InternalController {

    private final UserServicePort usuarioService;
    private final UserConverter userConverter;

    @GetMapping("/find-by-email-and-update-last-access/{email}")
    public ResponseEntity<UserInternalResponseDto> findUserByEmailAndUpdateLastAccess(@PathVariable("email") String email) {
        User user = usuarioService.getUserByEmailAndUpdateLastAccess(email);
        return ResponseEntity.ok(userConverter.toInternalResponseDto(user));
    }
}
