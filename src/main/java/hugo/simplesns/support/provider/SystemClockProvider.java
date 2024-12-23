package hugo.simplesns.support.provider;

import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
class SystemClockProvider implements ClockProvider {

    @Override
    public Long millis() {
        return Clock.systemUTC().millis();
    }

}
