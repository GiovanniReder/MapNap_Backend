package giovanni.MapNap.payloads;

import java.util.UUID;

public record UserLoginResponseDTO(String accessToken, String name, String avatar, UUID userId) {
}
