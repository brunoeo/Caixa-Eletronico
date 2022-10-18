package com.br.caixaEletronico.caixaEletronico.controllers;

import com.br.caixaEletronico.caixaEletronico.domain.Roles;
import com.br.caixaEletronico.caixaEletronico.domain.entities.User;
import com.br.caixaEletronico.caixaEletronico.dto.RequisicaoNovoADM;
import com.br.caixaEletronico.caixaEletronico.dto.RequisicaoNovoCliente;
import com.br.caixaEletronico.caixaEletronico.repositories.EnderecoRepository;
import com.br.caixaEletronico.caixaEletronico.repositories.PerfilRepository;
import com.br.caixaEletronico.caixaEletronico.repositories.UserRepository;
import com.br.caixaEletronico.caixaEletronico.services.ADM.ADMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("adm")
@PreAuthorize("hasAuthority('" + Roles.adm +"')")
public class ADMController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private ADMService admService;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("home")
    public String home(Model model, Principal principal){
        //userRepository.deleteById(9L);

        List<User> users = admService.buscaUsuarios(principal.getName(), userRepository);

        model.addAttribute("users", users);
        return "adm/home";
    }

    @GetMapping("login")
    public String login(){
        return "adm/login";
    }

    @GetMapping("novoCliente")
    public String novoCliente(RequisicaoNovoCliente requisicaoNovoCliente){
        return "adm/formularioCliente";
    }

    @GetMapping("novoADM")
    public String novoADM(RequisicaoNovoADM requisicao){
        return "adm/formularioADM";
    }

    @PostMapping("ativaDesativa/{id}")
    public String ativaDesativa(@PathVariable Long id){

        admService.updateEnable(userRepository, id);

        return "redirect:/adm/home";
    }

    @PostMapping("novoCliente")
    public String novo(@Valid RequisicaoNovoCliente requisicao, BindingResult result){
        if(result.hasErrors()){
            return "adm/formularioCliente";
        }

        admService.saveUsuario(requisicao, perfilRepository, enderecoRepository, userRepository);


        return "redirect:/adm/home";
    }

    @PostMapping("novoADM")
    public String novo(@Valid RequisicaoNovoADM requisicao, BindingResult result){
        if(result.hasErrors()){
            return "adm/formularioADM";
        }

        admService.saveUsuario(requisicao, perfilRepository, enderecoRepository, userRepository);


        return "redirect:/adm/home";
    }

}
