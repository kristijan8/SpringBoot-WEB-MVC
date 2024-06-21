package com.rungroop.web.service;

import com.rungroop.web.dto.EventDto;
import com.rungroop.web.models.Event;
import java.util.List;

public interface EventService {
    void createEvent(Long ClubId, EventDto eventDto);


    List<EventDto> findAllEvents();
}
