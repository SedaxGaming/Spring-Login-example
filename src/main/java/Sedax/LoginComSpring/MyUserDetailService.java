package Sedax.LoginComSpring;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Sedax.LoginComSpring.entities.User;
import Sedax.LoginComSpring.repository.UsuarioRepo;

@Service(value = "usuarioService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepo usuarioRepo;    
    
    public void DeleteByusername(String username, String Password){
    	usuarioRepo.DeleteByusername(username, Password);
    }
    
    public void UpdatePassword(String username, String password) {
    	usuarioRepo.UpdatePassword(username, password);
    }
      
    
    public Optional<User> findByUsername(String username) {
    	return usuarioRepo.findByUsername(username);
    }
    
    

    
    
    public Optional<User> obterId(long id){
    	return usuarioRepo.findById(id);
    }

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> opt = usuarioRepo.findByUsername(username);
        User user = null;
        if(opt.isPresent()){
            user = opt.get();
        }
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }

    public User save(@Valid User usuario) {
        usuario.setPassword(bcryptEncoder.encode(usuario.getPassword()));
        return usuarioRepo.save(usuario);
    }
}