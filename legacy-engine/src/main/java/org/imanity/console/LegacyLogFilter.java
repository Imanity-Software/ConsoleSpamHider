package org.imanity.console;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;

import java.util.List;

public class LegacyLogFilter implements Filter {

    private final List<String> filterMessages;

    public LegacyLogFilter(List<String> filterMessages) {
        this.filterMessages = filterMessages;
    }

    public Filter.Result findFilters(String input) {
        if (!this.filterMessages.isEmpty()) {
            if (this.filterMessages.stream()
                    .anyMatch(filter -> filter.contains(input))) {
                return Result.DENY;
            }
        }

        return Result.NEUTRAL;
    }

    @Override
    public Result getOnMismatch() {
        return Result.NEUTRAL;
    }

    @Override
    public Result getOnMatch() {
        return Result.NEUTRAL;
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
        return this.findFilters(msg);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t) {
        return this.findFilters(msg.toString());
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t) {
        return this.findFilters(msg.getFormattedMessage());
    }

    @Override
    public Result filter(LogEvent event) {
        return this.findFilters(event.getMessage().getFormattedMessage());
    }
}
