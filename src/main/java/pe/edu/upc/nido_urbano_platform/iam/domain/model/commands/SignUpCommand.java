package pe.edu.upc.nido_urbano_platform.iam.domain.model.commands;

import pe.edu.upc.nido_urbano_platform.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password, List<Role> roles) {
}
