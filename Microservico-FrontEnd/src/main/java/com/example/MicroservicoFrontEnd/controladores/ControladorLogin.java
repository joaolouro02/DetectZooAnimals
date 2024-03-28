package com.example.MicroservicoFrontEnd.controladores;

import com.example.MicroservicoFrontEnd.modulos.AnimalDTO;
import com.example.MicroservicoFrontEnd.modulos.UtilizadorDTO;
import com.example.MicroservicoFrontEnd.proxys.ProxyMicroservicoAnimais;
import com.example.MicroservicoFrontEnd.proxys.ProxyMicroservicoUtilizadores;
import com.example.MicroservicoFrontEnd.modulos.Tarefa;
import com.example.MicroservicoFrontEnd.repositorios.RepositorioTarefa;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ControladorLogin {

    @Autowired
    public RepositorioTarefa repositorioTarefa;

    @Autowired
    private ProxyMicroservicoUtilizadores proxyMicroservicoUtilizadores;

    @Autowired
    private ProxyMicroservicoAnimais proxyMicroservicoAnimais;

    @GetMapping({"/","/logout"})
    public String getIndex(){
        return "index";
    }



    @PostMapping("/login")
    public String getLogin(HttpSession session, Model model, @RequestParam("email") String email, @RequestParam("password") String password){

        if(proxyMicroservicoUtilizadores.utilizadorExiste(email,password)) {

            session.setAttribute("email", email);
            String role = proxyMicroservicoUtilizadores.getRole(email);
            String nome=proxyMicroservicoUtilizadores.getNome(email);
            model.addAttribute("nome",nome);
            model.addAttribute("role",role);
            model.addAttribute("email",email);

            if ("ROLE_ADMIN".equals(role)) {
                return "redirect:/admin/utilizadores";
            } else {
                List<Tarefa> tarefas = repositorioTarefa.findAll();
                model.addAttribute("tarefas", tarefas);
                Map<String, String> animais=proxyMicroservicoAnimais.getNomeAnimais();
                model.addAttribute("animais",animais);
                return "paginaInicial";
            }
        }
        return "index";
    }

    @GetMapping("/paginaInicial")
    public String getPaginaInicial(HttpSession session,Model model){
        String email= (String) session.getAttribute("email");
        String role = proxyMicroservicoUtilizadores.getRole(email);
        String nome=proxyMicroservicoUtilizadores.getNome(email);
        model.addAttribute("nome",nome);
        model.addAttribute("role",role);
        List<Tarefa> tarefas = repositorioTarefa.findAll();
        model.addAttribute("tarefas", tarefas);
        Map<String, String> animais=proxyMicroservicoAnimais.getNomeAnimais();
        model.addAttribute("animais",animais);
        return "paginaInicial";
    }


    @PostMapping("/detalhes-tarefa")
    public String detalhesTarefa(@RequestParam("tarefaId") String tarefaId, Model model) {
        String fileName = repositorioTarefa.findFileNameById(tarefaId);

        Tarefa t=repositorioTarefa.findTarefaById(tarefaId);
        model.addAttribute("fileName", fileName);
        model.addAttribute("tarefa",t);

        return "tarefa";
    }

}
