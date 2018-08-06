package com.jackson.ktmovie.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.SingleLayoutHelper
import com.jackson.ktmovie.R
import com.jackson.ktmovie.adapter.home.BannerAdapter
import com.jackson.ktmovie.bean.HomeBean
import com.jackson.ktmovie.dagger.component.DaggerHomeComponent
import com.jackson.ktmovie.dagger.module.HomeModule
import com.jackson.ktmovie.presenter.HomePresenter
import com.jackson.ktmovie.utils.Constant
import com.jackson.ktmovie.utils.LogUtil
import com.jackson.ktmovie.view.IView
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.find
import java.util.*
import javax.inject.Inject

/**
 * HomeFragment  2018-06-19
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * 首页
 * @author Jackson
 * @version 1.0.0
 * since 2018 06 19
 */
class HomeFragment : Fragment(), IView.IHomeView {

    private var rootView: View? = null
    private lateinit var llBack: LinearLayout  // 返回键，需要隐藏
    private lateinit var tvTitle: TextView   // title
    private lateinit var mRecyclerView: RecyclerView

    private lateinit var mAdapters: LinkedList<DelegateAdapter.Adapter<*>>
    private lateinit var delegateAdapter: DelegateAdapter


    // adapter
    private lateinit var mBannerAdapter: BannerAdapter  // Banner适配器

    @Inject
    lateinit var mHomePresenter: HomePresenter


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater!!.inflate(R.layout.fragment_home_layout, null)
            inject();
            initView()
            initData()
            mHomePresenter.getData()

        }
        return rootView
    }

    /**
     * inject
     */
    private fun inject() =
            DaggerHomeComponent.builder()
                    .homeModule(HomeModule(this))
                    .build()
                    .inject(this)


    /**
     * 初始化界面
     */
    private fun initView() {
        // 隐藏返回键
        llBack = rootView!!.find(R.id.ll_back)
        llBack.visibility = View.INVISIBLE
        // 设置title
        tvTitle = rootView!!.find(R.id.tv_head_title)
        tvTitle.text = "首页"
        // 设置RecyclerView
          mRecyclerView = rootView!!.find(R.id.recyclerView)
        /*   var layoutManger: LinearLayoutManager = LinearLayoutManager(activity)
          mRecyclerView.layoutManager = layoutManger*/
        mAdapters = LinkedList<DelegateAdapter.Adapter<*>>()
    }

    private fun initData() {
        //初始化
        //创建VirtualLayoutManager对象
        val layoutManager = VirtualLayoutManager(activity)
        layoutManager.setRecycleOffset(5000)
        // 将VirtualLayoutManager绑定到recyclerView
        mRecyclerView.layoutManager = layoutManager

        //设置组件复用回收池,（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        val viewPool = RecyclerView.RecycledViewPool()
        mRecyclerView.recycledViewPool = viewPool
        //  mRecyclerView.addItemDecoration(itemDecoration);
        viewPool.setMaxRecycledViews(0, 20)

        delegateAdapter = DelegateAdapter(layoutManager, true)
        mRecyclerView.adapter = delegateAdapter
    }

    override fun setData(mDataList: List<HomeBean.DataBean>) {
        LogUtil.d("size==" + mDataList.size)
        setBannerData(mDataList[0].items!!)
        setAllData()
    }

    override fun onRefresh(mDataList: List<HomeBean.DataBean>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setBannerData(items: List<HomeBean.DataBean.ItemsBean>) {
        val singleLayoutHelper: SingleLayoutHelper = SingleLayoutHelper()
        var imgList: MutableList<String> = arrayListOf()
        items.indices.mapTo(imgList) { "http:" + items[it].img }
        mBannerAdapter= BannerAdapter(activity,imgList,singleLayoutHelper,R.layout.item_home_banner_layout,1,Constant.typeBanner)
    }

    private fun setAllData(){
        mAdapters.add(mBannerAdapter)
        delegateAdapter.setAdapters(mAdapters)
    }

    override fun closeDispose(disposable: Disposable) = Unit


    /**
     * 伴生对象，提供Fragment的实例
     */
    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }

    }


}