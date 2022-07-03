package br.com.fiap.affily
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
=======
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fiap.affily.databinding.ActivityMainBinding
import br.com.fiap.affily.models.entities.Child
import br.com.fiap.affily.ui.base.auth.BaseAuthViewModel
import br.com.fiap.affily.ui.newchild.NewChildActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.*

import androidx.fragment.app.FragmentTransaction

>>>>>>> 6acacd4fb69235638cf981a352df2cb71f0d44d1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< HEAD
        fullScreen()
        setContentView(R.layout.activity_main)
=======
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        initViewModel()
        initObserver()
        initListeners()
    }
    private fun initListeners() {
        binding.fabNewProduct.setOnClickListener{
            val nextScreen = Intent(this, NewChildActivity::class.java)
           newProductRequest.launch(nextScreen)
        }
    }


    fun rand(from: Int, to: Int) : Int {

        val random = Random()
        return random.nextInt(to - from) + from // from(incluso) e to(excluso)
    }
    private val newProductRequest =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            if (it.resultCode == RESULT_OK) {
                val id = rand(0,10000);
                it.data?.getStringExtra(NewChildActivity.EXTRA_REPLY)?.let {
                    val child = Child(1, "a")
                    mainViewModel.insert(child)
                }
            }
        }


    private fun initViewModel() {
        mainViewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)
>>>>>>> 6acacd4fb69235638cf981a352df2cb71f0d44d1
    }

    private fun fullScreen() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide()
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }
}