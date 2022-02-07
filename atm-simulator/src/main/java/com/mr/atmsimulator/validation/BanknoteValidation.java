package com.mr.atmsimulator.validation;

import com.mr.atmsimulator.atm.denomination.Denomination;
import com.mr.atmsimulator.banknote.Banknote;

import java.util.*;
import java.util.stream.Collectors;

public class BanknoteValidation {

    public static Map<Banknote, Integer> checkBanknoteAndReturnIncorrect(Map<Banknote, Integer> banknotes) {

        Set<Denomination> denominations = new HashSet<>(Arrays.asList(Denomination.values()));

        return banknotes.entrySet().stream()
                .filter(o -> !denominations.contains(o.getKey().getDenomination()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
