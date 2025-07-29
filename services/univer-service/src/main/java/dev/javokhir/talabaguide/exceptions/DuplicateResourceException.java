package dev.javokhir.talabaguide.exceptions;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String fieldName,String value) {
        super(fieldName + " already exists: " + value);
    }
}
