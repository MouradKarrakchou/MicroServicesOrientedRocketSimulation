package fr.teama.payloadservice.connectors.externalDTO;

import java.util.List;

public class TrackingDTO {

    private final List<TrackItemDTO> data;
    private final String serviceToBeNotified;

    public TrackingDTO(List<TrackItemDTO> data, String requestService) {
        this.data = data;
        this.serviceToBeNotified = requestService;
    }

}
