package com.jefpoughon.animals.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.jefpoughon.animals.R
import com.jefpoughon.animals.ui.BaseActivity
import com.jefpoughon.animals.ui.CHOICE
import com.jefpoughon.animals.ui.animals.AnimalsActivity
import com.jefpoughon.animals.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_home.*

const val ANIMALS = "animals"

class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initDrawer()
    }

    private fun initDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        navigation.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            R.id.action_cats -> {
                startActivity(Intent(this, AnimalsActivity::class.java).apply {
                    putExtra(ANIMALS, CHOICE.CATS.choice)
                })
                true
            }
            R.id.action_dogs -> {
                startActivity(Intent(this, AnimalsActivity::class.java).apply {
                    putExtra(ANIMALS, CHOICE.DOGS.choice)
                })
                true
            }
            R.id.action_favorites -> {
                startActivity(Intent(this, AnimalsActivity::class.java).apply {
                    putExtra(ANIMALS, CHOICE.FAVORITES.choice)
                })
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        }
    }
}