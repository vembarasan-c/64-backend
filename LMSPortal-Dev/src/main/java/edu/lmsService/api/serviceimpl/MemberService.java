package edu.lmsService.api.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.lmsService.api.model.dao.MemberDAO;
import edu.lmsService.api.model.dto.MemberDTO;
import edu.lmsService.api.repo.IMemberRepository;
import edu.lmsService.api.service.IMemberService;
import edu.lmsService.api.util.MapperUtil;
@Service
public class MemberService implements IMemberService{
    
    private final IMemberRepository MemberRepo;

    public MemberService(IMemberRepository MemberRepo) {
        this.MemberRepo = MemberRepo;
    }



    @Override
    public Boolean createMember(MemberDTO MemberDetail) {
        if (!MemberDetail.getEmail().isEmpty() && !MemberRepo.findByEmail(MemberDetail.getEmail()).isPresent()){
            MemberDAO MemberData = MapperUtil.map(MemberDetail, MemberDAO.class);
            MemberRepo.save(MemberData);


            return true;
        }
        return false;
    }

            @Override
    public List<MemberDTO> getAllMember () {
        var MemberDatas = MemberRepo.findAll();
        List<MemberDTO> MemberResponse = new ArrayList<>();
        for (MemberDAO Member : MemberDatas){
            MemberDTO MemberDetail = MapperUtil.map(Member, MemberDTO.class);
//            MemberDetail.setKey((MemberResponse.size() +1)+"");
            MemberResponse.add(MemberDetail);
        }
        return MemberResponse;
    }
}