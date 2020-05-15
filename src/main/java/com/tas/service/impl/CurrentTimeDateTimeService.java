package com.tas.service.impl;

import com.tas.service.DateTimeService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class CurrentTimeDateTimeService implements DateTimeService {
    @Override
    public ZonedDateTime getCurrentDateAndTime() {
        return ZonedDateTime.now();
    }
}
