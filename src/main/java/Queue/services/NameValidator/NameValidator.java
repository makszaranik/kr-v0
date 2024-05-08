package Queue.services.NameValidator;

public class NameValidator {
    public static boolean isValidName(String name){
        return (name != null && !name.trim().isEmpty());
    }
}
