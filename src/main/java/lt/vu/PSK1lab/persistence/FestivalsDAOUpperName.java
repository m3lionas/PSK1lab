package lt.vu.PSK1lab.persistence;

import lt.vu.PSK1lab.entities.Festival;

import javax.enterprise.inject.Specializes;

@Specializes
public class FestivalsDAOUpperName extends FestivalsDAO{
    @Override
    public Festival update(Festival festival){
        String name = festival.getName();
        festival.setName(name.substring(0, 1).toUpperCase() + name.substring(1));
        return super.update(festival);
    }
}