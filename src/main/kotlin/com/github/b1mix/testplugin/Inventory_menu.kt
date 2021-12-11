package com.github.b1mix.testplugin

import com.google.common.util.concurrent.ServiceManager
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.plugin.Plugin
import java.net.http.WebSocket

class Inventory_menu(
    val plugin: Plugin,
    val player: Player,
    val rows: Int
) : InventoryHolder, Listener {
   private val inventory = Bukkit.createInventory(
       this,
       rows * 9
   )

    override fun getInventory(): Inventory = inventory

    fun open() {
      plugin.server.pluginManager.registerEvents(this, plugin)
      player.openInventory(inventory)
    }

    @EventHandler
    fun onClose(event: InventoryCloseEvent) {
        if (event.inventory.holder == this) {
            HandlerList.unregisterAll(this)
        }
    }
}