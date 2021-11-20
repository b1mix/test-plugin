package com.github.b1mix.testplugin

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.DyeColor
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.Sheep
import org.bukkit.entity.Zombie
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
        var timer = 5
        val killer = event.entity.killer
        if (killer is Player && event.entity is Sheep) {
            server.scheduler.runTaskTimer(this, Runnable {
                event.entity.world.spawnEntity(event.entity.location, EntityType.ZOMBIE)
                killer.sendMessage(Component.text(timer))
            }, 0, 20 * 5)

        }

    }

    @EventHandler
    fun nick(event: EntityDamageByEntityEvent) {

        if (event.damager is Player && event.entity is Zombie) {
            val zombie = event.entity as Zombie
            zombie.customName(Component.text("ABOBA", NamedTextColor.YELLOW))
            zombie.isCustomNameVisible = true
        }
    }
}