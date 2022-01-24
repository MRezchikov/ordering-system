package com.mr.atmsymulator;

import com.mr.atmsymulator.banknote.Banknote;

import java.util.LinkedHashMap;
import java.util.Map;

public class AtmApplication {

    public static void main(String[] args) {

        int money = 16774;
        takeBanknotes(money);
    }

    private static void takeBanknotes(int money) {

        Map<Integer, Banknote> cells = getIntegerBanknoteMap();

        int tempMoney = money;

        for (Map.Entry<Integer, Banknote> entry : cells.entrySet()) {

            Integer denomination = entry.getKey();
            int remainder = tempMoney % denomination;
            System.out.println("remainder = " + remainder);
            int tookBanknoteDenomination = tempMoney - remainder;
            int count = count(tookBanknoteDenomination, denomination);
            System.out.println("count = " + count);
            if (count == 1) {
                tookBanknoteDenomination = tempMoney - remainder;
            } else if (count > 1) {
                tookBanknoteDenomination /= count;
            }
            System.out.println("tookBanknoteDenomination = " + tookBanknoteDenomination);
            tempMoney = remainder;
            System.out.println("tempMoney = " + tempMoney);

        }
    }

    private static Map<Integer, Banknote> getIntegerBanknoteMap() {
        Map<Integer, Banknote> cells = new LinkedHashMap<>();

        cells.put(5000, new Banknote(5000));
        cells.put(1000, new Banknote(1000));
        cells.put(500, new Banknote(500));
        cells.put(100, new Banknote(100));
        cells.put(50, new Banknote(50));
        cells.put(10, new Banknote(10));
        cells.put(1, new Banknote(1));
        return cells;
    }

    public static int count(int n, int d) {
        return n / d;
    }
}
