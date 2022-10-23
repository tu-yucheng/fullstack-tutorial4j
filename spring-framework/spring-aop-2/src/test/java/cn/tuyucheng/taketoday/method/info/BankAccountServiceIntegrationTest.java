package cn.tuyucheng.taketoday.method.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BankAccountServiceIntegrationTest {

    @Autowired
    BankAccountService bankAccountService;
    private Account account;

    @BeforeEach
    public void setup() {
        account = new Account();
        account.setAccountNumber("12345");
        account.setBalance(2000.0);
    }

    @Test
    void withdraw() {
        bankAccountService.withdraw(account, 500.0);
        assertEquals(1500.0, account.getBalance());
    }

    @Test
    void withdrawWhenLimitReached() {
        assertThatExceptionOfType(WithdrawLimitException.class)
                .isThrownBy(() -> bankAccountService.withdraw(account, 600.0));
        assertEquals(2000.0, account.getBalance());
    }

    @Test
    void deposit() {
        bankAccountService.deposit(account, 500.0);
        assertEquals(2500.0, account.getBalance());
    }

    @Test
    void getBalance() {
        bankAccountService.getBalance();
    }
}