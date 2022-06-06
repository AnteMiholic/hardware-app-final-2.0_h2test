package hr.tvz.miholic.hardwareapp.Security.service;

import hr.tvz.miholic.hardwareapp.Security.domain.User;


public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}
