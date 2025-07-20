package net.rose.rosalib.tests.init;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import net.rose.rosalib.common.Rosalib;

public class ModEntityComponents implements EntityComponentInitializer {
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
    }

    // region BackEnd
    private static <C extends Component> ComponentKey<C> of(String componentId, Class<C> componentClass) {
        return ComponentRegistry.getOrCreate(Rosalib.id(componentId), componentClass);
    }
    // endregion
}