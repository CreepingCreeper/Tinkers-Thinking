package com.creeping_creeper.tinkers_thinking.data;

import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;

public class ModPredicate {
    private ModPredicate() {}
    public static LivingEntityPredicate IS_DAY = LivingEntityPredicate.simple(entity -> entity.level().isDay());
}
