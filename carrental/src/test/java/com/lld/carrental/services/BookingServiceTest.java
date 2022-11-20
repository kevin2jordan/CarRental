package com.lld.carrental.services;

import com.lld.carrental.exceptions.DoesNotExistException;
import com.lld.carrental.services.booking.BookingService;
import com.lld.carrental.services.booking.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookingServiceTest {

    BookingService bookingService;
    @BeforeEach
    public void setup() {
        bookingService = new BookingServiceImpl();
    }

    @Test
    public void bookVehicle() throws DoesNotExistException {

        assertThrows(DoesNotExistException.class,
                ()->bookingService.bookVehicle("test","SUV",123,345));
    }
}
