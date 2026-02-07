import java.util.Optional;

public record User(String name, Optional<String> email) {
}
