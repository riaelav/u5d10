package valeriapagliarini.u5d10.payloads;

import jakarta.validation.constraints.NotNull;
import valeriapagliarini.u5d10.enums.TravelStatus;

public record UpdateTravelStatusDTO(
        @NotNull(message = "Status is required")
        TravelStatus status
) {
}
