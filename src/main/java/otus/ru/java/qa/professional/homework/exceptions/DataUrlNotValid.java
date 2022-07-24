package otus.ru.java.qa.professional.homework.exceptions;

public class DataUrlNotValid extends Exception{

    public DataUrlNotValid(String... data) {
        super(String.format("Data {%s} in url not valid", String.join(", ", data)));
    }
}
