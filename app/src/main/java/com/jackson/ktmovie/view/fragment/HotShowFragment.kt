package com.jackson.ktmovie.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.jackson.ktmovie.R
import com.jackson.ktmovie.adapter.HotShowAdapter
import com.jackson.ktmovie.bean.HotShowBean
import com.jackson.ktmovie.dagger.component.DaggerHotShowComponent
import com.jackson.ktmovie.dagger.module.HotShowModule
import com.jackson.ktmovie.presenter.HotShowPresenter
import com.jackson.ktmovie.utils.Constant
import com.jackson.ktmovie.view.IView
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.find
import javax.inject.Inject

/**
 * HomeFragment  2018-06-19
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * 热映
 * @author Jackson
 * @version 1.0.0
 * since 2018 06 19
 */
class HotShowFragment : Fragment(), IView.IHotShowView {

    private var rootView: View? = null
    private lateinit var llBack: LinearLayout  // 返回键，需要隐藏
    private lateinit var tvTitle: TextView   // title
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mHotShowAdapter: HotShowAdapter
    private lateinit var mRefreshLayout: SmartRefreshLayout
    private var type: Int = 0  // 类型，0--刚进入 1--刷新 2--加载
    private var start: Int = 0  // 起始刷新位置

    @Inject
    lateinit var mHotShowPresenter: HotShowPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater!!.inflate(R.layout.fragment_hotshow_layout, null)
            inject()
            initView()
            mHotShowPresenter.getData(type, start)  // 获取数据
            refreshListen()

        }
        return rootView
    }

    /**
     * 初始化界面
     */
    private fun initView() {
        // 隐藏返回键
        llBack = rootView!!.find(R.id.ll_back)
        llBack.visibility = View.INVISIBLE
        // 设置title
        tvTitle = rootView!!.find(R.id.tv_head_title)
        tvTitle.text = "热映"
        // refresh
        mRefreshLayout = rootView!!.find(R.id.refreshLayout)
        // 设置RecyclerView
        mRecyclerView = rootView!!.find(R.id.recyclerView)
        var layoutManger: LinearLayoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = layoutManger
        // 设置refresh
        mRefreshLayout.autoRefresh() // 开启自动刷新
        mRefreshLayout.setEnableAutoLoadMore(true)  // 开启自动加载
        // mRefreshLayout.setEnableAutoLoadMore(false);//使上拉加载具有弹性效果
        //  mRefreshLayout.setEnableOverScrollDrag(false);//禁止越界拖动（1.0.4以上版本）
        // mRefreshLayout.setEnableOverScrollBounce(false);//关闭越界回弹功能

        // 设置官方刷新主题
        mRefreshLayout.setRefreshHeader(MaterialHeader(activity))
        mRefreshLayout.setEnableHeaderTranslationContent(false)
    }

    /**
     * 完成mInTheatersPresenter的注入
     */
    private fun inject() = DaggerHotShowComponent.builder()
            .hotShowModule(HotShowModule(this))
            .build()
            .inject(this)


    override fun setData(dataList: MutableList<HotShowBean.SubjectsBean>) {
        mHotShowAdapter = HotShowAdapter(R.layout.item_hotshow_layout, dataList)
        mRecyclerView.adapter = mHotShowAdapter
    }

    /**
     * 刷新加载监听
     */
    private fun refreshListen() {
        // refresh
        mRefreshLayout.setOnRefreshListener {
            mRefreshLayout.layout.postDelayed({
                type=1
                start = 0
                mHotShowPresenter.getData(type, start)
            }, 2000)
        }

        // loadmore
        mRefreshLayout.setOnLoadMoreListener {
            mRefreshLayout.layout.postDelayed({
                type = 2
                start += Constant.PAGE_COUNT
                mHotShowPresenter.getData(type, start)
            }, 2000)
        }
    }

    /**
     * refresh
     */
    override fun onRefresh(mDataList: List<HotShowBean.SubjectsBean>) {
        mHotShowAdapter.setNewData(mDataList)
        mRefreshLayout.finishRefresh()
        mRefreshLayout.setNoMoreData(false)
    }

    /**
     * loadMore
     */
    override fun onLoadMore(mDataList: List<HotShowBean.SubjectsBean>) {
        mHotShowAdapter.addData(mDataList)
        if (mDataList.size<Constant.PAGE_COUNT){
            mRefreshLayout.finishLoadMoreWithNoMoreData()
        }
        mRefreshLayout.finishLoadMore()
    }


    override fun closeDisposable(disposable: Disposable) = Unit


    /**
     * 伴生对象，提供Fragment的实例
     */
    companion object {
        fun newInstance(): HotShowFragment {
            val fragment = HotShowFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }

    }


}