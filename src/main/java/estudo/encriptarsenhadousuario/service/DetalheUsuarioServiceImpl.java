package estudo.encriptarsenhadousuario.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import estudo.encriptarsenhadousuario.data.DetalheUsuarioData;
import estudo.encriptarsenhadousuario.model.UsuarioModel;
import estudo.encriptarsenhadousuario.repository.UsuarioRepository;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {
	
	private final UsuarioRepository repository;
	
	public DetalheUsuarioServiceImpl(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsuarioModel> usuario = repository.findByLogin(username);
		
		if (usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuario [" + username + "] nao encontrado");
		}
				
		return new DetalheUsuarioData(usuario);
	}

}
