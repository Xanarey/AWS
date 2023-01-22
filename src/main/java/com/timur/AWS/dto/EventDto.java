package com.timur.AWS.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.timur.AWS.model.Event;
import com.timur.AWS.model.Status;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto {

    private Long id;
    private String created;
    private String updated;
    private Status status;

    public static EventDto fromEvent(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setCreated(event.getCreated());
        eventDto.setUpdated(event.getUpdated());
        eventDto.setStatus(event.getStatus());
        return eventDto;
    }
}
