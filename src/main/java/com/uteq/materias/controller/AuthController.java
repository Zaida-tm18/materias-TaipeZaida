package com.uteq.materias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// GET /login muestra el formulario. El POST /login es procesado internamente
// por el filtro de Spring Security (SecurityConfig -> formLogin), que valida
// las credenciales contra la BD usando BCryptPasswordEncoder.matches().
@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                             @RequestParam(value = "logout", required = false) String logout,
                             org.springframework.ui.Model model) {
        if (error != null) {
            model.addAttribute("error", "Usuario o contrasena incorrectos.");
        }
        if (logout != null) {
            model.addAttribute("logout", "Sesion cerrada correctamente.");
        }
        return "login";
    }
}
