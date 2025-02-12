package com.creeping_creeper.tinkers_thinking.common.tinkering.modifer.durability;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

import static net.minecraft.tags.DamageTypeTags.BYPASSES_ARMOR;
import static slimeknights.tconstruct.TConstruct.RANDOM;

public record  NetheriteModule(LevelingValue amount) implements ModifierModule, ToolDamageModifierHook, ModifyDamageModifierHook {
    private static final ResourceLocation KEY = new ResourceLocation("tinkers_thinking", "netherite");
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<NetheriteModule> LOADER;

    public RecordLoadable<NetheriteModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
    @Override
    public int onDamageTool(IToolStackView tool, ModifierEntry modifier, int amount, @org.jetbrains.annotations.Nullable LivingEntity holder) {
        if (tool.getPersistentData().getBoolean(KEY)){
            amount = 0;
            tool.getPersistentData().remove(KEY);
        }
        return amount;
    }
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
       float chance = Math.min(amount().eachLevel(),1);
        if (source.is(DamageTypes.ON_FIRE)&&!source.is(BYPASSES_ARMOR)&&tool.hasTag(TinkerTags.Items.WORN_ARMOR)&&RANDOM.nextFloat()<chance){
            tool.getPersistentData().putBoolean(KEY, true);
            amount=0;
        }
        return amount;
    }
    public LevelingValue amount() {
        return this.amount;
    }
    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(ModifierHooks.TOOL_DAMAGE, ModifierHooks.MODIFY_HURT);
        LOADER = RecordLoadable.create(LevelingValue.LOADABLE.directField(NetheriteModule::amount), NetheriteModule::new);
    }
}
