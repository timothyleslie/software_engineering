package com.example.roombasic.database.converters;

import androidx.room.TypeConverter;

import java.math.BigDecimal;
import java.util.Date;

public class Converters {

    //   将timestamp(从1970到现在经历的毫秒数）转化成时间，即timestamp -> date
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    //    date --> timestamp
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    //    将string --> BigDecimal
    @TypeConverter
    public static BigDecimal stringToBig(int intDecimal) {
        return new BigDecimal(intDecimal);
    }

    //    bigDecimal --> integer
    @TypeConverter
    public static int bigToString(BigDecimal bigDecimal) {
        return bigDecimal.intValue();
    }
}
