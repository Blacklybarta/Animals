package com.jefpoughon.animals.ui.animals.cats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.jefpoughon.animals.R
import com.jefpoughon.animals.model.AnimalPicture
import com.jefpoughon.animals.service.CatService
import com.jefpoughon.animals.ui.animals.AnimalAdapter
import com.jefpoughon.animals.ui.settings.AppSettings
import kotlinx.android.synthetic.main.activity_cats.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

class CatsActivity : KoinComponent, AppCompatActivity() {

    private lateinit var catService: CatService
    private val settings: AppSettings by inject()

    private lateinit var recyclerView: RecyclerView
    private lateinit var readerAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var animals = ArrayList<AnimalPicture>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cats)

        viewManager = LinearLayoutManager(this)
        readerAdapter = AnimalAdapter(animals, this)

        recyclerView = cats_rv.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = readerAdapter
        }

    }

    override fun onResume() {
        super.onResume()
        catService = CatService.Creator.create()
        lifecycleScope.launchWhenStarted {
            try {
                animals.clear()

                for (i in 0 until settings.number.toInt()) {
                    animals.add(
                        GsonBuilder().create()
                            .fromJson(catService.getCat(), AnimalPicture::class.java)
                    )
                }
            } catch (exception: Exception) {
                Log.e("URL", exception.message ?: "ERROR")
            }

            withContext(Dispatchers.Main) {
                refreshRecyclerView()
            }
        }
    }

    private fun refreshRecyclerView() {
        readerAdapter.notifyDataSetChanged()
    }

    fun saveAsFavorite(picture: AnimalPicture) {

    }
}