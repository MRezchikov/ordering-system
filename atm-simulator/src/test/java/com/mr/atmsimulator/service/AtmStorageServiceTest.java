package com.mr.atmsimulator.service;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.Cell;
import com.mr.atmsimulator.storage.MoneyStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AtmStorageServiceTest {

    private Atm atm;
    private MoneyStorage moneyStorage;

    private AtmStorageService atmStorageService;

    @BeforeEach
    void setUp() {
        atm = mock(Atm.class);
        moneyStorage = mock(MoneyStorage.class);
        atmStorageService = new AtmStorageService(atm, moneyStorage);
    }

    @Test
    void shouldReturnAcceptedAmountCashValue() {

        var cashAmount = 103880L;
        var expected = 103880L;

        List<Banknote> banknotes = new ArrayList<>();
        banknotes.add(new Banknote(20, 5000));
        banknotes.add(new Banknote(3, 1000));
        banknotes.add(new Banknote(1, 500));
        banknotes.add(new Banknote(3, 100));
        banknotes.add(new Banknote(1, 50));
        banknotes.add(new Banknote(3, 10));

        List<Cell> cells = new ArrayList<>();

        when(moneyStorage.getCells()).thenReturn(cells);
        when(atm.takeBanknotes()).thenReturn(cashAmount);
        doNothing().when(moneyStorage).setBalanceCash(cashAmount);
        doNothing().when(moneyStorage).setCells(cells);


        var acceptedBanknotes = atmStorageService.processAcceptedBanknotes(banknotes);

        assertThat(acceptedBanknotes).isEqualTo(expected);
    }

    @Test
    void shouldReturnListOfBanknotes() {

        List<Banknote> banknotes = new ArrayList<>();

        when(atm.giveBanknotes()).thenReturn(banknotes);
        doThrow(RuntimeException.class).when(atm).giveBanknotes();

        List<Banknote> actual = atmStorageService.processGivenBanknotes();
        assertThat(actual).isNull();
    }

    @Test
    void shouldReturnBalanceCash() {

        var balanceCash = 1000_000L;

        moneyStorage.setBalanceCash(balanceCash);

        when(atm.getBalanceCash()).thenReturn(balanceCash);

        var balanceCashRemainder = atmStorageService.getBalanceCash();

        assertThat(balanceCashRemainder).isEqualTo(balanceCash);
    }
}