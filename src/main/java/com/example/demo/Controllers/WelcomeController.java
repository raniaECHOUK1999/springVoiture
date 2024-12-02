package com.example.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    // Méthode qui retourne la vue de bienvenue
    @GetMapping("/")
    public String welcome() {
        return "welcome"; // Nom de la vue sans l'extension .html
    }
}
