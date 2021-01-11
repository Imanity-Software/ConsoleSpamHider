package org.imanity.console;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LifeCycle;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;

import java.util.List;

public class ModernLogFilter implements Filter {

    private final List<String> filterMessages;

    public ModernLogFilter(List<String> filterMessages) {
        this.filterMessages = filterMessages;
    }

    public Filter.Result findFilters(String input) {
        if (!this.filterMessages.isEmpty()) {
            if (this.filterMessages.stream().anyMatch(input::contains)) {
                return Result.DENY;
            }
        }

        return Result.NEUTRAL;
    }

    @Override
    public Filter.Result getOnMatch() {
        return Filter.Result.NEUTRAL;
    }

    @Override
    public Filter.Result getOnMismatch() {
        return Filter.Result.NEUTRAL;
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
        return this.findFilters(msg);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String message, Object p0) {
        return this.findFilters(message);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1) {
        return this.findFilters(message);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2) {
        return this.findFilters(message);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
        return this.findFilters(message);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        return this.findFilters(message);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        return this.findFilters(message);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        return this.findFilters(message);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        return this.findFilters(message);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        return this.findFilters(message);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        return this.findFilters(message);
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

    @Override
    public State getState() {
        try {
            return LifeCycle.State.STARTED;
        }
        catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void initialize() {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isStarted() {
        return true;
    }

    @Override
    public boolean isStopped() {
        return false;
    }
}
