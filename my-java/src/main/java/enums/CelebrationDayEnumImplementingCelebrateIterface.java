package enums;

public enum CelebrationDayEnumImplementingCelebrateIterface implements MyCelebrateInterface {

    BIRTHDAY {
        @Override
        public void celebrate() {
            System.out.println("It's birthday time!");
        }
    },
    NAMEDAY {
        @Override
        public void celebrate() {
            System.out.println("It's nameday time!");
        }
    };

}
