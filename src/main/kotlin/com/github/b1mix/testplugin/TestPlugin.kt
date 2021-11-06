package com.github.b1mix.testplugin

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerItemBreakEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class TestPlugin : JavaPlugin(), Listener {
    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
        println("Плагин запущен")
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {

        val textComponent : net.kyori.adventure.text.TextComponent = Component.text("Привет", NamedTextColor.GREEN)

        event.player.sendMessage(textComponent)
    }
    @EventHandler
    fun Move(event: PlayerMoveEvent) {

        event.player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 20*20, 30))

        val textComponent1 : net.kyori.adventure.text.TextComponent = Component.text("Запущено", NamedTextColor.BLUE)

        event.player.sendMessage(textComponent1)
    }
    @EventHandler
    fun Jump(event: PlayerJumpEvent) {

        event.player.addPotionEffect(PotionEffect(PotionEffectType.JUMP, 20*20, 30))

        val textComponent2 : net.kyori.adventure.text.TextComponent = Component.text("Запущено", NamedTextColor.RED)

        event.player.sendMessage(textComponent2)
    }
    @EventHandler
    fun destroy(event: BlockBreakEvent) {

        event.player.addPotionEffect(PotionEffect(PotionEffectType.FAST_DIGGING, 20*20, 220))

        val textComponent3 : net.kyori.adventure.text.TextComponent = Component.text("Привет", NamedTextColor.LIGHT_PURPLE)

        event.player.sendMessage(textComponent3)
    }
}