package com.tas.converter;

import com.tas.service.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Component("auditingDateTimeProvider")
public class AuditingDateTimeProvider implements DateTimeProvider {

    @Autowired
    private DateTimeService dateTimeService;

    @Override
    public Calendar getNow() {
        return GregorianCalendar.from(dateTimeService.getCurrentDateAndTime());
    }
}
