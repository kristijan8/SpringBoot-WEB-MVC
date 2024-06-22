package com.rungroop.web.controller;

import com.rungroop.web.dto.EventDto;
import com.rungroop.web.mapper.EventMapper;
import com.rungroop.web.models.Club;
import com.rungroop.web.models.Event;
import com.rungroop.web.service.EventService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private EventService eventService;
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    //create
    @GetMapping("/events/{clubId}/new")
    public String newEventForm(@PathVariable("clubId") Long clubId, Model model)
    {
        Event event=new Event();
        model.addAttribute("clubId",clubId);
        model.addAttribute("event",event);
        return "events-create";
    }
    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("event") EventDto eventDto, Model model, BindingResult result)
    {
        if (result.hasErrors())
        {
            model.addAttribute("event",eventDto);
            return "clubs-create";
        }
        eventService.createEvent(clubId,eventDto);
        return "redirect:/clubs/"+clubId;
    }


    //list
    @GetMapping("/events")
    public String eventLis(Model model)
    {
        List<EventDto> events=eventService.findAllEvents();
        model.addAttribute("events",events);
        return "events-list";
    }


    //detail
    @GetMapping("/events/{eventId}")
    public String eventDetail(@PathVariable("eventId") Long eventId, Model model)
    {
        EventDto eventDto=eventService.findByEventId(eventId);
        model.addAttribute("event",eventDto);
        return "events-detail";
    }


    //update
    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Long eventId, Model model)
    {
        EventDto eventDto=eventService.findByEventId(eventId);
        model.addAttribute("event",eventDto);
        return "events-edit";
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") Long eventId, @Valid @ModelAttribute("event") EventDto eventDto, Model model, BindingResult result)
    {
        if (result.hasErrors())
        {
            model.addAttribute("event",eventDto);
            return "events-edit";
        }
        EventDto eventDto1=eventService.findByEventId(eventId);
        Club club=eventDto1.getClub();

        eventDto.setId(eventId);
        eventDto.setClub(club);

        eventService.updateEvent(eventDto);
        return "redirect:/events";
    }





}
