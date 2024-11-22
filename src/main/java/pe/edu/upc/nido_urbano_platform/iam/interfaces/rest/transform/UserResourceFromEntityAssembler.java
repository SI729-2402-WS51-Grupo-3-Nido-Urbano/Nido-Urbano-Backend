package pe.edu.upc.nido_urbano_platform.iam.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.iam.domain.model.aggregates.User;
import pe.edu.upc.nido_urbano_platform.iam.domain.model.entities.Role;
import pe.edu.upc.nido_urbano_platform.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {

  public static UserResource toResourceFromEntity(User user) {
    var roles = user.getRoles().stream()
        .map(Role::getStringName)
        .toList();
    return new UserResource(user.getId(), user.getUsername(), roles);
  }
}