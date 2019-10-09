package application.clock;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SystemClock implements Clock {
    private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

    @Override
    public String nowHHmm(int minutesAdded) {
        Calendar arrivedTime = Calendar.getInstance();
        arrivedTime.add(Calendar.MINUTE, minutesAdded);
        return formatter.format(arrivedTime.getTime());
    }
}
