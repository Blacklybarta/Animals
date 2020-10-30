package com.jefpoughon.animals.ui.animals.dogs

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.jefpoughon.animals.R
import com.jefpoughon.animals.model.AnimalPicture
import com.jefpoughon.animals.service.DogService
import com.jefpoughon.animals.ui.BaseActivity
import com.jefpoughon.animals.ui.animals.AnimalAdapter
import kotlinx.android.synthetic.main.activity_dogs.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogsActivity : BaseActivity() {

    private lateinit var dogService: DogService

    private lateinit var recyclerView: RecyclerView
    private lateinit var readerAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var animals = ArrayList<AnimalPicture>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dogs)

        viewManager = LinearLayoutManager(this)
        readerAdapter = AnimalAdapter(animals, this)

        recyclerView = dogs_rv.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = readerAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        dogService = DogService.Creator.create()
        lifecycleScope.launchWhenStarted {
            try {
                animals.clear()

                for (i in 0 until settings.number.toInt()) {
                    animals.add(
                        GsonBuilder().create()
                            .fromJson(dogService.getDog(), AnimalPicture::class.java)
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
        pgb_dogs.visibility = View.GONE
    }
}