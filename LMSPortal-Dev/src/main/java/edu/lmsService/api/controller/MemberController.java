package edu.lmsService.api.controller;

import java.util.List;

import edu.lmsService.api.model.dto.UserDTO;
import edu.lmsService.api.service.IUserService;
import edu.lmsService.api.serviceimpl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.lmsService.api.model.dto.MemberDTO;
import edu.lmsService.api.service.IMemberService;
import edu.lmsService.api.service.IUserService;



@RestController
@RequestMapping("/members")
public class MemberController {


    private final IMemberService MemberService;

    private final IUserService UserService;


    public MemberController(IMemberService MemberService, IUserService userService) { this.MemberService = MemberService;
        UserService = userService;
    }




    @GetMapping("/getAllMember")
    public List<MemberDTO> getAllMember() {
        return MemberService.getAllMember();
    }


    @PostMapping("/createMember")
    public ResponseEntity<String> postMethodName(@RequestBody MemberDTO MemberDetail) {
        Boolean isCreated = MemberService.createMember(MemberDetail);

        if (isCreated){

           String email =  MemberDetail.getEmail();
           String password = MemberDetail.getPassword();

           UserDTO user = new UserDTO();
           user.setEmail(email);
           user.setPassword(password);
           UserService.registerUser(user);


            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Member created successfully");



        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Member creation failed");
    }

}
