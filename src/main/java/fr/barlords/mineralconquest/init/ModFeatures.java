package fr.barlords.mineralconquest.init;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModFeatures {

    public ConfiguredFeature<?,?> ORE_TITANE_FEATURE;
    public ConfiguredFeature<?,?> ORE_TERRASTEEL_FEATURE;
    public ConfiguredFeature<?,?> ORE_BARLORITE_FEATURE;
    public ConfiguredFeature<?,?> ORE_CELESTIUM_FEATURE;

    public void init() {
        ORE_TITANE_FEATURE = register("ore_titane", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.TITANE_ORE.get().defaultBlockState(), 3))
            .squared()
            .decorated((Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(14, 4))))
            .count(3));

        ORE_TERRASTEEL_FEATURE = register("ore_terrasteel", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.TERRASTEEL_ORE.get().defaultBlockState(), 3))
            .squared()
            .decorated((Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(14, 4))))
            .count(2));

        ORE_BARLORITE_FEATURE = register("ore_barlorite", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.BARLORITE_ORE.get().defaultBlockState(), 3))
            .squared()
            .decorated((Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(14, 4))))
            .count(2));

        ORE_CELESTIUM_FEATURE = register("ore_celestium", Feature.ORE.configured(new OreFeatureConfig(ModOreFeatureConfig.FillerBlockType.END, ModBlocks.CELESTIUM_ORE.get().defaultBlockState(), 3))
            .squared()
            .range(128)
            .count(10));

    }

    public <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, name, feature);
    }

    @SubscribeEvent
    public void biomeLoading(BiomeLoadingEvent e) {
        BiomeGenerationSettingsBuilder generation = e.getGeneration();
        if(e.getCategory() != Biome.Category.THEEND && e.getCategory() != Biome.Category.NETHER) {
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ORE_TITANE_FEATURE);
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ORE_TERRASTEEL_FEATURE);
        }
        if(e.getCategory() == Biome.Category.NETHER) {
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ORE_BARLORITE_FEATURE);
        }
        if(e.getCategory() == Biome.Category.THEEND) {
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ORE_CELESTIUM_FEATURE);
        }
    }
}
