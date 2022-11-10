package lotto.domain;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.stream.Stream;

import lotto.view.ErrorMessage;
import lotto.view.Message;

public class WinningLotto {

    private String winningNumber;
    private String bonusNumber;

    public void winningNumberInput() {
        System.out.println(Message.WINNING_NUMBER_INPUT.getMessage());
        winningNumber = readLine();
        validateWinningNumber(winningNumber);
        validateWinningNumberSize(winningNumber);
        validateInputBlank(winningNumber);
        validateInputSeparator(winningNumber);
    }

    public void bonusNumberInput() {
        System.out.println(Message.BONUS_NUMBER_INPUT.getMessage());
        bonusNumber = readLine();
        validateInputBonusNumber(bonusNumber);
    }

    private static void validateWinningNumber(String winningNumber) {
        Integer[] winningNumberArr = Stream.of(winningNumber.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toArray(Integer[]::new);
        for (Integer number : winningNumberArr) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException(ErrorMessage.ERROR_NUMBER_RANGE.getErrorMessage());
            }
        }
    }

    private static void validateWinningNumberSize(String winningNumber) {
        String[] numberSizeCheck = winningNumber.split(",");
        if (numberSizeCheck.length != 6) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_LOTTO_LENGTH.getErrorMessage());
        }
    }

    private static void validateInputBlank(String winningNumber) {
        for (int i = 0; i < winningNumber.length(); i++) {
            if (winningNumber.charAt(i) == ' ') {
                throw new IllegalArgumentException(ErrorMessage.ERROR_BLANK.getErrorMessage());
            }
        }
    }

    private static void validateInputSeparator(String winningNumber) {
        String[] separatorCheck = winningNumber.split(",");
        if (separatorCheck.length != 6) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_SEPARATOR.getErrorMessage());
        }
    }

    private static void validateInputBonusNumber(String bonusNumber) {
        Integer[] bonusNumberArr = Stream.of(bonusNumber)
                .mapToInt(Integer::parseInt)
                .boxed()
                .toArray(Integer[]::new);
        if (bonusNumberArr[0] < 1 || bonusNumberArr[0] > 45) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_BONUS_NUMBER.getErrorMessage());
        }
    }
}
