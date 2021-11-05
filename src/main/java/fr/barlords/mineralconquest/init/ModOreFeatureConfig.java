package fr.barlords.mineralconquest.init;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;

public class ModOreFeatureConfig implements IFeatureConfig {
    public static final Codec<ModOreFeatureConfig> CODEC = RecordCodecBuilder.create((p_236568_0_) -> {
        return p_236568_0_.group(RuleTest.CODEC.fieldOf("target").forGetter((config) -> {
            return config.target;
        }), BlockState.CODEC.fieldOf("state").forGetter((config) -> {
            return config.state;
        }), Codec.intRange(0, 64).fieldOf("size").forGetter((config) -> {
            return config.size;
        })).apply(p_236568_0_, ModOreFeatureConfig::new);
    });
    public final RuleTest target;
    public final int size;
    public final BlockState state;

    public ModOreFeatureConfig(RuleTest p_i241989_1_, BlockState state, int size) {
        this.size = size;
        this.state = state;
        this.target = p_i241989_1_;
    }

    //on supprime le contenu ci dessous, pour le remplacer par celui qu'on veut, avec RuleTest NOM_DE_LA_REGLE et TagMatchRuleTest(Definition.NOMDELADEFINITION)
    public static final class FillerBlockType {
        public static final RuleTest END = new TagMatchRuleTest(BlockTags.DRAGON_IMMUNE);
        public static final RuleTest AIR = new BlockMatchRuleTest(Blocks.AIR);
    }
}