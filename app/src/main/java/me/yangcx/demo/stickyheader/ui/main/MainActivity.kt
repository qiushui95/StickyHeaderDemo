package me.yangcx.demo.stickyheader.ui.main

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.marginBottom
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.noober.background.BackgroundLibrary
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_area_parent.view.*
import me.yangcx.demo.stickyheader.R
import me.yangcx.demo.stickyheader.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private val binder by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
            include.lifecycleOwner = this@MainActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BackgroundLibrary.inject2(this)
        binder.include.root.setOnClickListener {
            binder.rvProvinceAndCity.smoothScrollToPosition(viewModel.getPositionById(it.tag.toString()))
        }
        binder.rvProvinceAndCity.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (recyclerView.childCount > 0) {
                    val position = recyclerView.getChildAdapterPosition(recyclerView.getChildAt(0))
                    viewModel.getCurrentParent(position)?.also { currentParent ->
                        binder.include.data = currentParent
                        0.until(rvProvinceAndCity.childCount)
                                .map {
                                    rvProvinceAndCity.getChildAt(it)
                                }.lastOrNull {
                                    it.tag.toString() == currentParent.id
                                }?.apply {
                                    val targetY = min(binder.include.root.height * 1f, bottom * 1f + marginBottom) - binder.include.root.height
                                    if (targetY != binder.include.root.y) {
                                        binder.include.root.y = targetY
                                    }
                                }
                    }
                }
            }
        })
    }
}
