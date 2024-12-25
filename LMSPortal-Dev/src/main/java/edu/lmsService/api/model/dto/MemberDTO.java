package edu.lmsService.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private String name="";
    private String email="";
    private String password="";
    private String address ="";
    private String phoneNumber="";





}
