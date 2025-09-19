package com.carconnect.api.interfaces.web;

import com.carconnect.api.application.ClienteService;
import com.carconnect.api.domain.Cliente;
import com.carconnect.api.domain.Empregador;
import com.carconnect.api.domain.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.UUID;

@Controller
@RequestMapping("/clientes")
public class ClienteWebController {
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "clientes/lista";
    }
    
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form";
    }
    
    @GetMapping("/{id}")
    public String visualizar(@PathVariable UUID id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        model.addAttribute("cliente", cliente);
        return "clientes/detalhes";
    }
    
    @GetMapping("/{id}/editar")
    public String editar(@PathVariable UUID id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        model.addAttribute("cliente", cliente);
        return "clientes/form";
    }
    
    @PostMapping
    public String salvar(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        try {
            clienteService.salvar(cliente);
            redirectAttributes.addFlashAttribute("success", "Cliente salvo com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao salvar cliente: " + e.getMessage());
        }
        return "redirect:/clientes";
    }
    
    @PostMapping("/{id}")
    public String atualizar(@PathVariable UUID id, @ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        try {
            clienteService.atualizar(id, cliente);
            redirectAttributes.addFlashAttribute("success", "Cliente atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao atualizar cliente: " + e.getMessage());
        }
        return "redirect:/clientes";
    }
    
    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            clienteService.excluir(id);
            redirectAttributes.addFlashAttribute("success", "Cliente excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao excluir cliente: " + e.getMessage());
        }
        return "redirect:/clientes";
    }
    
    @PostMapping("/{id}/empregadores")
    public String adicionarEmpregador(@PathVariable UUID id, 
                                    @RequestParam String razaoSocial,
                                    @RequestParam BigDecimal rendimentoMensal,
                                    RedirectAttributes redirectAttributes) {
        try {
            Empregador empregador = new Empregador(razaoSocial, rendimentoMensal);
            clienteService.adicionarEmpregador(id, empregador);
            redirectAttributes.addFlashAttribute("success", "Empregador adicionado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao adicionar empregador: " + e.getMessage());
        }
        return "redirect:/clientes/" + id;
    }
    
}
