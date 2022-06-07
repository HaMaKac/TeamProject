package com.flowers

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.flowers.databinding.ActivityMainBinding
import com.flowers.ui.FlowerModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    var globalBalance = 2
    var availableFlowers = Array(10) {FlowerModel()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_shop, R.id.nav_plant, R.id.nav_info
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        globalBalance = 25
        fillFlowerArray()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun fillFlowerArray() {
        availableFlowers = arrayOf<FlowerModel>(
            FlowerModel(false, "0th flower", 1),
            FlowerModel(false, "Lily", R.drawable.lilia_plant),
            FlowerModel(false, "Lily of the Valley", R.drawable.konwalie_plant),
            FlowerModel(false, "Gerber", R.drawable.gerber),
            FlowerModel(false, "Forget-me-Not", R.drawable.niezapominajka_plant),
            FlowerModel(false, "Sunflower", R.drawable.slonecznik),
            FlowerModel(false, "Daisy", R.drawable.stokrotka),
            FlowerModel(false, "Tulip", R.drawable.tulipan),
            FlowerModel(false, "Daffodil", R.drawable.zonkil),
            FlowerModel(false, "Rose", R.drawable.roza_plant))
    }
}