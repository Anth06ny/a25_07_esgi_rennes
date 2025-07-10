package org.example.a25_07_esgi_rennes.controller;

import jakarta.servlet.http.HttpSession;
import org.example.a25_07_esgi_rennes.model.StudentBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class MyController {




    //http://localhost:8080/hello
    @GetMapping("/hello")
    public String testHello(Model model, HttpSession session) {
        System.out.println("/hello");

        ArrayList<StudentBean> liststudent = new ArrayList<>();
        liststudent.add(new StudentBean("Toto", 12));
        liststudent.add(new StudentBean("Tata", 14));

        model.addAttribute("texte", "Bonjour " + session.getId());
        model.addAttribute("studentBean", new StudentBean("Toto", 12));
        model.addAttribute("studentList", liststudent);

        //Nom du fichier HTML que l'on souhaite afficher
        return "welcome";
    }

}
