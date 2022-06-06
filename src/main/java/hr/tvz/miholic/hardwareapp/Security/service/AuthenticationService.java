package hr.tvz.miholic.hardwareapp.Security.service;




import hr.tvz.miholic.hardwareapp.Security.command.LoginCommand;
import hr.tvz.miholic.hardwareapp.Security.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
