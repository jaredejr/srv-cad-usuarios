package br.com.unumpeople.cad.users.web.converter;

import br.com.unumpeople.cad.users.core.domain.Address;
import br.com.unumpeople.cad.users.core.domain.Document;
import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.core.domain.UserRoleContext;
import br.com.unumpeople.cad.users.web.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserConverter {

    private final UserRoleContextConverter roleContextConverter;

    public User toDomain(UserCreateRequestDto userDto) {
        return User.builder()
                .setName(userDto.name())
                .setEmail(userDto.email())
                .setRoleContextList(getRoleContextList(userDto.userRoleContextList()))
                .setStatus(userDto.status())
                .setAddresses(userDto.addresses())
                .setDocuments(userDto.documents())
                .build();
    }

    public User.UserFieldsForUpdate toDomain(UserUpdateRequestDto userDto){
        return new User.UserFieldsForUpdate(
                userDto.name(),
                userDto.email(),
                getRoleContextList(userDto.userRoleContextList()),
                userDto.status());
    }

    public UserResponseDto toResponseDto(User user){
        return new UserResponseDto(user.getId(),
                user.getName(),
                user.getEmail().getValue(),
                user.getAddressList(),
                user.getDocuments(),
                user.getUserRoleContextList(),
                user.getCreationDate(),
                user.getLastAccess(),
                user.getStatus().getValue());
    }

    public UserInternalResponseDto toInternalResponseDto(User user){
        return new UserInternalResponseDto(user.getId(),
                user.getName(),
                user.getEmail().getValue(),
                user.getPassword().getValue(),
                user.getUserRoleContextList(),
                user.getCreationDate(),
                user.getLastAccess(),
                user.getStatus().getValue());
    }

    private Set<UserRoleContext> getRoleContextList(List<UserRoleContextDto> userRoleContextList){

        return userRoleContextList
                .stream()
                .map(roleContext -> roleContextConverter.convert(roleContext.role(),roleContext.context()))
                .collect(Collectors.toSet());
    }

    public Address toAddress(AddressRequestDto addressRequestDto) {
        return new Address(null,
                addressRequestDto.addressType(),
                addressRequestDto.description(),
                addressRequestDto.street(),
                addressRequestDto.number(),
                addressRequestDto.addressComplement(),
                addressRequestDto.neighborhood(),
                addressRequestDto.city(),
                addressRequestDto.state(),
                addressRequestDto.zipCode(),
                addressRequestDto.country()
        );
    }

    public Document toDocument(DocumentRequestDto documentRequestDto) {
        return new Document(
                documentRequestDto.number(),
                documentRequestDto.documentType(),
                documentRequestDto.issueDate(),
                documentRequestDto.expirationDate(),
                documentRequestDto.issuer()
        );
    }

}
