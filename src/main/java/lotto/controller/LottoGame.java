package lotto.controller;

import static lotto.view.LottoBuyer.purchaseLotto;
import static lotto.view.LottoBuyer.purchaseLottoCount;
import static lotto.view.BonusInput.*;
import static lotto.view.WinningInput.*;

import java.util.LinkedHashMap;
import java.util.Map;
import lotto.domain.LottoRank;
import lotto.domain.ErrorMessage;

import java.util.List;
import lotto.view.Message;

public class LottoGame {
    private static final int LOTTO_PRICE = 1000;
    private static final int PERCENT = 100;
    private static int winMoney = 0;

    public void start() {
        List<List<Integer>> lottos;
        lottos = purchaseLotto();
        winningNumberInput();
        bonusNumberInput();
        validateDuplicate(winningInput, bonusInput);
        getWinningResult(lottos, winningNumbers);
        getBonusResult(lottos);
        getTotalResult();
    }

    private static void getTotalResult() {
        Map<LottoRank, Integer> result = setToMap();
        LottoRank rank;
        double purchasePrice = purchaseLottoCount * LOTTO_PRICE;
        System.out.println(Message.WINNING_STATS.getMessage());
        for (int i = 0; i < winningCount; i++) {
            rank = LottoRank.getRankResult(winningCount, bonus);
            result.put(rank, result.get(rank) + 1);
            winMoney = rank.getMoney();
        }
        printWinningList(result);
        System.out.printf("총 수익률은 " + String.format("%.1f", getWinningRate(purchasePrice)) + "%%입니다.");
    }

    private static double getWinningRate(double purchasePrice) {
        return (winMoney / purchasePrice) * PERCENT;
    }

    private static void printWinningList(Map<LottoRank, Integer> result) {
        for (LottoRank rank : LottoRank.values()) {
            if (rank.getMoney() == 0) {
                continue;
            }
            System.out.println(rank.getMessage() + result.get(rank) + "개");
        }
    }

    private static Map<LottoRank, Integer> setToMap() {
        Map<LottoRank, Integer> result = new LinkedHashMap<>();
        for (LottoRank rank : LottoRank.values()) {
            result.put(rank, 0);
        }
        return result;
    }

    private static void validateDuplicate(String winningNumber, String bonusNumber) {
        String[] dupliateCheck = winningNumber.split(",");
        for (String str : dupliateCheck) {
            if (str.equals(bonusNumber)) {
                throw new IllegalArgumentException(ErrorMessage.ERROR_DUPLICATE.getErrorMessage());
            }
        }
    }
}
