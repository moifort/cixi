package util;

import application.clock.Clock;

public class MockClock implements Clock {
    private String time;

    public MockClock(String time) {
        this.time = time;
    }

    @Override
    public String nowHHmm(int minutesAdded) {
        return time;
    }
}