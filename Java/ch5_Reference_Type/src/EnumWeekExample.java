import java.util.Calendar;

public class EnumWeekExample {
    public static void main(String[] args) {
        Week today = null;

        Calendar cal = Calendar.getInstance();
        int week = cal.get(Calendar.DAY_OF_WEEK);

        switch (week){
            case 1:
                today = Week.SUNDAY; break;
            case 2:
                today = Week.MONDAY; break;
            case 3:
                today = Week.TUESDAY; break;
            case 4:
                today = Week.WEDNESDAY; break;
            case 5:
                today = Week.THURSDAY; break;
            case 6:
                today = Week.FRIDAY; break;
            case 7:
                today = Week.SUNDAY; break;
        }

        System.out.println("오늘 요일 " + today);

        if(today == Week.SUNDAY){
            System.out.println("일요일에는 축구를 합니다.");
        } else {
            System.out.println("열심히 자바 공부합니다.");
        }

        int year = cal.get(Calendar.YEAR);			// 년
        int month = cal.get(Calendar.MONTH) + 1;	// 월(1~12)
        int day = cal.get(Calendar.DAY_OF_MONTH);	// 일
        int hour = cal.get(Calendar.HOUR);			// 시간
        int minute = cal.get(Calendar.MINUTE);		// 분
        int second = cal.get(Calendar.SECOND);		// 초

        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);


    }
}
