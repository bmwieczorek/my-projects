package enums;

import static enums.CelebrationDayEnumImplementingCelebrateIterface.BIRTHDAY;
import static enums.CelebrationDayEnumImplementingCelebrateIterface.NAMEDAY;

public class CelebrationExample {
    public static void main(String[] args) {
        BIRTHDAY.celebrate();
        NAMEDAY.celebrate();
    }
}
