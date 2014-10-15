package org.kesler.cert.util;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.control.ListView;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Универсальные вспомогательные функции для работы с JavaFX
 */
public abstract class FXUtils {

    public static <T> void triggerUpdateListView(ListView<T> listView, T newValue, int i) {
        EventType<? extends ListView.EditEvent<T>> type = ListView.<T>editCommitEvent();
        Event event = new ListView.EditEvent<T>(listView, type, newValue, i);
        listView.fireEvent(event);
    }

    public static Date localDateToDate(LocalDate localDate) {
        return localDate==null?null:Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate dateToLocalDate(Date date) {
        return date==null?null:date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
