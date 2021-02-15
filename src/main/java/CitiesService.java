import java.util.ArrayList;
import java.util.List;


public class CitiesService {

    List<String> citiesList = new ArrayList<String>();

    public void addCity(City city) {
        citiesList.add(city.name);
    }
    public List<String> getCities(){
        for (String nameOfCity:citiesList){
            citiesList.listIterator(0);
        }

        return citiesList;
    }
}
