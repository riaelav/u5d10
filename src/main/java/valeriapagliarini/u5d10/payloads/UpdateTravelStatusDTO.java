package valeriapagliarini.u5d10.payloads;

import jakarta.validation.constraints.NotNull;

public record UpdateTravelStatusDTO(
        @NotNull(message = "Status is required")
        String status
) {
}
