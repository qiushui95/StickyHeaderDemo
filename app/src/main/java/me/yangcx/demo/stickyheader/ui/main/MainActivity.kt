package me.yangcx.demo.stickyheader.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.noober.background.BackgroundLibrary
import me.yangcx.demo.stickyheader.R
import me.yangcx.demo.stickyheader.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BackgroundLibrary.inject2(this)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner=this@MainActivity
            viewModel = this@MainActivity.viewModel
        }
    }
}
