package sg.lie.nata.ankomedia;

import javax.inject.Singleton;

import dagger.Component;
import sg.lie.nata.ankomedia.managers.SharedPreferencesManager;
import sg.lie.nata.ankomedia.modules.PreferencesModule;
import sg.lie.nata.ankomedia.modules.TheApplicationModule;

@Singleton
@Component(modules = {
        TheApplicationModule.class,
        PreferencesModule.class
})

public interface ApplicationComponent {
    void inject(SharedPreferencesManager sharedPreferencesManager);
}
