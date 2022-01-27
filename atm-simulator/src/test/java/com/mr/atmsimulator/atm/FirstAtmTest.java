package com.mr.atmsimulator.atm;

import com.mr.atmsimulator.atm.denomination.Denomination;
import com.mr.atmsimulator.atm.strategy.FirstGivingAlgorithm;
import com.mr.atmsimulator.atm.strategy.FirstTakingAlgorithm;
import com.mr.atmsimulator.atm.strategy.GivingAlgorithm;
import com.mr.atmsimulator.atm.strategy.TakingAlgorithm;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.Cell;
import com.mr.atmsimulator.storage.MoneyStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static com.mr.atmsimulator.atm.denomination.Denomination.FIFTY;
import static com.mr.atmsimulator.atm.denomination.Denomination.FIVE_HUNDRED;
import static com.mr.atmsimulator.atm.denomination.Denomination.FIVE_THOUSAND;
import static com.mr.atmsimulator.atm.denomination.Denomination.ONE_HUNDRED;
import static com.mr.atmsimulator.atm.denomination.Denomination.ONE_THOUSAND;
import static com.mr.atmsimulator.atm.denomination.Denomination.TEN;
import static com.mr.atmsimulator.testutil.TestUtil.populateAtm;
import static org.assertj.core.api.Assertions.assertThat;

class FirstAtmTest {

    private Map<Banknote, Integer> banknotes;
    private MoneyStorage moneyStorage;
    private TakingAlgorithm takingAlgorithm;
    private GivingAlgorithm givingAlgorithm;
    private Atm firstAtm;

    @BeforeEach
    void setUp() {
        moneyStorage = new MoneyStorage();
        moneyStorage.setBalanceCash(1000_000L);

        banknotes = new TreeMap<>((k1, k2) ->
                k2.getDenomination().getValue().compareTo(k1.getDenomination().getValue()));
        banknotes.put(new Banknote(FIVE_THOUSAND), 100);
        banknotes.put(new Banknote(ONE_THOUSAND), 100);
        banknotes.put(new Banknote(FIVE_HUNDRED), 100);
        banknotes.put(new Banknote(ONE_HUNDRED), 100);
        banknotes.put(new Banknote(FIFTY), 100);
        banknotes.put(new Banknote(TEN), 100);

        takingAlgorithm = new FirstTakingAlgorithm();
        givingAlgorithm = new FirstGivingAlgorithm(moneyStorage);
        firstAtm = new FirstAtm(takingAlgorithm, givingAlgorithm, moneyStorage);
    }

    @Test
    void shouldReturnMapDenominationCell() {

        Cell cell5000 = new Cell(100L, FIVE_THOUSAND);
        Cell cell1000 = new Cell(100L, ONE_THOUSAND);
        Cell cell500 = new Cell(100L, FIVE_HUNDRED);
        Cell cell100 = new Cell(100L, ONE_HUNDRED);
        Cell cell50 = new Cell(100L, FIFTY);
        Cell cell10 = new Cell(100L, TEN);

        Map<Denomination, Cell> denominationCellMap = firstAtm.takeBanknotes(banknotes);

        assertThat(denominationCellMap)
                .isNotEmpty()
                .hasSize(6)
                .containsKeys(FIVE_THOUSAND, ONE_THOUSAND, FIVE_HUNDRED, ONE_HUNDRED, FIFTY, TEN)
                .containsValues(cell5000, cell1000, cell500, cell100, cell50, cell10);
    }

    @Test
    void shouldReturnBanknoteIntegerMapAndReturnCorrectCashBalance() {

        long expectedCash = 1_549_130L;

        populateAtm(banknotes, firstAtm);

        var requestedCash = 116_870L;

        Map<Banknote, Integer> banknoteIntegerMap = firstAtm.giveBanknotes(requestedCash);
        System.out.println(banknoteIntegerMap);

        assertThat(banknoteIntegerMap)
                .isNotEmpty()
                .hasSize(6)
                .containsKeys(new Banknote(FIVE_THOUSAND),
                        new Banknote(ONE_THOUSAND),
                        new Banknote(FIVE_HUNDRED),
                        new Banknote(ONE_HUNDRED),
                        new Banknote(FIFTY),
                        new Banknote(TEN))
                .containsValues(23, 1, 1, 3, 1, 2);

        assertThat(firstAtm.getBalanceCash()).isEqualTo(expectedCash);
    }
}