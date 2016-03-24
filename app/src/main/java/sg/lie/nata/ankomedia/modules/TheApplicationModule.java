package sg.lie.nata.ankomedia.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TheApplicationModule {
    private final Application application;

    public TheApplicationModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Application application() {
        return application;
    }

    @Singleton
    @Provides
    Context context() {
        return application;
    }
}
