package com.mr.homework;


import com.mr.listener.homework.HistoryListener;
import com.mr.model.Message;
import com.mr.model.ObjectForMessage;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class HistoryListenerTest {

    @Test
    void listenerTest() {
        //given
        var historyListener = new HistoryListener();

        var id = 100L;
        var data = "33";
        var field13 = new ObjectForMessage();
        var field13Data = new ArrayList<String>();
        field13Data.add(data);
        field13.setData(field13Data);

        var message = new Message.Builder(id)
                .field10("field10")
                .field13(field13)
                .build();

        //when
        historyListener.onUpdated(message);
        message.getField13().setData(new ArrayList<>());
        field13Data.clear();
        message.toBuilder()
                .field10("test message")
                .build();

        //then
        var messageFromHistory = historyListener.findMessageById(id);
        AssertionsForClassTypes.assertThat(messageFromHistory).isPresent();

        Message expectedMessage = messageFromHistory.get();
        ObjectForMessage expectedObjectForMessage = expectedMessage.getField13();

        assertThat(expectedObjectForMessage.getData()).containsExactly(data);
        assertThat(expectedMessage.getField10()).isEqualTo("field10");
    }
}