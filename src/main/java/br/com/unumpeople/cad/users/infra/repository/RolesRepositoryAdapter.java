package br.com.unumpeople.cad.users.infra.repository;

import br.com.unumpeople.cad.users.infra.converter.EntityToRoleConverter;
import br.com.unumpeople.cad.users.infra.converter.RoleToEntityConverter;
import br.com.unumpeople.cad.users.infra.entity.RoleEntity;
import br.com.unumpeople.cad.users.core.domain.Role;
import br.com.unumpeople.cad.users.core.ports.RoleRepositoryPort;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RolesRepositoryAdapter implements RoleRepositoryPort {

    private RolesRepository rolesRepository;
    private EntityToRoleConverter entityToRole;
    private RoleToEntityConverter roleToEntity;

    @Override
    public Role saveRole(Role role) {
        return entityToRole.convert(
                rolesRepository.save(
                        Objects.requireNonNull(
                                roleToEntity.convert(role))));
    }

    @Override
    public void deleteRole(Role role) {
        rolesRepository.delete(Objects.requireNonNull(roleToEntity.convert(role)));
    }

    @Override
    public Optional<Role> getRoleByName(String name){
        return rolesRepository.findByName(name).map(entityToRole::convert);
    }

    @Override
    public Set<Role> buscarTodasAsRoles() {
        return rolesRepository.findAll()
                .stream()
                .map(entityToRole::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Role> buscarPorId(String id) {
        Optional<RoleEntity> optionalRole = rolesRepository.findById(new ObjectId(id));
        return optionalRole.map(entityToRole::convert);
    }
}
