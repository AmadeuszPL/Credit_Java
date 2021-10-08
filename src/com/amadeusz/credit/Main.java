package com.amadeusz.credit;

import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;


public class Main {

    public static void main(String[] args) {

        String cardNumber = getAndPreValidateCardNumber();
        verifyUsingLuhnAlgorithm(cardNumber);
        System.out.println(CardType.detect(cardNumber));

    }

    private static void verifyUsingLuhnAlgorithm(String cardNumber) {

        int verifySum = 0, secondNumberFromEnd = 0, miniSum = 0;
        Long cardNumberLong = Long.parseLong(cardNumber);
        for (int i = 0; i < cardNumber.length(); i += 2) {
            secondNumberFromEnd = (int) ((cardNumberLong % 100 - cardNumberLong % 10) / 10);
            miniSum = (secondNumberFromEnd * 2) / 10 + (secondNumberFromEnd * 2) % 10;
            verifySum += miniSum + (int) (cardNumberLong % 10);
            cardNumberLong = (cardNumberLong - cardNumberLong % 100) / 100;
        }
        if (verifySum % 10 != 0) {
            System.out.println("This is not Credit Card number.");
            System.exit(1);
        }
    }

    private static String getAndPreValidateCardNumber() {

        Scanner getCardNumber = new Scanner(System.in);
        boolean validateInput = false;
        String cardNumber = null;

        do {
            System.out.println("Enter card number:");
            cardNumber = getCardNumber.nextLine();
            if (cardNumber.length() < 13 || cardNumber.length() > 16 || !NumberUtils.isParsable(cardNumber)) {
                System.out.println("Not valid length.");
            } else {
                validateInput = true;
            }
        } while (!validateInput);

        return cardNumber;
    }

}