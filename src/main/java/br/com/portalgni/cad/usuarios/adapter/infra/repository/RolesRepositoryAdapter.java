package br.com.portalgni.cad.usuarios.adapter.infra.repository;

import br.com.portalgni.cad.usuarios.adapter.infra.converter.EntityToRoleConverter;
import br.com.portalgni.cad.usuarios.adapter.infra.converter.RoleToEntityConverter;
import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

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
    public void excluirRole(String id) {
        rolesRepository.deleteById(new ObjectId(id));
    }

    @Override
    public Role buscarRolePorNome(String nome){
        return entityToRole.convert(rolesRepository.findByNome(nome));
    }

    @Override
    public Set<Role> buscarTodasAsRoles() {
        return Set.of();
    }
}
