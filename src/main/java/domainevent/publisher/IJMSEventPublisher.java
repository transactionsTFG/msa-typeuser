package domainevent.publisher;

import msa.commons.event.EventId;
import msa.commons.event.EventResponse;

public interface IJMSEventPublisher {
    void publish(EventId event, EventResponse eventResponse);    
}
