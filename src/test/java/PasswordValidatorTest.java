import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.NotificationService;
import com.example.PasswordValidator;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PasswordValidatorTest {

    NotificationService mockService = Mockito.mock(NotificationService.class);
    PasswordValidator validator = new PasswordValidator(mockService);

    // Test for null input:
    @Test
    public void testNullPassword() {
        
        assertFalse(validator.isValidPassword(null));

    }

    // Test for empty string:
    @Test
    public void testEmptyPassword() {
            
        assertFalse(validator.isValidPassword(""));
    
    }

    // Test for passwords shorter than 8 characters:
    @Test
    public void testPasswordTooShort() {
        
        assertFalse(validator.isValidPassword("short"));
        assertFalse(validator.isValidPassword("1234567"));

    }

    // Test for passwords with sufficient length but no uppercase letter:
    @Test
    public void testPasswordWithNoUppercase() {
            
        assertFalse(validator.isValidPassword("1andrea123"));

    }

    // Test for passwords with uppercase but less than 8 characters:
    @Test
    public void testPasswordWithUppercaseButTooShort() {
                
        assertFalse(validator.isValidPassword("ShortA"));
    
    }

    // Test for passwords that meet both condition (valid password):
    @Test
    public void testPasswordWithUppercase() {
                
        assertTrue(validator.isValidPassword("ValidPass123"));
        assertTrue(validator.isValidPassword("HELLO123456"));
        
    }

    @Test
    public void testPasswordValidationWithNotification() {

        // Create a mock of NotificationService
        NotificationService mockService = Mockito.mock(NotificationService.class);

        //PasswordValidator uses the mock service
        PasswordValidator validator = new PasswordValidator(mockService);

        // Validate a password and verify that notification was sent

        validator.validatePasswordAndNotify("ValidPassword1");

        verify(mockService).sendNotification("Password is valid.");

    }

}
