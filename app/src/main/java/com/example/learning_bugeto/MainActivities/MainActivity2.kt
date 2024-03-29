package com.example.learning_bugeto.MainActivities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.learning_bugeto.R
import com.example.learning_bugeto.databinding.ActivityMain2Binding

const val RequestCode1 = 1

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMain2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        name = intent.getStringExtra("ok").toString()
        binding.tvTop.text = name

        listener()
        drawer()

    }

    private fun listener() {


        binding.btContact.setOnClickListener {
            val contact = Intent(Intent.ACTION_PICK)
            contact.type = ContactsContract.Contacts.CONTENT_TYPE
            startActivityForResult(contact, RequestCode1)
        }

        binding.btWeb.setOnClickListener {
            val web = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            startActivity(web)
        }

        binding.btMap.setOnClickListener {
            val map = Intent(Intent.ACTION_VIEW, Uri.parse("geo:35:74433781,51.35067178"))
            startActivity(map)
        }

        binding.btMakeCall.setOnClickListener {
            val call = Intent(Intent.ACTION_DIAL, Uri.parse("tel:09137707200"))
            startActivity(call)
        }

        binding.btProgressBar.setOnCreateContextMenuListener(this)

        binding.btProgressBar.setOnClickListener {
            binding.progressbar.visibility = View.VISIBLE
            binding.progressHorizontal.visibility = View.VISIBLE
            binding.progressCircular.visibility = View.VISIBLE

        }

        binding.btNavigation.setOnItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.action_home -> {
                    showMessage("home")
                    finish()
                    true
                }
                R.id.action_explore -> {
                    showMessage("explore")
                    true
                }
                R.id.action_user -> {
                    showMessage("user")
                    true
                }else ->{false}
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RequestCode1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "با موفقیت انجام شد.($data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_search -> {
                showMessage("action search clicked")
            }
            R.id.action_profile -> {
                showMessage("action profile clicked")
            }
            R.id.setting -> {
                showMessage("action setting clicked")
            }

        }
        return true
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu?.setHeaderTitle("which progress bar")
        menu?.add("horizontal")
        menu?.add("circular")
        menu?.add("circular Big")
        menu?.add("none")

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.title) {

            "horizontal" -> {
                showMessage("horizontal")
                binding.progressHorizontal.visibility = View.VISIBLE

            }
            "circular" -> {
                showMessage("circular")
                binding.progressbar.visibility = View.VISIBLE
            }
            "circular Big" -> {
                showMessage("circular Big")
                binding.progressCircular.visibility = View.VISIBLE
            }
            "none" -> {
                showMessage("none")
                binding.progressHorizontal.visibility = View.GONE
                binding.progressCircular.visibility = View.GONE
                binding.progressbar.visibility = View.GONE
            }
        }
        return true
    }

    private fun drawer() {
        drawerLayout = findViewById(R.id.drawerLayout)
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBarToggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.setting_drawer -> {
                    showMessage("setting drawer clicked")
                    true
                }
                R.id.order -> {
                    showMessage("order clicked")
                    true
                }
                R.id.myProfile -> {
                    showMessage("myProfile clicked")
                    true
                }
                else -> false

            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (drawerLayout.isDrawerOpen(binding.navigationView)) {
            drawerLayout.closeDrawer(binding.navigationView)
        } else {
            drawerLayout.openDrawer(binding.navigationView)
        }
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}