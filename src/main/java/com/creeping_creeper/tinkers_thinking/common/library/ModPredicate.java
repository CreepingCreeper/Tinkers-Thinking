package com.creeping_creeper.tinkers_thinking.common.library;

import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.mantle.data.predicate.IJsonPredicate;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;

public interface ModPredicate extends LivingEntityPredicate {
    interface Item {

    }
    interface Entity {
        LivingEntityPredicate IS_DAY = isDay();

        LivingEntityPredicate IS_RIDING = LivingEntityPredicate.simple(LivingEntity::isPassenger);

        LivingEntityPredicate IS_HOTBAR_EMPTY = getIsHotbarEmpty();


        private static LivingEntityPredicate isDay() {
            return SingletonLoader.singleton(loader -> new LivingEntityPredicate() {
                @Override
                public boolean matches(@NotNull LivingEntity input) {
                    return input.level().isDay();
                }

                @Override
                public @NotNull RecordLoadable<? extends LivingEntityPredicate> getLoader() {
                    return loader;
                }
            });
        }

        private static LivingEntityPredicate getIsHotbarEmpty(){
            return SingletonLoader.singleton(loader -> new LivingEntityPredicate() {

                @Override
                public boolean matches(@NotNull LivingEntity input) {
                    int x = 0;
                    for (int i=0;i<9;i++){
                        if (!isEmpty(input, i)) {
                            x++;
                        }
                    }
                    return x < 2;
                }

                @Override
                public @NotNull RecordLoadable<? extends IJsonPredicate<LivingEntity>> getLoader() {
                    return loader;
                }
            });
        }

        private static boolean isEmpty(LivingEntity living, int stack){
            return living.getSlot(stack).get().isEmpty();
        }

    }
}
