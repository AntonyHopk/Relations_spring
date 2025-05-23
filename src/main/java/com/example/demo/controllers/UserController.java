package com.example.demo.controllers;

import com.example.demo.entity.Payloads.UserPayload;
import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
//class UserController {
//    private final UserService userService;
//
//    @GetMapping()
//    List<User> getUsers() {
//        return userService.findUsers();
//    }
//
//    @GetMapping("/{userid}")
//    User getUser(@PathVariable("userid") int id) {
//        return userService.findUser(id).orElseThrow(() -> new NoSuchElementException("User not found"));
//    }
//
//    @PostMapping()
//    void createUser(@RequestBody User user) {
//        userService.saveUser(user);
//    }
//
//    @PutMapping("/{id}")
//    void updateUser(@PathVariable int id, @RequestBody @Valid UserPayload user) {
//        userService.updateUser(id, user);
//    }
//
//    @DeleteMapping("/{id}")
//    void deleteUser(@PathVariable int id) {
//        userService.deleteUser(id);
//    }
////    @DeleteMapping()
////    void deleteUser(@RequestParam(required = false) int id) {
////        userService.deleteUser(id);
////    }
//
//}
class UserController {
    private final UserService userService;

    @Operation(summary = "Получить всех пользователей!", tags = {"Users"}, description = "adssajb fajhsdbkhjabs", responses = {
            @ApiResponse(responseCode = "200", description = "Пользователи найден")
    })
    @GetMapping
    public ResponseEntity<Iterable<User>> getUsers() {
        return ResponseEntity.ok(userService.findUsers());
    }

    @Operation(summary = "Получить пользователя по ид", tags = {"Users"}, description = "adssajb fajhsdbkhjabs", responses = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден"),
            @ApiResponse(responseCode = "404", description = "User not found")
    }, parameters = {
            @Parameter(name = "id", description = "ID пользователя", required = true)
    }
    )
    @GetMapping("/{userid}")
    public ResponseEntity<User> getUser(@PathVariable("userid") int id) {
        return userService.findUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Создать пользователя",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные нового пользователя",
                    required = true
            ), description = "adssajb fajhsdbkhjabs", responses = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден")}, tags = {"Users"}
    )
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserPayload user) {
        User saved = userService.saveUser(user);
        return ResponseEntity.status(201).body(saved);
    }

    @Operation(
            summary = "Изменить данные пользователя",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные пользователя для изменения",
                    required = true
            ), description = "adssajb fajhsdbkhjabs", responses = {
            @ApiResponse(responseCode = "204", description = "Удачное обновление"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")}, parameters = {
            @Parameter(name = "id", description = "ID пользователя", required = true)
    }, tags = {"Users"}
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody UserPayload user) {
        Optional<User> existingUser = userService.findUser(id);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userService.updateUser(id, user);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Удалить пользователя",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Удачное удаление"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден")}, parameters = {
            @Parameter(name = "id", description = "ID пользователя", required = true)
    }, tags = {"Users"}
    )
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam(required = false) Integer id) {
        if (id == null || userService.findUser(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}