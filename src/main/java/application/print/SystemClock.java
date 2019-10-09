package application.print;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SystemClock implements Clockable {
    private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

    @Override
    public String nowHHmm(int minutesAdded) {
        Calendar arrivedTime = Calendar.getInstance();
        arrivedTime.add(Calendar.MINUTE, minutesAdded);
        return formatter.format(arrivedTime.getTime());
    }
}
