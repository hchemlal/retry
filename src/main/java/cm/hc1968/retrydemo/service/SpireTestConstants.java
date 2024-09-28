package cm.hc1968.retrydemo.service;

public interface SpireTestConstants {
    String PASSED_AT_FIRST_TRY = "Passed at 1 try";
    String PASSED_AT_SECOND_TRY = "Passed after 2 retry";
    String RECOVERED_FROM_RETRIABLE_EXCEPTIONS = "I have recovered from postToSpire after retriable exceptions";
    String RECOVERED_FROM_NON_RETRIABLE_EXCEPTIONS = "I have recovered from postToSpire after non retriable exceptions";
}
