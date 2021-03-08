package dbrito.com.br.countriesapp.model;

import com.google.gson.annotations.SerializedName;

public class CountryModel {
    @SerializedName("name") //Use to bind with the API without change the name of the object.
    String countryName;

    @SerializedName("capital")
    String capital;

    @SerializedName("flagPNG")
    String flag;

    public CountryModel(String countryName, String capital, String flag) {
        this.countryName = countryName;
        this.capital = capital;
        this.flag = flag;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCapital() {
        return capital;
    }

    public String getFlag() {
        return flag;
    }


}
