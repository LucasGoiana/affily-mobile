package br.com.fiap.affily

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: MainListAdapter

    private val baseAuthViewModel: BaseAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }

    private fun fullScreen() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide()

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }

    private fun initObserver() {
        mainViewModel.childs.observe(this, Observer { childs ->
            childs?.let { adapter.setChild(it) }
        })
    }

    private fun setUpRecyclerView() {
        adapter = MainListAdapter()
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
    }

}