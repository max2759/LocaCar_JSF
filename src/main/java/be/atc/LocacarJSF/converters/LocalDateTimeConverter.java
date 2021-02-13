package be.atc.LocacarJSF.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * Converter pour afficher correctement les dates en format Jour/mois/année
 *
 * @author Max
 */

@FacesConverter(value = "localDateTimeConverter")
public class LocalDateTimeConverter implements Converter {

    private static DateTimeFormatter DATE_FORMAT =
            new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy[ [HH][:mm][:ss][.SSS]]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .toFormatter();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        ZonedDateTime nowLocalDateTime = ZonedDateTime.now();
        return LocalDateTime.parse(value, DATE_FORMAT);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        LocalDateTime dateValue = (LocalDateTime) value;

        return dateValue.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
