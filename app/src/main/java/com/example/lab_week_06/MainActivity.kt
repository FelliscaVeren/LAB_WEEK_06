package com.example.lab_week_06

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.Gender

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    private val catAdapter by lazy {
        CatAdapter(layoutInflater, GlideImageLoader(this), object : CatAdapter.OnClickListener {
            override fun onItemClick(cat: CatModel) = showSelectionDialog(cat)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup RecyclerView
        recyclerView.adapter = catAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 🔹 Attach swipe-to-delete ke RecyclerView
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                catAdapter.removeItem(position)
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // Tambahkan data dummy (10 item minimal)
        val catList = listOf(
            CatModel(Gender.Male, CatBreed.BalineseJavanese, "Fred", "Silent and deadly", "https://cdn2.thecatapi.com/images/7dj.jpg"),
            CatModel(Gender.Female, CatBreed.ExoticShorthair, "Wilma", "Cuddly assassin", "https://cdn2.thecatapi.com/images/egv.jpg"),
            CatModel(Gender.Unknown, CatBreed.AmericanCurl, "Curious George", "Award winning investigator", "https://cdn2.thecatapi.com/images/bar.jpg"),
            CatModel(Gender.Male, CatBreed.Bengal, "Tiger", "Fast and playful", "https://cdn2.thecatapi.com/images/06e.jpg"),
            CatModel(Gender.Female, CatBreed.Burmese, "Luna", "Loves cuddles", "https://cdn2.thecatapi.com/images/5n8.jpg"),
            CatModel(Gender.Male, CatBreed.Chartreux, "Smokey", "Quiet observer", "https://cdn2.thecatapi.com/images/3j6.jpg"),
            CatModel(Gender.Unknown, CatBreed.DevonRex, "Pixie", "Funny and curious", "https://cdn2.thecatapi.com/images/8is.jpg"),
            CatModel(Gender.Female, CatBreed.Himalayan, "Bella", "Elegant beauty", "https://cdn2.thecatapi.com/images/mt7.jpg"),
            CatModel(Gender.Male, CatBreed.MaineCoon, "Max", "Big and friendly", "https://cdn2.thecatapi.com/images/ozm.jpg"),
            CatModel(Gender.Unknown, CatBreed.Ragdoll, "Mochi", "Lazy and adorable", "https://cdn2.thecatapi.com/images/9ou.jpg")
        )

        catAdapter.setData(catList)
    }

    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
            .setTitle("Cat Selected")
            .setMessage("You have selected cat ${cat.name}")
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }
}
