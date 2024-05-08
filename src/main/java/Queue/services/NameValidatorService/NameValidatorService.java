package Queue.services.NameValidatorService;

public class NameValidatorService {
    public static boolean isValidName(String name){
        return (name != null && !name.trim().isEmpty());
    }
}
