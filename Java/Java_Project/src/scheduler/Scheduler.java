package scheduler;

public abstract class Scheduler {
    private static final int HOUR_ROWS = 24;
    private static final int DAYS_COLUMNS = 8;
    private static final String TIMES[] = {
            "0시~1시", "1시~2시", "2시~3시", "3시~4시", "4시~5시",
            "5시~6시", "6시~7시", "7시~8시", "8시~9시", "9시~10시",
            "10시~11시", "11시~12시", "12시~13시", "13시~14시", "14시~15시",
            "15시~16시", "16시~17시", "17시~18시", "18시~19시", "19시~20시",
            "20시~21시", "21시~22시", "22시~23시", "23시~24시"
    };
    private static final String TIMES_TOKENS[] = {
            "0시", "1시", "2시", "3시", "4시", "5시",
            "6시", "7시", "8시", "9시", "10시", "11시",
            "12시", "13시", "14시", "15시", "16시", "17시",
            "18시", "19시", "20시", "21시", "22시", "23시", "24시"
    };

    private static final String DAYS[] = {
            "", "월", "화", "수", "목", "금", "토", "일"
    };
    private static final String ADD_SCHEDULE = "일정 추가";
    private static final String SEARCH_SCHEDULE = "일정 검색";
    private static final String ADJUST_SCHEDULE = "일정 수정";
    private static final String DELETE_SCHEDULE = "일정 삭제";
    private static final String NOW_TIME = "현재 시간 : ";
    private static final String EXIT = "종료";
    private String schedule[][] = new String[24][5];

    public Scheduler(){}

    public abstract void addSchedule();
    public abstract void searchSchedule();
    public abstract void adjustSchedule();
    public abstract void deleteSchedule();
    public abstract void exitScheduler();
    public abstract void currentTimeUpdate();

    public static String[] getTimesTokens() {
        return TIMES_TOKENS;
    }
    public static String getNowTime() {
        return NOW_TIME;
    }
    public static String[] getTimes() {
        return TIMES;
    }
    public static String[] getDays() {
        return DAYS;
    }
    public static int getDaysColumns() {
        return DAYS_COLUMNS;
    }
    public static int getHourRows() {
        return HOUR_ROWS;
    }
    public static String getAddSchedule() {
        return ADD_SCHEDULE;
    }
    public static String getSearchSchedule() {
        return SEARCH_SCHEDULE;
    }
    public static String getDeleteSchedule() {
        return DELETE_SCHEDULE;
    }
    public static String getAdjustSchedule() {
        return ADJUST_SCHEDULE;
    }
    public static String getExit() { return EXIT; }
    public String[][] getSchedule() {
        return schedule;
    }
    public void setSchedule(String[][] schedule) {
        this.schedule = schedule;
    }
}