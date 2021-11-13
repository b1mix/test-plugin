package com.github.b1mix.testplugin

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.DyeColor
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.Sheep
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDeathEvent
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
   fun spawn(event: BlockBreakEvent) {

       event.block.world.spawnEntity(event.block.location, EntityType.SHEEP)
   }
    @EventHandler
    fun color(event: EntityDamageByEntityEvent) {

        if (event.damager is Player && event.entity is Sheep) {
            val colorSheep = event.entity as Sheep
            colorSheep.color = DyeColor.values().random()
        }
    }
    @EventHandler
    fun zombie(event: EntityDeathEvent) {
        if (event.entity.killer is Player && event.entity is Sheep) {
            event.entity.world.spawnEntity(event.entity.location, EntityType.ZOMBIE)
        }

    }
}