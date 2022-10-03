package com.br.caixaEletronico.caixaEletronico.controllers;

import com.br.caixaEletronico.caixaEletronico.domain.Endereco;
import com.br.caixaEletronico.caixaEletronico.domain.Perfil;
import com.br.caixaEletronico.caixaEletronico.domain.User;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("adm")
@PreAuthorize("hasAuthority('ADM')")
public class ADMController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PerfilRepository perfilRepository;

    @Autowired
    ADMService admService;
    @Autowired
    EnderecoRepository enderecoRepository;

    @RequestMapping("home")
    public String home(Model model, Principal principal){

        //userRepository.deleteById(9L);

        List<User> users = userRepository.findAllByOrderByUserNameAsc();
        for (User user : users){
            if (user.getUserName().equalsIgnoreCase(principal.getName())){
                users.remove(user);
                break;
            }
        }
        model.addAttribute("users", users);
        return "adm/home";
    }

    @RequestMapping("login")
    public String login(){
        return "adm/login";
    }

    @RequestMapping("novoCliente")
    public String novoCliente(RequisicaoNovoCliente requisicaoNovoCliente){
        return "adm/formularioCliente";
    }

    @RequestMapping("novoADM")
    public String novoADM(RequisicaoNovoADM requisicao){
        return "adm/formularioADM";
    }

    @PostMapping("ativaDesativa/{id}")
    public String ativaDesativa(@PathVariable Long id){


        Optional<User> user = userRepository.findById(id);
        User userUpdate = user.get();
        userUpdate.setEnable();
        userRepository.save(userUpdate);

        return "redirect:/adm/home";
    }

    @PostMapping("novoCliente")
    public String novo(@Valid RequisicaoNovoCliente requisicao, BindingResult result){
        if(result.hasErrors()){
            return "adm/formularioCliente";
        }

        User user = requisicao.toUser();
        Endereco endereco = requisicao.toEndereco();
        Perfil perfil = perfilRepository.findByNome("CLIENTE");
        user.getPerfis().add(perfil);
        enderecoRepository.save(endereco);
        user.setEndereco(endereco);
        userRepository.save(user);

        return "redirect:/adm/home";
    }

    @PostMapping("novoADM")
    public String novo(@Valid RequisicaoNovoADM requisicao, BindingResult result){
        if(result.hasErrors()){
            return "adm/formularioADM";
        }


        User user = requisicao.toUser();
        Endereco endereco = requisicao.toEndereco();
        Perfil perfil = perfilRepository.findByNome("ADM");
        user.getPerfis().add(perfil);
        enderecoRepository.save(endereco);
        user.setEndereco(endereco);
        userRepository.save(user);

        return "redirect:/adm/home";
    }

}
