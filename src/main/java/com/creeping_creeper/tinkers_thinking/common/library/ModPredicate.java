package com.creeping_creeper.tinkers_thinking.common.library;

import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;

public interface ModPredicate extends LivingEntityPredicate {
    interface Item {

    }
    interface Entity {
        LivingEntityPredicate IS_DAY = isDay();

        LivingEntityPredicate IS_RIDING = LivingEntityPredicate.simple(LivingEntity::isPassenger);

        private static LivingEntityPredicate isDay() {
            return SingletonLoader.singleton(loader -> new LivingEntityPredicate() {
                @Override
                public boolean matches(@NotNull LivingEntity entity) {
                    return entity.level().isDay();
                }

                @Override
                public @NotNull RecordLoadable<? extends LivingEntityPredicate> getLoader() {
                    return loader;
                }
            });
        }
    }
}
