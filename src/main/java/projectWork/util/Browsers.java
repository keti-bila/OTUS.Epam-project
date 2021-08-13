package projectWork.util;

public enum Browsers {
    CHROME,
    FIREFOX,
    OPERA;

    public static Browsers getBrowserByString(String browser) {
        return Browsers.valueOf(browser.toUpperCase());
    }
}
