package com.rungroop.web.controller;
import java.util.List;
import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.Club;
import com.rungroop.web.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClubController {
    private ClubService clubService;


    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }


    //get all clubs
    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs",clubs);
        return "clubs-list";

    }



    //create club
    @GetMapping("/clubs/new")
    public String createClubForm(Model model)
    {
        Club club=new Club();
        model.addAttribute("club",club);
        return "clubs-create";
    }

    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto,
                           BindingResult result,
                           Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("club",clubDto);
            return "clubs-create";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }



    //edit club
    @GetMapping("clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long clubId,Model model)
    {
        ClubDto club=clubService.findClubById(clubId);
        model.addAttribute("club",club);
        return "clubs-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId,
                             @Valid @ModelAttribute("club") ClubDto club,
                             BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("club",club);
            return "clubs-edit";
        }
        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/clubs";

    }



    //details
    @GetMapping("/clubs/{clubId}")
    public String getClub(@PathVariable("clubId") Long clubId,Model model)
    {
        ClubDto clubDto=clubService.findClubById(clubId);
        model.addAttribute("club",clubDto);
        return "clubs-detail";
    }



    //delete
    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId)
    {
        clubService.deleteClub(clubId);
        return "redirect:/clubs";
    }



    //search
    @GetMapping("/clubs/search")
    public String searchClubs(@RequestParam(value = "query") String query,Model model)
    {
        List<ClubDto> clubs=clubService.searchClubs(query);
        model.addAttribute("clubs",clubs);
        return "clubs-list";
    }










}
