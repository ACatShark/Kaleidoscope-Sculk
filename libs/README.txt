前置模组依赖说明
================

本模组需要两个前置模组（均为 Minecraft 1.21.1 / NeoForge）：

  1. Kaleidoscope Tavern   modid: kaleidoscope_tavern
  2. Kaleidoscope Cookery  modid: kaleidoscope_cookery

这两个模组未在稳定的 Maven 仓库中提供，需手动下载 jar 放到本目录（libs/）：

  - Kaleidoscope Cookery  ->  https://modrinth.com/mod/kaleidoscope-cookery
  - Kaleidoscope Tavern   ->  https://modrinth.com/mod/kaleidoscopetavern

build.gradle 通过文件名通配自动引用这两个 jar：

  compileOnly / localRuntime  ->  kaleidoscopecookery-*.jar
                                  kaleidoscopetavern-*.jar

升级版本时，直接替换本目录里对应的 jar（保持文件名前缀不变即可），
无需改动 build.gradle。目录里的其他 jar 不再参与编译，可保留或清理。

依赖的关键类（Kaleidoscope Tavern）：
  com.github.ysbbbbbb.kaleidoscopetavern.block.brew.DrinkBlock           (block/SculkDrinkBlock.java)
  com.github.ysbbbbbb.kaleidoscopetavern.blockentity.brew.DrinkBlockEntity
  com.github.ysbbbbbb.kaleidoscopetavern.datamap.data.DrinkEffectData
  com.github.ysbbbbbb.kaleidoscopetavern.datamap.resources.DrinkEffectDataReloadListener
  com.github.ysbbbbbb.kaleidoscopetavern.item.DrinkBlockItem / BottleBlockItem / IHasContainer
  com.github.ysbbbbbb.kaleidoscopetavern.fluid.JuiceFluidType            (register/ModFluids.java)
  com.github.ysbbbbbb.kaleidoscopetavern.init.ModBlocks

依赖的关键内容（Kaleidoscope Cookery）：
  resources 里的 chopping_board / pot / stockpot / millstone / pressing_tub
  等配方类型与物品标签，以及 warmth / mustard / vigor 等效果。

编译：
  gradlew build

产物位于 build/libs/。
