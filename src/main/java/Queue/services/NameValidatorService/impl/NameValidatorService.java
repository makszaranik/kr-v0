package Queue.services.NameValidatorService.impl;

import Queue.services.NameValidatorService.AbstractNameValidatorService;

public class NameValidatorService implements AbstractNameValidatorService {
    public  boolean isValidName(String name){
        return (name != null && !name.trim().isEmpty());
    }
}
