package com.creeping_creeper.tinkers_thinking.common.things.block;

import com.creeping_creeper.tinkers_thinking.common.register.ModCommonItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ChlorophyllOreBlock extends Block {
    public ChlorophyllOreBlock(Properties properties) {
        super(properties);
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        {
            if (!level.isAreaLoaded(pos, 3)) {
                return;
            }
            if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
                BlockState blockstate = ModCommonItems.mud_chlorophyll_ore.get().defaultBlockState();
                for(int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (level.getBlockState(blockpos).is(Blocks.MUD)) {
                        level.setBlockAndUpdate(blockpos, blockstate);
                    }
                }
            }
        }

    }
}