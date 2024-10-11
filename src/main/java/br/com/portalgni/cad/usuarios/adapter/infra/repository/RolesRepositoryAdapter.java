package br.com.portalgni.cad.usuarios.adapter.infra.repository;

import br.com.portalgni.cad.usuarios.adapter.infra.converter.EntityToRoleConverter;
import br.com.portalgni.cad.usuarios.adapter.infra.converter.RoleToEntityConverter;
import br.com.portalgni.cad.usuarios.adapter.infra.entity.RoleEntity;
import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
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
    public Role salvarRole(Role role) {
        return entityToRole.convert(
                rolesRepository.save(
                        Objects.requireNonNull(
                                roleToEntity.convert(role))));
    }

    @Override
    public void excluirRole(Role role) {
        rolesRepository.delete(Objects.requireNonNull(roleToEntity.convert(role)));
    }

    @Override
    public Optional<Role> buscarRolePorNome(String nome){
        return rolesRepository.findByNome(nome).map(entityToRole::convert);
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
