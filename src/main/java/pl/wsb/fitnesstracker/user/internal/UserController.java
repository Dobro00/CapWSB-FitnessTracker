package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllUsersSimple(){
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }
    @GetMapping("/older/{time}")
    public  List<UserDto> getOlderUsers(@PathVariable LocalDate time){
        return userService.getOlderUsers(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
    @GetMapping("/{id}")
    public Optional<UserDto> getUserbyID(@PathVariable  Long id){
        return userService.getUser(id);
    }
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws InterruptedException {

        User user = userMapper.toEntity(userDto);
        userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email")
    public List<UserEmailDto> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
}