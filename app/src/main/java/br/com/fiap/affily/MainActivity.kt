package br.com.fiap.affily
import android.annotation.SuppressLint
import android.content.IntentFilter.create
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.affily.models.entities.Children
import br.com.fiap.affily.ui.child.ChildrenViewModel

class MainActivity : AppCompatActivity(), MainListAdapter.OnItemClickListener {

    private val childrenViewModel: ChildrenViewModel by viewModels()
    private lateinit var list: ArrayList<Children>
    private lateinit var rvList: RecyclerView
    private lateinit var btSave: Button

    private lateinit var name: EditText

    private lateinit var mainListAdapter: MainListAdapter
    private var selected: Children = Children()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen()
        setContentView(R.layout.activity_main)
        initElement()
        initViewModel()

    }

    private fun initElement() {
        rvList = findViewById(R.id.rvList)

        list = ArrayList()


        // Get list
        childrenViewModel.getList()

    }

    private fun initViewModel() {
//        childrenViewModel.createLiveData.observe(this, {
//            onCreate(it)
//        })

//        productViewModel.updateLiveData.observe(this, {
//            onUpdate(it)
//        })

//        productViewModel.deleteLiveData.observe(this, {
//            onDelete(it)
//        })


        childrenViewModel.getListLiveData.observe(this) {
            onGetList(it)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun onGetList(it: List<Children>) {
        list = ArrayList()
        list.addAll(it)

        var mainListAdapter = MainListAdapter(list, this)

        rvList.adapter = mainListAdapter
        rvList.layoutManager = LinearLayoutManager(baseContext)

        mainListAdapter.notifyDataSetChanged()
    }
    private fun fullScreen() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide()
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }

    override fun onClick(item: Children, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onDelete(item: Children, position: Int) {
        TODO("Not yet implemented")
    }
}