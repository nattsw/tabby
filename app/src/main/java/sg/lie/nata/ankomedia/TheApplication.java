package sg.lie.nata.ankomedia;

import android.app.Application;

import sg.lie.nata.ankomedia.modules.PreferencesModule;
import sg.lie.nata.ankomedia.modules.TheApplicationModule;

public class TheApplication extends Application {
    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .theApplicationModule(new TheApplicationModule(this))
                .preferencesModule(new PreferencesModule())
                .build();
    }

    public ApplicationComponent component() {
        return applicationComponent;
    }
}
