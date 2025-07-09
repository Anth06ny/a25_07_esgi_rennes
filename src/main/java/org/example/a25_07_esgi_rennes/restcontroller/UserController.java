package org.example.a25_07_esgi_rennes.restcontroller;

import org.example.a25_07_esgi_rennes.model.UserBean;
import org.example.a25_07_esgi_rennes.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    //http://localhost:8080/users
    @GetMapping
    public ResponseEntity<List<UserBean>> getAllUsers() {
        List<UserBean> users = UserService.load();
        return ResponseEntity.ok(users);
    }

    //http://localhost:8080/users/1
    @GetMapping("/{id}")
    public ResponseEntity<UserBean> getUserById(@PathVariable Long id) {
        UserBean userBean = UserService.findById(id);
        if (userBean != null) {
            return ResponseEntity.ok(userBean);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://localhost:8080/users
    //{"login":"aaa", "password":"bbb"}
    @PostMapping
    public ResponseEntity<UserBean> createUser( @RequestBody UserBean user) {
        UserBean savedUser = UserService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //http://localhost:8080/users/1
    //{"login":"aaa", "password":"bbb"}
    @PutMapping("/{id}")
    public ResponseEntity<UserBean> updateUser(@PathVariable Long id, @RequestBody UserBean userDetails) {
        UserBean user = UserService.findById(id);
        if (user != null) {
            userDetails.setId(id);//écrase celui reçu dans le JSON au cas ou
            UserService.save(userDetails);
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://localhost:8080/users/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (UserService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
