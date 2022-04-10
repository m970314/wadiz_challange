package com.test.wadiz.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.wadiz.ViewModel.SearchViewModel
import com.test.wadiz.data.Body
import com.test.wadiz.data.SearchResponse
import com.test.wadiz.databinding.ActivityMainBinding
import com.test.wadiz.repo.RequestRepository
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var searchinfoMutableLiveData: MutableLiveData<Body> = MutableLiveData()
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = requireNotNull(_binding)
    private lateinit var viewModel: SearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        val adapter = SearchAdapter()
            .apply { onClick =this@MainActivity::openWebView }
        binding.lectureList.layoutManager = LinearLayoutManager(this)
        binding.lectureList.adapter = adapter
        /**
         * 검색 API 예제 코드.
         * 샘플의 모든 코드는 원하는데로 변경, 수정 가능합니다.
         */
        binding.editText.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                var edittext = binding.editText.text.toString()
                viewModel.search(edittext)
                binding.whole.setOnClickListener {
                    viewModel.searchinfoMutableLiveData.observe(this, Observer {
                        (binding.lectureList.adapter as SearchAdapter).wholeupdateLectures(it.body.list)
                    })
                }
                binding.funding.setOnClickListener {
                    viewModel.searchinfoMutableLiveData.observe(this, Observer {
                        (binding.lectureList.adapter as SearchAdapter).fundingupdateLectures(it.body.list)
                    })
                }
                binding.store.setOnClickListener {
                    viewModel.searchinfoMutableLiveData.observe(this, Observer {
                        (binding.lectureList.adapter as SearchAdapter).storeupdateLectures(it.body.list)
                    })
                }
            }
            true
        }
        viewModel.progressVisible.observe(this, Observer {
            binding.progressBar.visibility = it.toVisibility()
        })
    }

    private fun initUI() {
        //TODO init UI
    }

    /**
     * URL을 넘겨 웹브라우져 실행.
     */
    fun openWebView(url: String) = startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))
}