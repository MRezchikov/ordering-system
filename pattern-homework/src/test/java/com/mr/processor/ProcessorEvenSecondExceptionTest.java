package com.mr.processor;

import com.mr.model.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProcessorEvenSecondExceptionTest {

    @DisplayName("Должен выбросить исключение в четную секунду")
    @Test
    void processShouldThrowExceptionIfEvenSecond() {

        //given
        Message message = new Message.Builder(1L).build();

        //when
        ProcessorEvenSecondException processor = new ProcessorEvenSecondException(()
                -> LocalDateTime.of(2022, Month.FEBRUARY, 8, 8, 0, 18));

        //then
        assertThrows(EvenSecondException.class, () -> processor.process(message));
    }

    @DisplayName("Возращает message без выбрасывания исключеиня в нечетную секунду")
    @Test
    void processShouldReturnMessageWithoutException() {

        //given
        Message actual = new Message.Builder(1L).build();

        //when
        ProcessorEvenSecondException processor = new ProcessorEvenSecondException(()
                -> LocalDateTime.of(2022, Month.FEBRUARY, 8, 8, 0, 19));


        Message expected = processor.process(actual);

        //then
        assertThat(actual).isEqualTo(expected);
        assertDoesNotThrow(() -> processor.process(actual));
    }

}