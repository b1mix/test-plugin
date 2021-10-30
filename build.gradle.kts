import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    kotlin("jvm") version "1.5.31"
    id("io.papermc.paperweight.userdev") version "1.1.12"
    id("xyz.jpenilla.run-paper") version "1.0.4"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.0"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "com.github.andreypfau"
version = "1.0.0-SNAPSHOT"

dependencies {
    paperDevBundle("1.17.1-R0.1-SNAPSHOT")
}

tasks {
    build {
        dependsOn(reobfJar)
    }
}

bukkit {
    main = "com.github.b1mix.testplugin.TestPlugin"
    apiVersion = "1.17"
}