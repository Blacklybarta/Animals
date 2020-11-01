package com.jefpoughon.animals.extensions

import com.jefpoughon.animals.model.AnimalJson
import com.jefpoughon.animals.model.AnimalPicture

fun AnimalJson.toAnimalPicture(): AnimalPicture {
    return AnimalPicture(
        filePath = if (file.isNotEmpty()) {
            file
        } else {
            if (url.endsWith(".mp4")) "https://random.dog/1573beb6-6c9f-4705-95c9-7878c7c2e8d8.jpg" else url
        },
        image = null,
        favorite = false
    )
}
