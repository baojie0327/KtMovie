package com.jackson.ktmovie.view.fragment

import android.graphics.Color
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
import com.alibaba.android.vlayout.layout.*
import com.jackson.ktmovie.R
import com.jackson.ktmovie.adapter.BaseDelegateAdapter
import com.jackson.ktmovie.adapter.home.*
import com.jackson.ktmovie.bean.HomeBean
import com.jackson.ktmovie.dagger.component.DaggerHomeComponent
import com.jackson.ktmovie.dagger.module.HomeModule
import com.jackson.ktmovie.presenter.HomePresenter
import com.jackson.ktmovie.utils.CommonMethod
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
    private lateinit var mGridMenuAdapter: GridMenuAdapter // GridMenu
    private lateinit var mMarqueeAdapter: MarqueeAdapter // 公告适配器
    private lateinit var mGridMenuSpanAdapter: GridMenuSpanAdapter // Grid Span

    private lateinit var mScrollFixAdapter: ScrollFixAdapter // ScrollFix

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

        /* var list_f = mutableListOf(1, 2, 3, 4, 5, 6, 7)
         list_f.indices
                 .map { it.toString() + " my" }
                 .filter { it == "5 my" }
                 .forEach { LogUtil.d(it.toString()) }*/
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
        setGridMenu(mDataList[1].items!!)
        setScrollFix()
        setMarquee(mDataList[2].items!!)
        setGridSpan(mDataList[3].items!!)
        setAllData()
    }

    override fun onRefresh(mDataList: List<HomeBean.DataBean>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 设置轮播
     */
    private fun setBannerData(items: List<HomeBean.DataBean.ItemsBean>) {
        val singleLayoutHelper: SingleLayoutHelper = SingleLayoutHelper()
        var imgList: MutableList<String> = mutableListOf()
        items.indices.mapTo(imgList) { "http:" + items[it].img }
        mBannerAdapter = BannerAdapter(activity, imgList, singleLayoutHelper, R.layout.item_home_banner_layout, 1, Constant.typeBanner)
        mBannerAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(activity, "click--" + position, false)


        })
    }

    /**
     * GridMenu
     */
    private fun setGridMenu(items: List<HomeBean.DataBean.ItemsBean>) {
        val gridLayoutHelper: GridLayoutHelper = GridLayoutHelper(5)
        //  gridLayoutHelper.setMarginTop(300);  //设置距离顶部的距离
        //  gridLayoutHelper.setItemCount(30);  // 设置布局里Item个数
        gridLayoutHelper.setPadding(0, 0, 0, 0) // 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        // gridLayoutHelper.setMargin(20,10,20,10);   //设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //  gridLayoutHelper.setAspectRatio(6); // 设置设置布局内每行布局的宽与高的比
        //  gridLayoutHelper.setWeights(new float[]{40, 10, 20,20,10});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.vGap = 16  // 控制子元素之间的垂直间距
        gridLayoutHelper.hGap = 0  // 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false)//是否自动填充空白区域
        //   gridLayoutHelper.setSpanCount(3);// 设置每行多少个网格
        gridLayoutHelper.bgColor = Color.WHITE // 设置背景颜色
        mGridMenuAdapter = GridMenuAdapter(activity, items, gridLayoutHelper, R.layout.item_home_gridmenu_layout, items.size, Constant.typeGvidMenu)
        // 监听
        mGridMenuAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(activity, "click--" + position, false)
        })

        mGridMenuAdapter.setOnItemChildClickListener(object : BaseDelegateAdapter.OnItemChildClickListener {
            override fun onItemChildClick(view: View, position: Int) {
                when (view.id) {
                    R.id.img_grid_menu -> CommonMethod.showToast(activity, "click-child-" + position, false)
                }
            }

        })

    }


    /**
     * 设置公告
     */
    private fun setMarquee(items: List<HomeBean.DataBean.ItemsBean>) {
        val linearLayoutHelper: LinearLayoutHelper = LinearLayoutHelper()
        var infoList: MutableList<String> = mutableListOf()
        items.indices.mapTo(infoList) { items[it].title!! }
        mMarqueeAdapter = MarqueeAdapter(activity, infoList, linearLayoutHelper, R.layout.item_home_marquee_layout, 1, Constant.typeMarquee)
        mMarqueeAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(activity, "click--" + position, false)

        })
    }

    private fun setGridSpan(items: List<HomeBean.DataBean.ItemsBean>) {
        val gridLayoutHelper = GridLayoutHelper(4)
        // gridLayoutHelper.setPadding(0, 30, 0, 30); // 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        //    gridLayoutHelper.setVGap(10);  // 控制子元素之间的垂直间距
        //  gridLayoutHelper.setHGap(10);  // 控制子元素之间的水平间距
        //  gridLayoutHelper.setMargin(0,20,0,20);
        gridLayoutHelper.setAutoExpand(false)//是否自动填充空白区域
        gridLayoutHelper.bgColor = Color.WHITE // 设置背景颜色

        // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
        val count = mBannerAdapter.itemCount + mGridMenuAdapter.itemCount + mMarqueeAdapter.itemCount + mScrollFixAdapter.itemCount

        gridLayoutHelper.setSpanSizeLookup(object : GridLayoutHelper.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int = if (position <= count + 1) {
                2
            } else {
                1
            }
        })

        mGridMenuSpanAdapter = GridMenuSpanAdapter(activity, items, gridLayoutHelper, R.layout.item_home_gridspan_layout, items.size, Constant.typeHot)
        mGridMenuSpanAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(activity, "click--" + position, false)

        })

    }

    /**
     * ScrollFix
     */
    private fun setScrollFix() {
        val scrollFixLayoutHelper: ScrollFixLayoutHelper = ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_RIGHT, 80, 60)
        // 公共属性
        scrollFixLayoutHelper.itemCount = 1// 设置布局里Item个数
        scrollFixLayoutHelper.setPadding(20, 20, 20, 20)// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        scrollFixLayoutHelper.setMargin(20, 20, 20, 20)// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        scrollFixLayoutHelper.bgColor = Color.GRAY// 设置背景颜色
        //  scrollFixLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // fixLayoutHelper特有属性
        scrollFixLayoutHelper.setAlignType(FixLayoutHelper.TOP_RIGHT)// 设置吸边时的基准位置(alignType)
        scrollFixLayoutHelper.setX(10)// 设置基准位置的横向偏移量X
        scrollFixLayoutHelper.setY(10)// 设置基准位置的纵向偏移量Y
        scrollFixLayoutHelper.showType = ScrollFixLayoutHelper.SHOW_ON_LEAVE// 设置Item的显示模式

        val list: List<String> = arrayListOf()
        mScrollFixAdapter = ScrollFixAdapter(activity, list, scrollFixLayoutHelper, R.layout.item_home_fix_layout, 1, Constant.typeScrollFix)
        mScrollFixAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(activity, "click--" + position, false)


        })
    }

    private fun setAllData() {
        mAdapters.add(mBannerAdapter)
        mAdapters.add(mGridMenuAdapter)
        mAdapters.add(mScrollFixAdapter)
        mAdapters.add(mMarqueeAdapter)
        mAdapters.add(mGridMenuSpanAdapter)
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