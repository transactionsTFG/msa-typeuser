package domainevent.command.handler;

import msa.commons.event.EventResponse;

public interface EventTypeUserHandler {
    void handle(EventResponse eventResponse);
}
