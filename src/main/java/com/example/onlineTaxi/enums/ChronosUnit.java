package com.example.onlineTaxi.enums;


//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import lombok.RequiredArgsConstructor;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.time.Duration;

@RequiredArgsConstructor
public enum ChronosUnit implements TemporalUnit {
    NANOS("Nanos", Duration.ofNanos(1L)),
    MICROS("Micros", Duration.ofNanos(1000L)),
    MILLIS("Millis", Duration.ofNanos(1000000L)),
    SECONDS("Seconds", Duration.ofSeconds(1L)),
    MINUTES("Minutes", Duration.ofSeconds(60L)),
    HOURS("Hours", Duration.ofSeconds(3600L)),
    HALF_DAYS("HalfDays", Duration.ofSeconds(43200L)),
    DAYS("Days", Duration.ofSeconds(86400L)),
    WEEKS("Weeks", Duration.ofSeconds(604800L)),
    MONTHS("Months", Duration.ofSeconds(2629746L)),
    YEARS("Years", Duration.ofSeconds(31556952L)),
    DECADES("Decades", Duration.ofSeconds(315569520L)),
    CENTURIES("Centuries", Duration.ofSeconds(3155695200L)),
    MILLENNIA("Millennia", Duration.ofSeconds(31556952000L)),
    ERAS("Eras", Duration.ofSeconds(31556952000000000L)),
    FOREVER("Forever", Duration.ofSeconds(Long.MAX_VALUE, 999999999L));

    private final String name;
    private final Duration duration;

//    private ChronoUnit(String name, Duration estimatedDuration) {
//        this.name = name;
//        this.duration = estimatedDuration;
//    }

    public Duration getDuration() {
        return this.duration;
    }

    public boolean isDurationEstimated() {
        return this.compareTo(DAYS) >= 0;
    }

    public boolean isDateBased() {
        return this.compareTo(DAYS) >= 0 && this != FOREVER;
    }

    public boolean isTimeBased() {
        return this.compareTo(DAYS) < 0;
    }

    public boolean isSupportedBy(Temporal temporal) {
        return temporal.isSupported(this);
    }

    public <R extends Temporal> R addTo(R temporal, long amount) {
        return (R) temporal.plus(amount, this);
    }

    public long between(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return temporal1Inclusive.until(temporal2Exclusive, this);
    }

    public String toString() {
        return this.name;
    }
}

