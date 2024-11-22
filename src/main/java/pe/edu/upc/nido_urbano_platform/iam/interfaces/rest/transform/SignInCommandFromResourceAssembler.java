package pe.edu.upc.nido_urbano_platform.iam.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.iam.domain.model.commands.SignInCommand;
import pe.edu.upc.nido_urbano_platform.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {

  public static SignInCommand toCommandFromResource(SignInResource signInResource) {
    return new SignInCommand(signInResource.username(), signInResource.password());
  }
}
