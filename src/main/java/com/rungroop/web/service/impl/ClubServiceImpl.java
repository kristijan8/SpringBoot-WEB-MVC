package com.rungroop.web.service.impl;
import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.repository.ClubRepository;
import com.rungroop.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import com.rungroop.web.models.Club;
import org.springframework.stereotype.Service;
import com.rungroop.web.mapper.ClubMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubRepository clubRepository;


    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }
    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map(club -> ClubMapper.mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(ClubDto clubDto) {
        Club club=ClubMapper.mapToClub(clubDto);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(Long clubId) {
        Club club= clubRepository.findById(clubId).get();
        return ClubMapper.mapToClubDto(club);

    }

    @Override
    public void updateClub(ClubDto clubDto) {
        Club club=ClubMapper.mapToClub(clubDto);
        clubRepository.save(club);
    }

    @Override
    public void deleteClub(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(club -> ClubMapper.mapToClubDto(club)).collect(Collectors.toList());
    }
    
    








}
