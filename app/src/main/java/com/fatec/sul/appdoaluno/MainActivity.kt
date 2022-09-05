package com.fatec.sul.appdoaluno

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.arraySetOf
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fatec.sul.appdoaluno.databinding.ActivityMainBinding
import com.fatec.sul.appdoaluno.model.Horario
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val navView = binding.navigationView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val setMenu = arraySetOf(R.id.professorFragment,
            R.id.materiaFragment,
            R.id.homeFragment,
            R.id.perfilFragment,
            R.id.chamadaFragment,
            R.id.acenoFragment,
            R.id.abrirChamadaFragment,
            R.id.materiaProfessorFragment,
        R.id.horarioFragment)

        appBarConfiguration = AppBarConfiguration(
            setMenu, binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
       return findNavController(R.id.nav_host_fragment_content_main)
           .navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}