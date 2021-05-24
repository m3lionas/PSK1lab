package lt.vu.PSK1lab.usecases;

public interface IUpdateFestivalDetails {
    String updateFestivalName() throws InterruptedException;
    void checkFestivalName(String festivalName);
}