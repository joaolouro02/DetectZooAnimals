package com.example.MicroservicoFrontEnd.controladores;

import com.example.MicroservicoFrontEnd.modulos.Tarefa;
import com.example.MicroservicoFrontEnd.modulos.UtilizadorDTO;
import com.example.MicroservicoFrontEnd.proxys.ProxyMicroservicoUtilizadores;
import com.example.MicroservicoFrontEnd.repositorios.RepositorioTarefa;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ControladorTarefas {

    @Autowired
    RepositorioTarefa repositorioTarefa;

    @Autowired
    ProxyMicroservicoUtilizadores proxyMicroservicoUtilizadores;

    @GetMapping("/admin/animais")
    public String getTarefas(HttpSession session, Model modelo){
        String email = (String) session.getAttribute("email");

        List<UtilizadorDTO> utilizadores = proxyMicroservicoUtilizadores.getAll();
        Map<Long, List<Tarefa>> tarefasPorUtilizador = new HashMap<>();

        for (UtilizadorDTO utilizador : utilizadores) {
            Long userId = utilizador.getId();
            List<Tarefa> tarefas = repositorioTarefa.findTarefasByUserId(userId);
            tarefasPorUtilizador.put(userId, tarefas);
        }

        modelo.addAttribute("nome",proxyMicroservicoUtilizadores.getNome(email));
        modelo.addAttribute("role", proxyMicroservicoUtilizadores.getRole(email));
        modelo.addAttribute ("tarefasPorUtilizador", tarefasPorUtilizador);
        return "listaTarefas";
    }

    @PostMapping("/admin/tarefas/{id}")
    public String apagarTarefa(@PathVariable String id){

        repositorioTarefa.deleteById(id);
        return "redirect:/admin/animais";
    }
}
