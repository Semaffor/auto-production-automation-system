package by.bsuir.app.service.impl;

import by.bsuir.app.dao.AccountDao;
import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.enums.Role;
import by.bsuir.app.service.AccountService;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest extends TestCase {
    @Mock
    private AccountDao accountDao;

    @InjectMocks
    private AccountService accountService = new AccountServiceImpl(accountDao);

    private Account account;

    @Before
    public void initializationData() {
        account = new Account();
        account.setLogin("Dima");
    }

    @Test
    public void testUserAuthenticationWhenGivenLoginReturnTrue() {

        given(accountDao.auth(account)).willReturn(Role.USER);

        boolean result = accountService.auth(account);

        Assert.assertTrue(result);
        verify(accountDao).auth(account);
    }

    @Test
    public void testUserAuthenticationWhenGivenWrongLoginReturnFalse() {
        given(accountDao.auth(account)).willReturn(null);

        boolean result = accountService.auth(account);

        Assert.assertFalse(result);
        verify(accountDao).auth(account);
    }

    @Test
    public void testUserFindByLoginWhenGivenExistingLoginReturnUser() {
        given(accountDao.findByLogin("Dima")).willReturn(account);

        Account resultUser = accountService.findByLogin("Dima");

        Assert.assertEquals(account, resultUser);
    }

    @Test
    public void testFindAllByCriteriaWhenGivenCriteriaReturnNotNullList() {
        List<Account> localUsers = new ArrayList<>();
        localUsers.add(new Account(1L, "Dima","Dima"));
        localUsers.add(new Account(2L, "Pasha","Pasha"));

        given(accountDao.findAllByCriteria(anyString())).willReturn(localUsers);

        List mockitoList = Mockito.mock(List.class);
        ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);

        mockitoList.addAll(localUsers);

        List<Account> users = accountService.findAllByCriteria("name");

        Assert.assertEquals(localUsers, users);
    }

    @Test
    public void testResetPasswordWhenGivenUserReturnPositive() {
        given(accountDao.saveOrUpdate(account)).willReturn(true);

        boolean result = accountService.resetPassword(account);
        Assert.assertTrue(result);
    }

    @Test
    public void testResetPasswordWhenGivenUserReturnNegative() {
        given(accountDao.saveOrUpdate(account)).willReturn(false);

        boolean result = accountService.resetPassword(account);
        Assert.assertFalse(result);
    }

    @Test
    public void testRegistrationWhenRepeatLoginReturnFalse() {
        given(accountDao.findByLogin("Dima")).willReturn(new Account(1L, "Borya", "Borya"));
//        given(userDao.saveOrUpdate(any())).willReturn(true);

        boolean result = accountService.registration(account);

        assertFalse(result);
    }

    @Test
    public void testRegistrationWhenLoginNotExistReturnTrue() {
        given(accountDao.findByLogin("Dima")).willReturn(new Account());
        given(accountDao.saveOrUpdate(any())).willReturn(true);

        boolean result = accountService.registration(account);

        assertTrue(result);
    }

    @Test
    public void testAddEntranceLog() {
        accountService.addEntranceLog(account);
        Mockito.verify(accountDao, Mockito.times(1)).saveOrUpdate(any(Account.class));
    }
}