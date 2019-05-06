@file:Suppress("SENSELESS_COMPARISON")

package com.example.ejemplotoolbar

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ShareActionProvider
import android.widget.Toast


@Suppress("CAST_NEVER_SUCCEEDS")
class MainActivity : AppCompatActivity() {

    var toolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(R.string.app_name)
        //toolbar?.title = "Deportes Scott"
        setSupportActionBar(toolbar)

        var bIr = findViewById<Button>(R.id.bIr)
        bIr.setOnClickListener{
            val intent = Intent(this, pantalla2::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val itemBusqueda = menu?.findItem(R.id.busqueda)
        val vistaBusqueda = itemBusqueda?.actionView as android.support.v7.widget.SearchView
        val itemCompartir = menu?.findItem(R.id.share)
        val shareActionProvider = MenuItemCompat.getActionProvider(itemCompartir) as ShareActionProvider

        compartirIntent(shareActionProvider)

        vistaBusqueda.queryHint = "¿Que es lo que buscas?"

        vistaBusqueda.setOnQueryTextFocusChangeListener { v, hasFocus ->
            Log.d("LISTENERFOCUS", hasFocus.toString())
        }


        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.bfav -> {
                Toast.makeText(this, "Elemento añadido a favorito", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)}
        }


    }

    private fun compartirIntent(shareActionProvider: ShareActionProvider){
        if (shareActionProvider != null){
            val intent= Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Este es un mensaje commpartido")
            shareActionProvider.setShareIntent(intent)
        }
    }

}
