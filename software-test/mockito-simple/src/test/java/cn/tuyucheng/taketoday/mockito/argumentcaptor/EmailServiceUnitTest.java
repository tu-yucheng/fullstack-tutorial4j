package cn.tuyucheng.taketoday.mockito.argumentcaptor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class EmailServiceUnitTest {

    @Mock
    DeliveryPlatform platform;

    @InjectMocks
    EmailService emailService;

    @Captor
    ArgumentCaptor<Email> emailCaptor;

    @Captor
    ArgumentCaptor<Credentials> credentialsCaptor;

    @Test
    void whenDoesNotSupportHtml_expectTextOnlyEmailFormat() {
        String to = "info@tuyucheng.com";
        String subject = "Using ArgumentCaptor";
        String body = "Hey, let'use ArgumentCaptor";

        emailService.send(to, subject, body, false);

        Mockito.verify(platform).deliver(emailCaptor.capture());
        Email emailCaptorValue = emailCaptor.getValue();
        assertThat(emailCaptorValue.getFormat()).isEqualTo(Format.TEXT_ONLY);
    }

    @Test
    void whenDoesSupportHtml_expectHTMLEmailFormat() {
        String to = "info@tuyucheng.com";
        String subject = "Using ArgumentCaptor";
        String body = "<html><body>Hey, let'use ArgumentCaptor</body></html>";

        emailService.send(to, subject, body, true);

        Mockito.verify(platform).deliver(emailCaptor.capture());
        Email value = emailCaptor.getValue();
        assertThat(value.getFormat()).isEqualTo(Format.HTML);
    }

    @Test
    void whenServiceRunning_expectUpResponse() {
        Mockito.when(platform.getServiceStatus()).thenReturn("OK");

        ServiceStatus serviceStatus = emailService.checkServiceStatus();

        assertThat(serviceStatus).isEqualTo(ServiceStatus.UP);
    }

    @Test
    void whenServiceNotRunning_expectDownResponse() {
        Mockito.when(platform.getServiceStatus()).thenReturn("Error");

        ServiceStatus serviceStatus = emailService.checkServiceStatus();

        assertThat(serviceStatus).isEqualTo(ServiceStatus.DOWN);
    }

    @Test
    void whenUsingArgumentMatcherForValidCredentials_expectTrue() {
        Credentials credentials = new Credentials("tuyucheng", "correct_password", "correct_key");
        Mockito.when(platform.authenticate(Mockito.eq(credentials))).thenReturn(AuthenticationStatus.AUTHENTICATED);

        assertTrue(emailService.authenticatedSuccessfully(credentials));
    }

    @Test
    void whenUsingArgumentCaptorForValidCredentials_expectTrue() {
        Credentials credentials = new Credentials("tuyucheng", "correct_password", "correct_key");
        Mockito.when(platform.authenticate(credentialsCaptor.capture())).thenReturn(AuthenticationStatus.AUTHENTICATED);

        assertTrue(emailService.authenticatedSuccessfully(credentials));
        assertThat(credentialsCaptor.getValue()).isEqualTo(credentials);
    }

    @Test
    void whenNotAuthenticated_expectFalse() {
        Credentials credentials = new Credentials("tuyucheng", "incorrect_password", "incorrect_key");
        Mockito.when(platform.authenticate(Mockito.eq(credentials))).thenReturn(AuthenticationStatus.NOT_AUTHENTICATED);

        assertFalse(emailService.authenticatedSuccessfully(credentials));
    }
}