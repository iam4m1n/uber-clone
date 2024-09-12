package com.example.onlineTaxi.enums;



//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.time.Duration;
import java.util.Objects;

public enum TimeUnit {
    NANOSECONDS(1L),
    MICROSECONDS(1000L),
    MILLISECONDS(1000000L),
    SECONDS(1000000000L),
    MINUTES(60000000000L),
    HOURS(3600000000000L),
    DAYS(86400000000000L);

    private static final long NANO_SCALE = 1L;
    private static final long MICRO_SCALE = 1000L;
    private static final long MILLI_SCALE = 1000000L;
    private static final long SECOND_SCALE = 1000000000L;
    private static final long MINUTE_SCALE = 60000000000L;
    private static final long HOUR_SCALE = 3600000000000L;
    private static final long DAY_SCALE = 86400000000000L;
    private final long scale;
    private final long maxNanos;
    private final long maxMicros;
    private final long maxMillis;
    private final long maxSecs;
    private final long microRatio;
    private final int milliRatio;
    private final int secRatio;

    private TimeUnit(long s) {
        this.scale = s;
        this.maxNanos = Long.MAX_VALUE / s;
        long ur = s >= 1000L ? s / 1000L : 1000L / s;
        this.microRatio = ur;
        this.maxMicros = Long.MAX_VALUE / ur;
        long mr = s >= 1000000L ? s / 1000000L : 1000000L / s;
        this.milliRatio = (int)mr;
        this.maxMillis = Long.MAX_VALUE / mr;
        long sr = s >= 1000000000L ? s / 1000000000L : 1000000000L / s;
        this.secRatio = (int)sr;
        this.maxSecs = Long.MAX_VALUE / sr;
    }

    private static long cvt(long d, long dst, long src) {
        if (src == dst) {
            return d;
        } else if (src < dst) {
            return d / (dst / src);
        } else {
            long r;
            long m;
            if (d > (m = Long.MAX_VALUE / (r = src / dst))) {
                return Long.MAX_VALUE;
            } else {
                return d < -m ? Long.MIN_VALUE : d * r;
            }
        }
    }

    public long convert(long sourceDuration, TimeUnit sourceUnit) {
        switch (this.ordinal()) {
            case 0:
                return sourceUnit.toNanos(sourceDuration);
            case 1:
                return sourceUnit.toMicros(sourceDuration);
            case 2:
                return sourceUnit.toMillis(sourceDuration);
            case 3:
                return sourceUnit.toSeconds(sourceDuration);
            default:
                return cvt(sourceDuration, this.scale, sourceUnit.scale);
        }
    }

    public long convert(Duration duration) {
        long secs = duration.getSeconds();
        int nano = duration.getNano();
        if (secs < 0L && nano > 0) {
            ++secs;
            nano -= 1000000000;
        }

        long nanoVal;
        if (this == NANOSECONDS) {
            nanoVal = (long)nano;
        } else {
            long s;
            if ((s = this.scale) >= 1000000000L) {
                if (this == SECONDS) {
                    return secs;
                }

                return secs / (long)this.secRatio;
            }

            nanoVal = (long)nano / s;
        }

        long val = secs * (long)this.secRatio + nanoVal;
        return (secs >= this.maxSecs || secs <= -this.maxSecs) && (secs != this.maxSecs || val <= 0L) && (secs != -this.maxSecs || val >= 0L) ? (secs > 0L ? Long.MAX_VALUE : Long.MIN_VALUE) : val;
    }

    public long toNanos(long duration) {
        long s;
        if ((s = this.scale) == 1L) {
            return duration;
        } else {
            long m;
            if (duration > (m = this.maxNanos)) {
                return Long.MAX_VALUE;
            } else {
                return duration < -m ? Long.MIN_VALUE : duration * s;
            }
        }
    }

    public long toMicros(long duration) {
        long s;
        if ((s = this.scale) <= 1000L) {
            return s == 1000L ? duration : duration / this.microRatio;
        } else {
            long m;
            if (duration > (m = this.maxMicros)) {
                return Long.MAX_VALUE;
            } else {
                return duration < -m ? Long.MIN_VALUE : duration * this.microRatio;
            }
        }
    }

    public long toMillis(long duration) {
        long s;
        if ((s = this.scale) <= 1000000L) {
            return s == 1000000L ? duration : duration / (long)this.milliRatio;
        } else {
            long m;
            if (duration > (m = this.maxMillis)) {
                return Long.MAX_VALUE;
            } else {
                return duration < -m ? Long.MIN_VALUE : duration * (long)this.milliRatio;
            }
        }
    }

    public long toSeconds(long duration) {
        long s;
        if ((s = this.scale) <= 1000000000L) {
            return s == 1000000000L ? duration : duration / (long)this.secRatio;
        } else {
            long m;
            if (duration > (m = this.maxSecs)) {
                return Long.MAX_VALUE;
            } else {
                return duration < -m ? Long.MIN_VALUE : duration * (long)this.secRatio;
            }
        }
    }

    public long toMinutes(long duration) {
        return cvt(duration, 60000000000L, this.scale);
    }

    public long toHours(long duration) {
        return cvt(duration, 3600000000000L, this.scale);
    }

    public long toDays(long duration) {
        return cvt(duration, 86400000000000L, this.scale);
    }

    private int excessNanos(long d, long m) {
        long s;
        if ((s = this.scale) == 1L) {
            return (int)(d - m * 1000000L);
        } else {
            return s == 1000L ? (int)(d * 1000L - m * 1000000L) : 0;
        }
    }

    public void timedWait(Object obj, long timeout) throws InterruptedException {
        if (timeout > 0L) {
            long ms = this.toMillis(timeout);
            int ns = this.excessNanos(timeout, ms);
            obj.wait(ms, ns);
        }

    }

    public void timedJoin(Thread thread, long timeout) throws InterruptedException {
        if (timeout > 0L) {
            long ms = this.toMillis(timeout);
            int ns = this.excessNanos(timeout, ms);
            thread.join(ms, ns);
        }

    }

    public void sleep(long timeout) throws InterruptedException {
        if (timeout > 0L) {
            long ms = this.toMillis(timeout);
            int ns = this.excessNanos(timeout, ms);
            Thread.sleep(ms, ns);
        }

    }

    public ChronosUnit toChronoUnit() {
        switch (this.ordinal()) {
            case 0:
                return ChronosUnit.NANOS;
            case 1:
                return ChronosUnit.MICROS;
            case 2:
                return ChronosUnit.MILLIS;
            case 3:
                return ChronosUnit.SECONDS;
            case 4:
                return ChronosUnit.MINUTES;
            case 5:
                return ChronosUnit.HOURS;
            case 6:
                return ChronosUnit.DAYS;
            default:
                throw new AssertionError();
        }
    }

    public static TimeUnit of(ChronosUnit chronoUnit) {
        switch ((ChronosUnit)Objects.requireNonNull(chronoUnit, "chronoUnit")) {
            case NANOS:
                return NANOSECONDS;
            case MICROS:
                return MICROSECONDS;
            case MILLIS:
                return MILLISECONDS;
            case SECONDS:
                return SECONDS;
            case MINUTES:
                return MINUTES;
            case HOURS:
                return HOURS;
            case DAYS:
                return DAYS;
            default:
                throw new IllegalArgumentException("No TimeUnit equivalent for " + chronoUnit);
        }
    }
}

