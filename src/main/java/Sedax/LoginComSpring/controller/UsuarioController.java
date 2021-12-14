package Sedax.LoginComSpring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import Sedax.LoginComSpring.MyUserDetailService;
import Sedax.LoginComSpring.entities.User;
import Sedax.LoginComSpring.repository.UsuarioRepo;

   
@Controller
public class UsuarioController {

    @Autowired
    MyUserDetailService usuarioService;

    @GetMapping("/")
    public String root() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
    
    @GetMapping("/flappy")
    public String flappy() {
        return "flappy";
    }
    
    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }
    
    @PostMapping("/cadastro")
    public String cadastroNovo(String username, String password) {
    	if (usuarioService.findByUsername(username).toString() != "Optional.empty") {
    		return("cadastro");
    	}
    	else {
    	User usuario = new User();
    	usuario.setUsername(username);
    	usuario.setPassword(password);
    	usuarioService.save(usuario);
    	return("login");
    	}
    }
    
    @PostMapping("/atualizar")
    public String alterarSenha(String username, String password) {
    	User user = new User();
    	user.setPassword(password);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String senhaEnconder = encoder.encode(user.getPassword());
            user.setPassword(senhaEnconder);
        usuarioService.UpdatePassword(username, senhaEnconder);
    	return("login");
    }
    
    @GetMapping("/atualizar")
    public String alterarSenha() {
    	return("alterarSenha");
    }
    
    @PostMapping("/deletar")
	public String deletarUser(String username, String password) {
    	User user = new User();
    	user.setPassword(password);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String senhaEnconder = encoder.encode(user.getPassword());
            user.setPassword(senhaEnconder);
            if (usuarioService.findByUsername(username) != null){
    		usuarioService.DeleteByusername(username, senhaEnconder);	
            }
    	return("login");
    }
    
    @GetMapping("/deletar")
	public String deletarUser() {
    	return("deletarUsuario");
    }
    
//primeiro usuario criado

//    @GetMapping("/new-user")
//    public String newUser() {
//        User usuario = new User();
//        usuario.setId(1L);
//        usuario.setUsername("Sedax");
//        usuario.setPassword("123");
//        usuarioService.save(usuario);
//        return usuario.toString();
//    }

}