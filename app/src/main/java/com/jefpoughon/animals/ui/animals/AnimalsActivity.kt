package com.jefpoughon.animals.ui.animals

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jefpoughon.animals.R
import com.jefpoughon.animals.extensions.toAnimalPicture
import com.jefpoughon.animals.model.AnimalPicture
import com.jefpoughon.animals.service.CatService
import com.jefpoughon.animals.service.DogService
import com.jefpoughon.animals.ui.BaseActivity
import com.jefpoughon.animals.ui.home.ANIMALS
import com.jefpoughon.animals.ui.home.CATS
import kotlinx.android.synthetic.main.activity_animals.*
import kotlinx.coroutines.*
import java.net.URL

class AnimalsActivity : BaseActivity() {

    private lateinit var catService: CatService
    private lateinit var dogService: DogService

    private lateinit var recyclerView: RecyclerView
    private lateinit var readerAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var animals = ArrayList<AnimalPicture>()

    private var isCats = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animals)

        viewManager = LinearLayoutManager(this)
        readerAdapter = AnimalAdapter(animals, this)

        recyclerView = animals_rv.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = readerAdapter
        }

        isCats = intent.getStringExtra(ANIMALS).equals(CATS)
    }

    override fun onResume() {
        super.onResume()

        if (isCats) {
            catService = CatService.Creator.create()
        } else {
            dogService = DogService.Creator.create()
        }

        animals.clear()

        for (i in 0 until settings.number.toInt()) {
            GlobalScope.launch(Dispatchers.Main) {

                // async task to get / download bitmap from url
                val result: Deferred<AnimalPicture> = GlobalScope.async {
                    val animalJson = if (isCats) catService.getCat() else dogService.getDog()
                    val animal = animalJson.toAnimalPicture()
                    animal.image = getBitMap(animal.filePath)
                    animal
                }

                // get the downloaded bitmap
                val animal: AnimalPicture = result.await()
                animals.add(animal)
                refreshRecyclerView()
            }
        }
    }

    private fun getBitMap(url: String): Bitmap {
        Log.d("URL", url)
        val inputStream =
            URL(url).openConnection().getInputStream()
        return BitmapFactory.decodeStream(inputStream)
    }

    private fun refreshRecyclerView() {
        readerAdapter.notifyDataSetChanged()
        pgb_animals.visibility = View.GONE
    }
}