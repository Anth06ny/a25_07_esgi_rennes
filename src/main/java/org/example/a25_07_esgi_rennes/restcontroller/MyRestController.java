package org.example.a25_07_esgi_rennes.restcontroller;

import org.example.a25_07_esgi_rennes.model.StudentBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    //http://localhost:8080/receiveStudent
//Json Attendu : {"name": "toto", "note": 12}
    @PostMapping("/receiveStudent")
    public void receiveStudent(@RequestBody StudentBean student) {
        System.out.println("/receiveStudent : " + student.getName() + " : " + student.getNote());

        //traitement, mettre en base…
        //Retourner d'autres données
    }

    //http://localhost:8080/max?p1=5&p2=6
    @GetMapping("/max")
    public Integer max(Integer p1, Integer p2) {
        System.out.println("/max");

        if (p1 == null) {
            return p2;
        } else if (p2 == null) {
            return p1;
        }

        return Math.max(p1, p2);
    }

    //http://localhost:8080/max2?p1St=5&p2St=toto
    @GetMapping("/max2")
    public Integer max2(String p1St, String p2St) {
        System.out.println("/max2");
        Integer p1 = null, p2 = null;
        try {
            p1 = Integer.parseInt(p1St);
        } catch (Exception e) {
        }
        try {
            p2 = Integer.parseInt(p2St);
        } catch (Exception e) {
        }

        if (p1 == null) {
            return p2;
        } else if (p2 == null) {
            return p1;
        }

        return Math.max(p1, p2);
    }

    //http://localhost:8080/test
    @GetMapping("/test")
    public String testMethode() {
        System.out.println("/test");

        return "<b>helloWorld</b>";
    }


    //http://localhost:8080/getStudent
    @GetMapping("/getStudent")
    public StudentBean getStudent() {
        System.out.println("/getStudent");

        StudentBean studentBean = new StudentBean("toto", 12);
        return studentBean;
    }

}
