package dbrito.com.br.countriesapp.di;

import dagger.Component;
import dbrito.com.br.countriesapp.model.CountriesService;
import dbrito.com.br.countriesapp.viewmodel.ListViewModel;

@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(CountriesService service);

    void inject(ListViewModel viewModel);
}
