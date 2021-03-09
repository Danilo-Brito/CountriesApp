package dbrito.com.br.countriesapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dbrito.com.br.countriesapp.model.CountriesService;
import dbrito.com.br.countriesapp.model.CountryModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {
    public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    //LiveData is objects observable that generates async values

    private CountriesService countriesService = CountriesService.getInstance();

    private CompositeDisposable disposable = new CompositeDisposable();

    public void refresh(){
        fetchCountries();
    }

    private void fetchCountries(){
        loading.setValue(true);
        disposable.add(
                countriesService.getCountries() // Create connection with API
                .subscribeOn(Schedulers.newThread()) //communication with API in new thread
                .observeOn(AndroidSchedulers.mainThread()) // enable to use the response on the new Thread
                .subscribeWith(new DisposableSingleObserver<List<CountryModel>>(){

                    @Override
                    public void onSuccess(@NonNull List<CountryModel> countryModels) {
                        countries.setValue(countryModels);
                        countryLoadError.setValue(false);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        countryLoadError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();
                    }
                })
        );
    }

    @Override
    protected void onCleared(){
        super.onCleared();
        disposable.clear();
    }
}
