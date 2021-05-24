package lt.vu.PSK1lab.decorators;

import lt.vu.PSK1lab.usecases.IUpdateFestivalDetails;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class UpdateFestivalDetailsDecorator implements IUpdateFestivalDetails{

    @Inject @Delegate @Any
    IUpdateFestivalDetails updateFestivalDetails;

    @Override
    public void checkFestivalName(String festivalName){
        updateFestivalDetails.checkFestivalName(festivalName);
        if (festivalName.matches(".*\\d.*"))
        {
            throw new IllegalArgumentException("Festival name cannot contain numbers");
        }
    }
}