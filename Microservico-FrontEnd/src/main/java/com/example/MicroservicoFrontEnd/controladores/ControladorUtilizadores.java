package com.example.MicroservicoFrontEnd.controladores;

import com.example.MicroservicoFrontEnd.modulos.Tarefa;
import com.example.MicroservicoFrontEnd.modulos.UtilizadorDTO;
import com.example.MicroservicoFrontEnd.proxys.ProxyMicroservicoUtilizadores;
import com.example.MicroservicoFrontEnd.repositorios.RepositorioTarefa;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;


@Controller
public class ControladorUtilizadores {

    @Autowired
    ProxyMicroservicoUtilizadores proxyMicroservicoUtilizadores;

    @Autowired
    RepositorioTarefa repositorioTarefa;

    @GetMapping("/admin/utilizadores")
    public String getPaginaInicial(HttpSession session, Model modelo){
        String email = (String) session.getAttribute("email");

        List<UtilizadorDTO> utilizadores = proxyMicroservicoUtilizadores.getAll();
        for (UtilizadorDTO utilizador : utilizadores) {
            String role = proxyMicroservicoUtilizadores.getRole(utilizador.getEmail());
            utilizador.setRole(role);
        }

        modelo.addAttribute("nome",proxyMicroservicoUtilizadores.getNome(email));
        modelo.addAttribute("role", proxyMicroservicoUtilizadores.getRole(email));
        modelo.addAttribute ("utilizadores", utilizadores);

        return "listaUtilizadores";
    }

    @PostMapping("/admin/contas/{email}")
    public String apagarUtilizador(@PathVariable String email) {

        proxyMicroservicoUtilizadores.deleteUtilizador(email);
        return "redirect:/admin/utilizadores";
    }



    @PostMapping("/registar")
    public ModelAndView registaUtilizador(@Valid @ModelAttribute UtilizadorDTO utilizador, BindingResult bindingResult,Model model, Principal principal){

        ModelAndView modeloEVista = new ModelAndView();
        if(bindingResult.hasErrors()){
            modeloEVista.addObject(utilizador);
            modeloEVista.setViewName("registar");
            return modeloEVista;
        }

        if (!proxyMicroservicoUtilizadores.emailUsado(utilizador.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "Utilizador ou Email já Registado!");
            modeloEVista.addObject("utilizador", utilizador);
            modeloEVista.setViewName("registar");
            return modeloEVista;
        }

        proxyMicroservicoUtilizadores.save(utilizador);
        modeloEVista.setViewName("redirect:/");
        return modeloEVista;
    }

    @GetMapping(value = "/formregistar")
    public String getIndex(Model model){
        model.addAttribute("utilizador",new UtilizadorDTO());
        return "registar.html";
    }
}
